package User.BookingSeat;

import java.util.Scanner;

public class Payment {
    public static void printPaymentReceipt(String paymentId, String paymentMethod, String transactionId, String paymentStatus, double amount) {
        System.out.println("\n============================================================");
        System.out.println("                        Payment Details                     ");
        System.out.println("============================================================");
        System.out.printf("| %-15s | %-30s |\n", "Payment ID", paymentId);
        System.out.printf("| %-15s | %-30s |\n", "Method", paymentMethod);
        System.out.printf("| %-15s | %-30s |\n", "Transaction ID", transactionId);
        System.out.printf("| %-15s | %-30s |\n", "Status", paymentStatus);
        System.out.printf("| %-15s | $%-29.2f |\n", "Amount", amount);
        System.out.println("============================================================");
        System.out.println("🎉 Thank you for your payment! Enjoy your movie.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for payment details
        System.out.print("Enter Payment ID: ");
        String paymentId = scanner.nextLine();

        System.out.print("Enter Payment Method: ");
        String paymentMethod = scanner.nextLine();

        System.out.print("Enter Transaction ID: ");
        String transactionId = scanner.nextLine();

        System.out.print("Enter Payment Status: ");
        String paymentStatus = scanner.nextLine();

        System.out.print("Enter Payment Amount: ");
        double amount = scanner.nextDouble();

        // Print the receipt
        printPaymentReceipt(paymentId, paymentMethod, transactionId, paymentStatus, amount);

        scanner.close();
    }
}
