package Users;

import Users.MovieList.DisplayMovie;
import org.fusesource.jansi.Ansi;

import java.sql.SQLException;
import java.util.ArrayList;

import static Users.SeatHall.addBookingToDB;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import java.time.LocalDate;
import java.util.Scanner;
import MVC.Config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
        selectedMovie = "";
        selectedDate = "";
        selectedLocation = "";
        selectedTime = "";
        assignedHall = "";
        bookedSeats.clear();
        // Cancel the booking in the database
        cancelBookingInDB();

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
    public static void cancelBookingInDB() {
        // If no movie has been selected, there's nothing to cancel
        if (selectedMovie.isEmpty()) {
            System.out.println("❌ No booking to cancel.");
            return;
        }

        String sql = "DELETE FROM booking WHERE movie_name = ? AND seat_label IN (?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Join the bookedSeats list into a comma-separated string
            String seatsToCancel = String.join(",", bookedSeats);

            stmt.setString(1, selectedMovie); // Set the movie name
            stmt.setString(2, seatsToCancel);  // Set the seats to cancel (comma-separated)

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Booking successfully canceled in the database.");
            } else {
                System.out.println("❌ No bookings were found to cancel.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
            e.printStackTrace();
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
            System.out.print("🔢 Enter your promotion code: ");
            String promoCode = scanner.nextLine().trim();

            if (promoCode.equalsIgnoreCase("BOOK50")) {
                discount = Math.max(discount, 5);
                System.out.println("✅ Promotion code applied! 10% discount.");
            } else if (promoCode.equalsIgnoreCase("MOVIE20")) {
                discount = Math.max(discount, 10);
                System.out.println("✅ Promotion code applied! 20% discount.");
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
        System.out.println("Regular Seats: " + regularSeats);
        System.out.println("VIP Seats: " + vipSeats);
        int totalTickets = regularSeats + vipSeats;
        double totalPrice = (regularSeats * regularSeatPrice) + (vipSeats * vipSeatPrice);
        double discountAmount = totalPrice * (discount / 100);
        double finalPrice = totalPrice - discountAmount;

   String boxTopBottom = "╔════════════════════════════════════════════════════════════════════╗";
        String boxMiddle = "║                                                                    ";

        // Display receipt
       System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a(boxTopBottom));
       System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("║                🎟️ BOOKING RECEIPT                               "));
       System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a(boxTopBottom));
       System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("║ 🎬 Movie:     " + selectedMovie + "                              "));
       System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("║ 📅 Date:      " + selectedDate + "                               "));
       System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("║ 📍 Location:  " + selectedLocation + "                             "));
       System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("║ 🕒 Time:      " + selectedTime + "                               "));
       System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("║ 🏛 Hall:      " + assignedHall + "                               "));
       System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("║ 💺 Seats:     " + (bookedSeats.isEmpty() ? "No seats selected" : bookedSeats) ));
       System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("╠════════════════════════════════════════════════════════════════════╣"));
       System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("║ 🎟 Total Tickets: " + totalTickets + "                             "));
       System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("║ 🎟 Regular Seats: " + regularSeats + " x $4 = $" + (regularSeats * regularSeatPrice) ));
       System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("║ 🎟 VIP Seats:     " + vipSeats + " x $10 = $" + (vipSeats * vipSeatPrice)));
       System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("╠════════════════════════════════════════════════════════════════════╣"));
       System.out.println(Ansi.ansi().fg(Ansi.Color.MAGENTA).a("║ 💰 Total Price: $"+ String.format("%.2f", totalPrice) + "                "));

        if (discount > 0) {
            System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("║ 🔥 Discount:   " + (int)discount + "% (-$" + String.format("%.2f", discountAmount) + ")  "));
            System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("║ 💲 Final Price: $" + String.format("%.2f", finalPrice) + "                "));
        }

        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a("╚════════════════════════════════════════════════════════════════════╝"));

        // Call addPaymentToDB method to insert payment details into the database
        addPaymentToDB(selectedMovie, totalTickets, regularSeats, vipSeats, regularSeatPrice, vipSeatPrice,
                totalPrice, discount, discountAmount, finalPrice);

        String topBorder = "╔════════════════════════════════════════════════════════════════════╗";
        String bottomBorder = "╚════════════════════════════════════════════════════════════════════╝";

        // Displaying payment success message with design
        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a(topBorder));
        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("║                                                                    ║"));
        System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("║ 🎉 Payment successful! Enjoy your movie! 🍿                         ║"));
        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("║                                                                    ║"));
        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a(bottomBorder));


    }

    public static void addPaymentToDB(String movieName, int totalTickets, int regularSeats, int vipSeats,
                                      double regularSeatPrice, double vipSeatPrice, double totalPrice,
                                      double discount, double discountAmount, double finalPrice) {
        String sql = "INSERT INTO payment (movie_name, total_tickets, regular_seats, vip_seats, " +
                "regular_price, vip_price, total_price, discount, discount_amount, final_price, payment_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movieName);
            stmt.setInt(2, totalTickets);
            stmt.setInt(3, regularSeats);
            stmt.setInt(4, vipSeats);
            stmt.setDouble(5, regularSeatPrice);
            stmt.setDouble(6, vipSeatPrice);
            stmt.setDouble(7, totalPrice);
            stmt.setDouble(8, discount);
            stmt.setDouble(9, discountAmount);
            stmt.setDouble(10, finalPrice);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Payment details have been successfully recorded in the database.");
            } else {
                System.out.println("❌ Failed to record payment details in the database.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }


}

