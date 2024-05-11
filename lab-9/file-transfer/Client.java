import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        OutputStream out = socket.getOutputStream();
        FileInputStream in = new FileInputStream("client-file.txt");
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        System.out.println("File sent.");
        in.close();
        out.close();
        socket.close();
    }
}
