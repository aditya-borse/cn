import java.util.Scanner;

public class Subnet {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter an IP address in this form (a.b.c.d / n): ");
        String ipAddress = sc.nextLine();
        String [] parts = ipAddress.split("/");
        int numbits = Integer.parseInt(parts[1]);
        int[] ipOctets = new int[4];
        String[] octets = parts[0].split("\\.");
        for (int i = 0; i < 4; i++) {
            ipOctets[i] = Integer.parseInt(octets[i]);
        }
        findIpClass(ipOctets[0]);
        findSubnetMask(ipOctets, numbits);
        sc.close();
    }

    public static void findIpClass(int firstOctet) {
        if (firstOctet >= 0 && firstOctet <= 127) {
            System.out.println("Class: A");
        }
        else if (firstOctet >= 128 && firstOctet <= 191) {
            System.out.println("Class: B");
        }
        else if (firstOctet >= 192 && firstOctet <= 223) {
            System.out.println("Class: C");
        }
        else if (firstOctet >= 224 && firstOctet <= 239) {
            System.out.println("Class: D");
        }
        else if (firstOctet >= 240 && firstOctet <= 255) {
            System.out.println("Class: E");
        }
        else {
            System.out.println("Invalid IP Address");
        }
    }

    public static void findSubnetMask(int[] ipOctets, int numbits) {
        int[] subnetMask = new int[4];
        for (int i = 0; i < 4; i++) {
            if (numbits >= 8) {
                subnetMask[i] = 255;
                numbits -= 8;
            }
            else {
                subnetMask[i] = (int) (Math.pow(2, numbits) - 1) << (8 - numbits);
                numbits = 0;
            }
        }

        System.out.println("Subnet Mask: " + subnetMask[0] + "." + subnetMask[1] + "." + subnetMask[2] + "." + subnetMask[3]);

        int[] networkAddress = new int[4];
        for (int i = 0; i < 4; i++) {
            networkAddress[i] = ipOctets[i] & subnetMask[i];
        }

        int[] broadcastAddress = new int[4];
        for (int i = 0; i < 4; i++) {
            broadcastAddress[i] = networkAddress[i] | ~subnetMask[i];
        }

        for (int i = 0; i < 4; i++) {
            if (broadcastAddress[i] < 0) {
                broadcastAddress[i] = 256 + broadcastAddress[i];
            }
        }

        System.out.println("Network Address: " + networkAddress[0] + "." + networkAddress[1] + "." + networkAddress[2] + "." + networkAddress[3]);
        System.out.println("Broadcast Address: " + broadcastAddress[0] + "." + broadcastAddress[1] + "." + broadcastAddress[2] + "." + broadcastAddress[3]);
    }
}
