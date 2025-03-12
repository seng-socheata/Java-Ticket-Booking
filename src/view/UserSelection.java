package view;

import org.fusesource.jansi.Ansi;
import view.MovieList.DisplayMovie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class UserSelection {
    public static String selectedMovie = "";
    public static String selectedDate = "";
    public static String selectedLocation = "";
    public static String selectedTime = "";
    public static String assignedHall = "";
    public static ArrayList<String> bookedSeats = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in); // ✅ Define scanner once




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
        // Ask if the user wants to proceed to payment
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n💳 Do you want to proceed to payment? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            processPayment();
        } else if (response.equals("no")) {
            System.out.println("\n❌ Booking Canceled. Have a great day!🌷");
            cancelBooking();

        }
    }


    public static void cancelBooking() {
//        System.out.println("\n❌ Booking Canceled. Resetting your selection...");

        // Reset selections
        selectedMovie = "";
        selectedDate = "";
        selectedLocation = "";
        selectedTime = "";
        assignedHall = "";
        bookedSeats.clear();

        System.out.println("✅ Your booking has been successfully canceled.");
        System.out.print("\n🔄 Do you want to book again? (yes/no): ");

        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes")) {
            DisplayMovie.showMovies(false);
            SeatHall.resetSeating();  // Reset seating before booking
            SeatHall.displaySeating();
            SeatHall.bookSeats();
            SeatHall.printReceipt();
        } else {
            System.out.println("👋 Thank you! Have a great day!");
        }
    }

    public static void processPayment() {
        Scanner scanner = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        double discount = 0;

        // Special discount for today
        if (today.getDayOfMonth() == 18 && today.getMonthValue() == 2) {
            discount = 15; // 15% discount
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
        System.out.println("\n========================================");
        System.out.println("           🎟️ BOOKING RECEIPT         ");
        System.out.println("==========================================");
        System.out.printf("🎬 Movie:     %s\n", UserSelection.selectedMovie);
        System.out.printf("📅 Date:      %s\n", UserSelection.selectedDate);
        System.out.printf("📍 Location:  %s\n", UserSelection.selectedLocation);
        System.out.printf("🕒 Time:      %s\n", UserSelection.selectedTime);
        System.out.printf("🏛 Hall:      %s\n", UserSelection.assignedHall);
        System.out.printf("💺 Seats:     %s\n", UserSelection.bookedSeats.isEmpty() ? "No seats selected" : UserSelection.bookedSeats);
        System.out.println("-----------------------------------------");
        System.out.printf("🎟 Total Tickets: %d\n", totalTickets);
        System.out.printf("🎟 Regular Seats: %d x $4 = $%.2f\n", regularSeats, regularSeats * regularSeatPrice);
        System.out.printf("🎟 VIP Seats:     %d x $10 = $%.2f\n", vipSeats, vipSeats * vipSeatPrice);
        System.out.println("-----------------------------------------");
        System.out.printf("💰 Total Price: $%.2f\n", totalPrice);

        if (discount > 0) {
            System.out.printf("🔥 Discount:   %d%% (-$%.2f)\n", (int) discount, discountAmount);
            System.out.printf("💲 Final Price: $%.2f\n", finalPrice);
        }

        System.out.println("=========================================");
        System.out.println("🎉 Payment successful! Enjoy your movie! 🍿");
        System.out.println("=========================================\n");


    }
}



