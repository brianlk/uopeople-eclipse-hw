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
	
	/**
	 * The server listens on this port.  Note that the port number must
	 * be greater than 1024 and lest than 65535.
	 */
	private final static int LISTENING_PORT = 50505;
	
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
				handleConnection(connection);
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
		if (splittedArr[0].equals("GET") && splittedArr[1].length() > 0 && splittedArr[2].equals("HTTP/1.1")) {
			String filename = splittedArr[1];
			File file = new File("/tmp" + filename);
			if (!file.exists())
				try {
					// Send file not found.
					notFoundFile(connection);
				} catch(Exception e) {
					
				}
			else if (file.isDirectory()) {
				// Send file default index.html
				sendRequestFile(file, connection);
			} else if (!file.canRead())
				System.out.println("Failed to read the file.");
			else if (!(file.length() > 0)) {
				System.out.println("File length is 0.");
			} else {
				// Send file requested file.
				sendRequestFile(file, connection);
			}
		} else
			System.out.println("Connection is not HTTP/1.1.");
	}
	
	private static void sendRequestFile(File file, Socket connection) {
		File requestFile;
		if (file.isDirectory()) {
			requestFile = new File("/tmp/index.html");
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
	    InputStream in = new BufferedInputStream(new FileInputStream(requestFile));
//	    OutputStream out = new BufferedOutputStream(socketOut);
		PrintWriter writerObj = new PrintWriter(socketOut);
		writerObj.write("HTTP/1.1 200 OK\r\n");
		writerObj.write("Connection: close\r\n");
		writerObj.write("Content-Type: text/html\r\n");
		writerObj.write("Content-Length: " + requestFile.length() +"\r\n");
		writerObj.write("\r\n");
		writerObj.flush();
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
	
	private static void notFoundFile(Socket socket) throws IOException {
		String fileNotFound = "File not found.";
		PrintWriter writerObj = new PrintWriter(socket.getOutputStream());
		writerObj.write("HTTP/1.1 200 OK\r\n");
		writerObj.write("Connection: close\r\n");
		writerObj.write("Content-Type: text/html\r\n");
		writerObj.write("Content-Length: " + fileNotFound.length() +"\r\n");
		writerObj.write("\r\n");
		writerObj.write(fileNotFound);
		writerObj.flush();
	}

}
