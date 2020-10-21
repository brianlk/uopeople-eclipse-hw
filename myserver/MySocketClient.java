package myserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;


public class MySocketClient {

	
	public static void main(String[] args) {
		Socket connection;
//		InputStream in;
//		OutputStream out;
		try {
			connection = new Socket("localhost", 50505);
			PrintWriter writerObj = new PrintWriter(connection.getOutputStream());
			writerObj.write("GET /index.html HTTP/1.1\r\n");
			writerObj.write("quit\r\n");
			writerObj.flush();

			InputStream input = connection.getInputStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//			
//			String line;
//			while ((line = reader.readLine()) != null ) {
//				System.out.println(line);
//			}
			int data = input.read();
			while(data != -1){
			    System.out.print((char)data);
			    data = input.read();
			}
			connection.close();
//			connection.close();
		} catch (Exception e) {
			System.out.println("Failed to connect socket 50505.");
		}
		
	}
	
}
