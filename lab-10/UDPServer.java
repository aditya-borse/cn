import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(9876);
            byte [] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            String fileName = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("File requested by client: " + fileName);

            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File not found.");
                serverSocket.close();
                return;
            }
            
            FileInputStream in = new FileInputStream(file);
            byte [] fileBytes = new byte[(int) file.length()];
            in.read(fileBytes);

            DatagramPacket sendPacket = new DatagramPacket(fileBytes, fileBytes.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);

            System.out.println("File sent to the client.");
            in.close();
            serverSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}