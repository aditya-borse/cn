import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for client.");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");
        InputStream in = socket.getInputStream();
        FileOutputStream out = new FileOutputStream("server-received-file.txt");
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        System.out.println("File received and saved.");
        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}
