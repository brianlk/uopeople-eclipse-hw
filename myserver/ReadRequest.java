package myserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

/**
 * The main() program in this class is designed to read requests from
 * a Web browser and display the requests on standard output.  The
 * program sets up a listener on port 50505.  It can be contacted
 * by a Web browser running on the same machine using a URL of the
 * form  http://localhost:505050/path/to/resource.html  This method
 * does not return any data to the web browser.  It simply reads the
 * request, writes it to standard output, and then closes the connection.
 * The program continues to run, and the server continues to listen
 * for new connections, until the program is terminated (by clicking the
 * red "stop" square in Eclipse or by Control-C on the command line).
 */
public class ReadRequest {
	
	private static class ConnectionThread extends Thread {
	    Socket connection;
	    ConnectionThread(Socket connection) {
	       this.connection = connection;
	    }
	    public void run() {
	       handleConnection(connection);
	    }
	 }
	
	/**
	 * The server listens on this port.  Note that the port number must
	 * be greater than 1024 and lest than 65535.
	 */
	private final static int LISTENING_PORT = 50505;
	final static String ROOTDIR = "/tmp";
	
	/**
	 * Main program opens a server socket and listens for connection
	 * requests.  It calls the handleConnection() method to respond
	 * to connection requests.  The program runs in an infinite loop,
	 * unless an error occurs.
	 * @param args ignored
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket;
		try {
			/**
			 * 
    			Open a socket.
    			Open an input stream and output stream to the socket.
    			Read from and write to the stream according to the server's protocol.
    			Close the streams.
    			Close the socket.
			 */
			serverSocket = new ServerSocket(LISTENING_PORT);
		}
		catch (Exception e) {
			System.out.println("Failed to create listening socket.");
			return;
		}
		System.out.println("Listening on port " + LISTENING_PORT);
		try {
			while (true) {
				Socket connection = serverSocket.accept();
				System.out.println("\nConnection from " 
						+ connection.getRemoteSocketAddress());
//				handleConnection(connection);
				ConnectionThread thread = new ConnectionThread(connection);
				thread.start();
			}
		}
		catch (Exception e) {
			System.out.println("Server socket shut down unexpectedly!");
			System.out.println("Error: " + e);
			System.out.println("Exiting.");
		}
	}

	/**
	 * Handle commuincation with one client connection.  This method reads
	 * lines of text from the client and prints them to standard output.
	 * It continues to read until the client closes the connection or
	 * until an error occurs or until a blank line is read.  In a connection
	 * from a Web browser, the first blank line marks the end of the request.
	 * This method can run indefinitely,  waiting for the client to send a
	 * blank line.
	 * NOTE:  This method does not throw any exceptions.  Exceptions are
	 * caught and handled in the method, so that they will not shut down
	 * the server.
	 * @param connection the connected socket that will be used to
	 *    communicate with the client.
	 */
	private static void handleConnection(Socket connection) {
		try {
			Scanner in = new Scanner(connection.getInputStream());
			while (true) {
				if ( ! in.hasNextLine() )
					break;
				String line = in.nextLine();
				if (line.trim().length() == 0 || line.equals("quit"))
					break;
				handleLogic(line, connection);
			}
		}
		catch (Exception e) {
			System.out.println("Error while communicating with client: " + e);
		}
		finally {  // make SURE connection is closed before returning!
			try {
				connection.close();
			}
			catch (Exception e) {
			}
			System.out.println("Connection closed.");
		}
	}
	
	private static void handleLogic(String line, Socket connection) {
		String[] splittedArr = line.split("\\s+");
		try {
			if (splittedArr[0].equals("GET") && splittedArr[1].length() > 0 && splittedArr[2].equals("HTTP/1.1")) {
				String filename = splittedArr[1];
				File file = new File(ROOTDIR + filename);
					if (!file.exists())
						// Send file not found.
						sendErrorResponse(404, connection);
					else if (file.isDirectory()) {
						// Send file default index.html
						sendRequestFile(file, connection);
					} else if (!file.canRead())
						sendErrorResponse(403, connection);
					else if (!(file.length() > 0)) {
						System.out.println("File length is 0.");
					} else {
						// Send file requested file.
						sendRequestFile(file, connection);
					}	
			} else if (!splittedArr[0].equals("GET")) {
//				sendErrorResponse(501, connection);
			} else
				System.out.println("Connection is not HTTP/1.1.");
			
		} catch (Exception e) {
			try {
				sendErrorResponse(505, connection);
			} catch(Exception ex) {
			}
		}
	}
	
	private static void sendRequestFile(File file, Socket connection) {
		File requestFile;
		if (file.isDirectory()) {
			requestFile = new File(ROOTDIR+"/index.html");
			if (!requestFile.exists()) {
				System.out.println("File not found.");
				return;
			}
		} else {
			requestFile = file;
		}
	
		try {
			System.out.println("Sending file...");
			sendFile(requestFile, connection.getOutputStream());
		} catch (Exception e) {
			System.out.println("Failed to send file: " + requestFile.getName());
		}
	}
	
	private static void sendFile(File requestFile, OutputStream socketOut) throws IOException {
//	    OutputStream out = new BufferedOutputStream(socketOut);
		PrintWriter writerObj = new PrintWriter(socketOut);
		writerObj.write("HTTP/1.1 200 OK\r\n");
		writerObj.write("Connection: close\r\n");
		writerObj.write("Content-Type: " + getMimeType(requestFile.getName()) + "\r\n");
		writerObj.write("Content-Length: " + requestFile.length() +"\r\n");
		writerObj.write("\r\n");
		writerObj.flush();
		InputStream in = new BufferedInputStream(new FileInputStream(requestFile));
	    while (true) {
	      int x = in.read(); // read one byte from file
	      if (x < 0)
	         break; // end of file reached
	      socketOut.write(x);  // write the byte to the socket
	    }
	    in.close();
	    socketOut.flush();
	    System.out.println("Send file completed.");
	}
	
	private static void sendErrorResponse(int errorCode, Socket socket) throws IOException {
		String status, desc;
		switch (errorCode) {
		case 404:
			status = "404 Not Found";
			desc = "The resource that you requested does not exist on this server.";
			break;
		case 403:
			status = "403 Forbidden";
			desc = "you could send this if the requested file exists, but you don't have permission to read it.";
			break;
		case 400:
			status = "400 Bad Request";
			desc = "you could send this if the syntax of the request is bad, for example if the third token is not \"HTTP/1.1\" or \"HTTP/1.0\"";
			break;
		case 501:
			status = "501 Not Implemented";
			desc = "if the method in the request in anything other than \"GET\"";
			break;
		case 500:
			status = "500 Internal Server Error";
			desc = "you could send this if you catch some unexpected error in the handleConnection method.";
			break;
		default:
			status = "";
			desc = "";
		}
		String fileNotFound = "Error: " + status + "\n" + desc + "\n";
		PrintWriter writerObj = new PrintWriter(socket.getOutputStream());
		writerObj.write("HTTP/1.1 " + status + "\r\n");
		writerObj.write("Connection: close\r\n");
		writerObj.write("Content-Type: text/plain\r\n");
		writerObj.write("\r\n");
		writerObj.write(fileNotFound);
		writerObj.flush();
	}
	
	private static String getMimeType(String fileName) {
        int pos = fileName.lastIndexOf('.');
        if (pos < 0)  // no file extension in name
            return "x-application/x-unknown";
        String ext = fileName.substring(pos+1).toLowerCase();
        if (ext.equals("txt")) return "text/plain";
        else if (ext.equals("html")) return "text/html";
        else if (ext.equals("htm")) return "text/html";
        else if (ext.equals("css")) return "text/css";
        else if (ext.equals("js")) return "text/javascript";
        else if (ext.equals("java")) return "text/x-java";
        else if (ext.equals("jpeg")) return "image/jpeg";
        else if (ext.equals("jpg")) return "image/jpeg";
        else if (ext.equals("png")) return "image/png";
        else if (ext.equals("gif")) return "image/gif";
        else if (ext.equals("ico")) return "image/x-icon";
        else if (ext.equals("class")) return "application/java-vm";
        else if (ext.equals("jar")) return "application/java-archive";
        else if (ext.equals("zip")) return "application/zip";
        else if (ext.equals("xml")) return "application/xml";
        else if (ext.equals("xhtml")) return"application/xhtml+xml";
        else return "x-application/x-unknown";
           // Note:  x-application/x-unknown  is something made up;
           // it will probably make the browser offer to save the file.
     }

}
