/* Q. Write a program for DNS lookup. Given an IP address as input, it should return URL and vice-versa. */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DNSLookup {
    public static String ipToHostname(String ipAddress) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            return inetAddress.getHostName();
        }
        catch (UnknownHostException e) {
            return "Couldn't resolve IP address to hostname";
        }
    }

    public static String hostnameToIp(String host) {
        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            return inetAddress.getHostAddress();
        }
        catch (UnknownHostException e) {
            return "Couldn't resolve hostname to ip address";
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Menu\n1. IP to Hostname\n2. Hostname to IP\n3. Exit\n");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.print("IP Address: ");
                String ipAddress = sc.nextLine();
                System.out.println("Hostname: " + ipToHostname(ipAddress));
            }
            else if (choice == 2) {
                System.out.print("Hostname: ");
                String hostname = sc.nextLine();
                System.out.println("IP Address: " + hostnameToIp(hostname));
            }
            else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }
            else {
                System.out.println("Invalid Choice");
            }
        }
        sc.close();
    }
}