/* Q. TCP socket program in which client and server say hello to each other */

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server initiated. Waiting for client.");
        Socket socket = serverSocket.accept();
        System.out.println("Client Connected.");
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println("Hello from Server!");
        System.out.println("Hello sent.");
        String response = in.readLine();
        System.out.println("Client: " + response);
        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }
}
