import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("localhost", 5000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String expression = "5 + 3"; 
        out.println(expression);
        System.out.println("Expression sent: " + expression);
        in.close();
        out.close();
        socket.close();
    }
}
