
package User.BookingSeat.PaymentProcessor;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.ansi;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println(ansi().fgBrightYellow().a("💳 Processing credit card payment of $" + amount).reset());
        return true; // Simulating success
    }
}

class ABABankPayment implements PaymentMethod {
    private String accountNumber;

    public ABABankPayment(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println(ansi().fgBrightBlue().a("🏦 Processing ABA Bank payment of $" + amount + " for account " + accountNumber).reset());
        return true; // Simulating success
    }
}

class Promotion {
    private static final Map<String, Double> promoCodes = new HashMap<>();

    static {
        promoCodes.put("SONITA", 1.5);
        promoCodes.put("SOCHEATA", 2.0);
    }

    public static void displayPromoTable() {
        System.out.println(ansi().fgCyan().a("\n📢 Available Promo Codes 📢").reset());

        // Table border with color
        System.out.println(ansi().fg(Ansi.Color.RED).a("╔═══════════════════╦═══════════════════╗").reset());
        System.out.println(ansi().fg(Ansi.Color.RED).a("║ Promo Code        ║ Discount %        ║").reset());
        System.out.println(ansi().fg(Ansi.Color.RED).a("╠═══════════════════╬═══════════════════╣").reset());

        // Table content
        for (Map.Entry<String, Double> entry : promoCodes.entrySet()) {
            System.out.printf(String.valueOf(ansi().fg(Ansi.Color.GREEN).a("║ %-17s ║ %-17.1f ║\n").reset()), entry.getKey(), entry.getValue());
        }

        // Closing border line with color
        System.out.println(ansi().fg(Ansi.Color.RED).a("╚═══════════════════╩═══════════════════╝\n").reset());
    }

    public static double applyPromoCode(String promoCode, double price) {
        if (promoCodes.containsKey(promoCode)) {
            double discount = promoCodes.get(promoCode);
            double discountedPrice = price - (price * discount / 100);
            System.out.println(ansi().fgBrightGreen().a("🎉 Promo applied! " + discount + "% off. Final price: $" + discountedPrice).reset());
            return discountedPrice;
        } else {
            System.out.println(ansi().fgRed().a("❌ Invalid promo code. No discount applied.").reset());
            return price;
        }
    }
}

public class PaymentProcessor {
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        Scanner scanner = new Scanner(System.in);

        // Display available promo codes in a table
        Promotion.displayPromoTable();

        // Step 1: Enter Ticket Price
        System.out.print(ansi().fgBrightBlue().a("Enter ticket price: $").reset());
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Step 2: Apply Promo Code
        System.out.print(ansi().fgBrightYellow().a("Enter promo code (or press Enter to skip): ").reset());
        String promoCode = scanner.nextLine().toUpperCase();
        double finalPrice = Promotion.applyPromoCode(promoCode, price);

// Step 3: Choose Payment Method with a table
        System.out.println(ansi().fgMagenta().a("\n💰 Choose Payment Method 💰").reset());

        // Table border with color
        System.out.println(ansi().fg(Ansi.Color.GREEN).a("╔════╦════════════════╦════════════════════════════════╗").reset());
        System.out.println(ansi().fg(Ansi.Color.GREEN).a("║ #  ║ Method         ║ Description                    ║").reset());
        System.out.println(ansi().fg(Ansi.Color.GREEN).a("╠════╬════════════════╬════════════════════════════════╣").reset());

        // Table content with color
        System.out.println(ansi().fgYellow().a("║ 1️⃣ ║ Credit Card    ║ Fast and secure payments       ║").reset());
        System.out.println(ansi().fgBlue().a("║ 2️⃣ ║ ABA Bank       ║ Payment via bank account       ║").reset());

        // Closing border line with color
        System.out.println(ansi().fg(Ansi.Color.GREEN).a("╚════╩════════════════╩════════════════════════════════╝").reset());

        System.out.print(ansi().fgBrightBlue().a("Enter your choice: ").reset());
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        PaymentMethod payment;
        switch (choice) {
            case 1:
                System.out.print(ansi().fgBrightBlue().a("Enter Credit Card Number: ").reset());
                String cardNumber = scanner.nextLine();
                payment = new CreditCardPayment(cardNumber);
                break;
            case 2:
                System.out.print(ansi().fgBrightBlue().a("Enter ABA Bank Account Number: ").reset());
                String accountNumber = scanner.nextLine();
                payment = new ABABankPayment(accountNumber);
                break;
            default:
                System.out.println(ansi().fgRed().a("❌ Invalid choice. Exiting.").reset());
                scanner.close();
                return;
        }

        // Step 4: Process Payment
        boolean success = payment.processPayment(finalPrice);
        if (success) {
            System.out.println(ansi().fgBrightGreen().a("✅ Payment Successful! Enjoy your movie 🎬").reset());
        } else {
            System.out.println(ansi().fgRed().a("❌ Payment Failed. Please try again.").reset());
        }

        scanner.close();
        AnsiConsole.systemUninstall();
    }
}