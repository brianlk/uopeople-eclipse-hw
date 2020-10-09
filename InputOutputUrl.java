import java.io.*;
import java.net.*;


public class InputOutputUrl {

	private static void copyStream(InputStream in, OutputStream out) throws IOException {
		int oneByte = in.read();
		while (oneByte >= 0) { // negative value indicates end-of-stream
			out.write(oneByte);
			oneByte = in.read();
		}
	}
	
	public static void main(String[] args) {
		 InputStream in = null;
		 OutputStream out = null;
		 try {
			 URL webUrl = new URL("https://www.google.com");
			 in = webUrl.openStream();
			 out = new FileOutputStream("/tmp/output.txt");
			 copyStream(in, out);
			 in.close();
			 out.close();
		 } catch (Exception e) {
			 System.out.println("Invalid Output File:");
			 e.printStackTrace();
			 System.exit(1);
		 } finally {
			 try {
				 in.close();
				 out.close();
			 } catch(Exception e) {
				 e.printStackTrace();
				 System.exit(1);
			 }
		 }
		 System.out.println("Output is done.");
		 		 
	}
	
	
}
