package User;
import org.fusesource.jansi.Ansi;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.*;

public class UserSelection {
    public static String selectedMovie = "";
    public static String selectedDate = "";
    public static String selectedLocation = "";
    public static String selectedTime = "";
    public static String assignedHall = "";
    public static ArrayList<String> bookedSeats = new ArrayList<>();

//    public static void displaySummary() {
//        System.out.println(Ansi.ansi().fg(GREEN).a("\n╔═══════════════════════════════════════════════════╗").reset());
//        System.out.println(Ansi.ansi().fg(YELLOW).a("║             CONFIRMATION                          ║").reset());
//        System.out.println(Ansi.ansi().fg(GREEN).a("╚═══════════════════════════════════════════════════╝").reset());
//        System.out.println("🎬 Movie: " + selectedMovie);
//        System.out.println("📅 Date: " + selectedDate);
//        System.out.println("📍 Location: " + selectedLocation);
//        System.out.println("🕒 Time: " + selectedTime);
//        System.out.println("🏛 Hall: " + assignedHall);
//        System.out.println("💺 Seats: " + (bookedSeats.isEmpty() ? "No seats selected" : bookedSeats));
//        System.out.println("🎉 Thank you for booking with us! Enjoy your movie.");
//        // Ask if the user wants to proceed to payment
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("\n💳 Do you want to proceed to payment? (yes/no): ");
//        String response = scanner.nextLine().trim().toLowerCase();
//
//        if (response.equals("yes")) {
//            processPayment();
//        } else {
//            System.out.println("\n❌ Booking Canceled. Have a great day!🌷");
//        }
//    }


    public static void displaySummary() {
        System.out.println(Ansi.ansi().fg(GREEN).a("\n╔═══════════════════════════════════════════════════╗").reset());
        System.out.println(Ansi.ansi().fg(YELLOW).a("║             CONFIRMATION                          ║").reset());
        System.out.println(Ansi.ansi().fg(GREEN).a("╚═══════════════════════════════════════════════════╝").reset());
        System.out.println("🎬 Movie: " + selectedMovie);
        System.out.println("📅 Date: " + selectedDate);
        System.out.println("📍 Location: " + selectedLocation);
        System.out.println("🕒 Time: " + selectedTime);
        System.out.println("🏛 Hall: " + assignedHall);
        System.out.println("💺 Seats: " + (bookedSeats.isEmpty() ? "No seats selected" : bookedSeats));
        System.out.println("🎉 Thank you for booking with us! Enjoy your movie.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n💳 Do you want to proceed to payment? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            processPayment();
        } else {
            System.out.print("\n❌ Booking Canceled. Do you want to book again? (yes/no): ");
            String retryResponse = scanner.nextLine().trim().toLowerCase();

            if (retryResponse.equals("yes")) {
                bookedSeats.clear(); // Clear previous booked seats
                System.out.println("\n🔄 Restarting booking process...");

                // Restart the booking process
                SeatHall.displaySeating();
                SeatHall.bookSeats();
                SeatHall.printReceipt();
            } else {
                System.out.println("\n👋 Have a great day! 🌷");
            }

        }
    }

    public static void processPayment() {
        Scanner scanner = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        double discount = 0;

        // Special discount for today
        if (today.getDayOfMonth() == 18 && today.getMonthValue() == 2) {
            discount = 15;
            System.out.println("\n🎉 Special Offer: 15% Discount Today!");
        }

        // Ask for a promo code
        System.out.print("\n🎟️ Do you have a promo code? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            System.out.print("🔢 Enter your promo code: ");
            String promoCode = scanner.nextLine().trim();

            if (promoCode.equalsIgnoreCase("BOOK50")) {
                discount = Math.max(discount, 10);
                System.out.println("✅ Promo code applied! 10% discount.");
            } else if (promoCode.equalsIgnoreCase("MOVIE20")) {
                discount = Math.max(discount, 20);
                System.out.println("✅ Promo code applied! 20% discount.");
            } else {
                System.out.println("❌ Invalid promo code.");
            }
        }

        // Pricing details
        double regularSeatPrice = 4.0;
        double vipSeatPrice = 10.0;
        int regularSeats = 0;
        int vipSeats = 0;

        System.out.println("Booked Seats: " + bookedSeats);

        for (String seat : UserSelection.bookedSeats) {
            if (seat.matches("^[A-J]\\d+")) {
                regularSeats++;
            } else if (seat.startsWith("V")) {
                vipSeats++;
            }
        }
        // Debugging output
        System.out.println("Regular Seats: " + regularSeats);
        System.out.println("VIP Seats: " + vipSeats);

        int totalTickets = regularSeats + vipSeats;
        double totalPrice = (regularSeats * regularSeatPrice) + (vipSeats * vipSeatPrice);
        double discountAmount = totalPrice * (discount / 100);
        double finalPrice = totalPrice - discountAmount;

//        Display receipt
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║               🎟️ BOOKING RECEIPT               ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.printf("║ 🎬 Movie:     %-32s ║\n", UserSelection.selectedMovie);
        System.out.printf("║ 📅 Date:      %-32s ║\n", UserSelection.selectedDate);
        System.out.printf("║ 📍 Location:  %-32s ║\n", UserSelection.selectedLocation);
        System.out.printf("║ 🕒 Time:      %-32s ║\n", UserSelection.selectedTime);
        System.out.printf("║ 🏛 Hall:      %-32s ║\n", UserSelection.assignedHall);
        System.out.printf("║ 💺 Seats:     %-32s ║\n", UserSelection.bookedSeats.isEmpty() ? "No seats selected" : UserSelection.bookedSeats);
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.printf("║ 🎟 Total Tickets: %-29d ║\n", totalTickets);
        System.out.printf("║ 🎟 Regular Seats: %-2d x $4  = $%-10.2f ║\n", regularSeats, regularSeats * regularSeatPrice);
        System.out.printf("║ 🎟 VIP Seats:     %-2d x $10 = $%-10.2f ║\n", vipSeats, vipSeats * vipSeatPrice);
        System.out.println("╠══════════════════════════════════════════════════╣");

        if (discount > 0) {  // If discount exists, show receipt with discount
            System.out.printf("║ 💰 Total Price: $%-33.2f ║\n", totalPrice);
            System.out.printf("║ 🔥 Discount:   %-2d%%           ║\n", (int) discount);
            System.out.printf("║ 💲 Final Price: $%-32.2f ║\n", finalPrice);
        } else {  // If no discount, show normal receipt
            System.out.printf("║ 💲 Total Price: $%-32.2f ║\n", totalPrice);
        }

        System.out.println("╚══════════════════════════════════════════════════╝");




        System.out.println("======================================");
        System.out.println("🎉 Payment successful! Enjoy your movie! 🍿");
        System.out.println("======================================\n");


    }
}




