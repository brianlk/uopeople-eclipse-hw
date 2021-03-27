import java.io.*;

public class PlayProc {
	public static void main(String[] args) throws IOException {
		String[] command = {"touch", "/tmp/abc"};
		ProcessBuilder build = new ProcessBuilder(command);
		build.start();
	}
}
