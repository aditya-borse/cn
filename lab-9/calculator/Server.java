import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for client.");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String exp = in.readLine();
        System.out.println("Expression to evaluate: " + exp);
        String[] tokens = exp.split(" ");
        double op1 = Double.parseDouble(tokens[0]);
        double op2 = Double.parseDouble(tokens[2]);
        char operator = tokens[1].charAt(0);
        double result;
        switch (operator) {
            case '+':
                result = op1 + op2;
                break;
            case '-':
                result = op1 - op2;
                break;
            case '/':
                if (op2 == 0) {
                    serverSocket.close();
                    throw new ArithmeticException("Division by zero!");
                }
                result = op1 / op2;
                break;
            case '*':
                result = op1 * op2;
                break;
            default:
                serverSocket.close();
                throw new IllegalArgumentException("Invalid operator!");
        }
        System.out.println("Result: " + result);
        out.println("Result: " + result);
        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}
