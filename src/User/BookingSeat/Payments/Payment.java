package User.BookingSeat.Payments;

import java.util.Scanner;
import java.util.UUID;

// Payment Interface
interface PaymentMethod {
    PaymentDetails processPayment(double amount);
}

// Class to hold payment details
class PaymentDetails {
    private String paymentId;
    private String paymentMethod;
    private String transactionId;
    String paymentStatus;
    private double amount;

    public PaymentDetails(String paymentMethod, String paymentStatus, double amount) {
        // Generate unique payment and transaction IDs
        this.paymentId = UUID.randomUUID().toString();
        this.transactionId = UUID.randomUUID().toString();
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
    }

    public void displayPaymentDetails() {
        System.out.println("\n============================================================");
        System.out.println("                        Payment Details                     ");
        System.out.println("============================================================");
        System.out.printf("| %-15s | %-30s |\n", "Payment ID", paymentId);
        System.out.printf("| %-15s | %-30s |\n", "Method", paymentMethod);
        System.out.printf("| %-15s | %-30s |\n", "Transaction ID", transactionId);
        System.out.printf("| %-15s | %-30s |\n", "Status", paymentStatus);
        System.out.printf("| %-15s | $%-29.2f |\n", "Amount", amount);
        System.out.println("============================================================");
    }
}

// Credit Card Payment
class CreditCardPayment implements PaymentMethod {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public PaymentDetails processPayment(double amount) {
        System.out.println("Processing Credit Card Payment of $" + amount);
        // Simulate processing...
        return new PaymentDetails("Credit Card", "Success", amount);
    }
}

// PayPal Payment
class PayPalPayment implements PaymentMethod {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public PaymentDetails processPayment(double amount) {
        System.out.println("Processing PayPal Payment of $" + amount);
        // Simulate processing...
        return new PaymentDetails("PayPal", "Success", amount);
    }
}

// Movie Ticket Class
class MovieTicket {
    private String movieName;
    private int quantity;
    private double pricePerTicket;

    public MovieTicket(String movieName, int quantity, double pricePerTicket) {
        this.movieName = movieName;
        this.quantity = quantity;
        this.pricePerTicket = pricePerTicket;
    }

    public double getTotalPrice() {
        return quantity * pricePerTicket;
    }

    public void displayTicket() {
        System.out.println("\n------------------------------------------------");
        System.out.printf("| %-20s | %-10s | %-10s |\n", "Movie", "Quantity", "Total Price");
        System.out.println("------------------------------------------------");
        System.out.printf("| %-20s | %-10d | $%-9.2f |\n", movieName, quantity, getTotalPrice());
        System.out.println("------------------------------------------------");
    }
}

// Main Booking System
public class Payment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { // Loop to allow multiple bookings
            // Movie Selection
            System.out.println("\nEnter movie name:");
            String movieName = scanner.nextLine();

            System.out.println("Enter number of tickets:");
            int quantity = scanner.nextInt();

            double pricePerTicket = 10.0; // Fixed price
            MovieTicket ticket = new MovieTicket(movieName, quantity, pricePerTicket);


            // Payment Method Selection
            System.out.println("Select Payment Method: 1. Credit Card  2. PayPal");
            int paymentChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            PaymentMethod paymentMethod;
            if (paymentChoice == 1) {
                System.out.println("Enter Credit Card Number:");
                String cardNumber = scanner.nextLine();
                paymentMethod = new CreditCardPayment(cardNumber);
            } else {
                System.out.println("Enter PayPal Email:");
                String email = scanner.nextLine();
                paymentMethod = new PayPalPayment(email);
            }

            // Processing Payment and obtaining payment details
            PaymentDetails details = paymentMethod.processPayment(ticket.getTotalPrice());

            // Display results if payment is successful
            if (details != null && details.paymentStatus.equals("Success")) {
                System.out.println("\n✅ Payment Successful! Booking Confirmed.");
                ticket.displayTicket();
                details.displayPaymentDetails();
            } else {
                System.out.println("❌ Payment Failed. Please try again.");
            }

            // Asking user if they want to book another ticket
            System.out.println("\nDo you want to book another ticket? (yes/no):");
            String choice = scanner.nextLine().toLowerCase();

            if (!choice.equals("yes")) {
                System.out.println("Thank you for using our booking system. Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}
