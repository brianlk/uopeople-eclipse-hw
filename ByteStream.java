import java.io.*;

public class ByteStream {

	public static void main(String[] args) {
//		File file = new File("/tmp/abc.txt");
//		try {
//			FileInputStream fis = new FileInputStream(file);
//			int temp;
//			while((temp= fis.read()) != -1) {
//				System.out.printf("%d\n",temp);
//			}
//		} catch(Exception e) {
//			System.out.println("Exception");
//		}
		
		String s = "ABCDF";
        byte[] data = s.getBytes();
        OutputStream output = null;
        for (byte b : data) {
        	System.out.println((char)b);
        	System.out.println(b);
        }
        try
        {
            output = System.out;
            output.write(data);
            output.flush();
            output.close();

        }
        catch(Exception e)
        {
            System.out.println("Message: " + e);
        }
		
	}
}
