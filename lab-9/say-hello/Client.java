import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String response = in.readLine();
        System.out.println("Server: " + response);
        out.println("Hello from Client");
        System.out.println("Hello sent.");
        in.close();
        out.close();
        socket.close();
    }
}
