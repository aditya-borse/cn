import java.util.Scanner;

public class SubnetCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an IP address: ");
        String ipAddress = sc.nextLine();
        System.out.print("Number of subnets required: ");
        int subnets = sc.nextInt();
        calculateSubnets(ipAddress, subnets);
        sc.close();
    }

    public static void calculateSubnets(String ipAddress, int subnets) {
        int [] ipOctets = new int[4];
        String [] octets = ipAddress.split("\\.");
        for (int i = 0; i < 4; i++) {
            ipOctets[i] = Integer.parseInt(octets[i]);
        }

        int numbits = (int) Math.ceil(Math.log(subnets) / Math.log(2));
        int borrowedBits = 32 - numbits;
        int [] subnetMask = new int[4];
        for (int i = 0; i < 4; i++) {
            if (borrowedBits >= 8) {
                subnetMask[i] = 255;
                borrowedBits -= 8;
            }
            else {
                subnetMask[i] = (int) (Math.pow(2, numbits) - 1) << (8 - numbits);
                borrowedBits = 0;
            }
        }

        System.out.println("Subnet Masks: ");
        for (int i = 0; i < subnets; i++) {
            System.out.println("Subnet " + (i + 1) + ": " + ipOctets[0] + "." + ipOctets[1] + "." + ipOctets[2] + "." + (ipOctets[3] + i * (int) (Math.pow(2, 8 - numbits))) + " / " + subnetMask[0] + "." + subnetMask[1] + "." + subnetMask[2] + "." + subnetMask[3]);
        }
    }
}
