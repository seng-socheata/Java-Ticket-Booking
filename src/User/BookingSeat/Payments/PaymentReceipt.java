package User.BookingSeat.Payments;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PaymentReceipt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Payment Amount: $");
        double amount = scanner.nextDouble();

        System.out.print("Enter Payment Method (Cash/Card): ");
        scanner.nextLine(); // Consume newline
        String paymentMethod = scanner.nextLine();

        String receipt = generateReceipt(customerName, amount, paymentMethod);
        System.out.println(receipt);

        scanner.close();
    }

    public static String generateReceipt(String customerName, double amount, String paymentMethod) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return "\n========== PAYMENT RECEIPT ==========" +
                "\nDate: " + formatter.format(date) +
                "\nCustomer: " + customerName +
                "\nAmount Paid: $" + String.format("%.2f", amount) +
                "\nPayment Method: " + paymentMethod +
                "\n====================================\n";
    }
}
