import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket clientSocket = null;
        try {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            clientSocket = new DatagramSocket();
            byte [] sendData = "script.txt".getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
            clientSocket.send(sendPacket);

            byte [] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            clientSocket.receive(receivePacket);

            String filename = "received_" + new String(sendData);
            FileOutputStream out = new FileOutputStream(filename);
            out.write(receivePacket.getData(), 0, receivePacket.getLength());

            out.close();
            clientSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
