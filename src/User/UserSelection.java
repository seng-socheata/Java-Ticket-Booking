package User;

import User.MovieList.DisplayMovie;
import User.SeatHall;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.fusesource.jansi.Ansi;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;

import static User.SeatHall.addBookingToDB;
import static jdk.internal.org.jline.utils.AttributedStyle.CYAN;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;
import org.fusesource.jansi.Ansi;
import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import MVC.Config.Database;

import javax.swing.*;
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

    String boxMiddle = "                ║";
    public static void displaySummary() {
//        System.out.println(ansi().fg(Ansi.Color.YELLOW).a("╔═════════════════════════════════════════════╗"));
//        System.out.println(ansi().fg(YELLOW).a("║         🎟 BOOKING CONFIRMATION 🎟          ║").reset());
//        System.out.println(ansi().fg(Ansi.Color.YELLOW).a("╠═════════════════════════════════════════════╣"));
//        System.out.println(ansi().fg(Ansi.Color.GREEN).a("║ 🎬 Movie    :     " + selectedMovie + "                    ║"));
//        System.out.println(ansi().fg(Ansi.Color.GREEN).a("║ 📅 Date     :      " + selectedDate + "               ║"));
//        System.out.println(ansi().fg(Ansi.Color.GREEN).a("║ 📍 Location :  " + selectedLocation + "                    ║"));
//        System.out.println(ansi().fg(Ansi.Color.GREEN).a("║ 🕒 Time     :      " + selectedTime + "                  ║"));
//        System.out.println(ansi().fg(Ansi.Color.GREEN).a("║ 🏛 Hall     :      " + assignedHall + "                   ║"));
//        System.out.println(ansi().fg(Ansi.Color.GREEN).a("║ 💺 Seats    :     " + (bookedSeats.isEmpty() ? "No seats selected" : bookedSeats)+"                      ║" ));
//        System.out.println(ansi().fg(Ansi.Color.CYAN).a("╚═════════════════════════════════════════════╝"));
        String[] lines = {
                "🎬 Movie: " + selectedMovie,
                "🎭 Hall: " + assignedHall,
                "📅 Date: " + selectedDate,
                "📍 Location: " + selectedLocation,
                "🕒 Time: " + selectedTime,
                "💺 Seats: "+ (bookedSeats.isEmpty() ? "No seats selected" : bookedSeats)
        };

        // Find the maximum line length (for box sizing)
        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length());
        }


        int boxWidth = maxLength + 36;

        String boxTopBottom = "╔" + "═".repeat(boxWidth - 2) + "╗";
        String boxBottom = "╚" + "═".repeat(boxWidth - 2) + "╝";

        // Print the top border
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a(boxTopBottom));
        System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("║               🎟 BOOKING CONFIRMATION 🎟               ║"));
        System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("╠" + "═".repeat(boxWidth - 2) + "╣"));

        for (String line : lines) {
            // Calculate spaces for padding based on the max width
            String paddedLine = "║ " + line + " ".repeat(boxWidth - 4 - line.length()) + " ║";
            System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a(paddedLine));
        }

        // Print the bottom border
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a(boxBottom));



        System.out.println(ansi().fg(YELLOW).a("🎉 Thank you for booking with us! Enjoy your movie. 🍿").reset());

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

        cancelBookingInDB();

        System.out.println("✅ Your booking has been successfully canceled.");
        System.out.print("\n🔄 Do you want to book again? (yes/no): ");

        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes")) {
            DisplayMovie.showMovies(false);
            SeatHall.resetSeating();
            SeatHall.displaySeating();
            SeatHall.bookSeats();
            SeatHall.printReceipt();
        } else {
            System.out.println("👋 Thank you! Have a great day!");
        }
    }
    public static void cancelBookingInDB() {

        if (selectedMovie.isEmpty()) {
            System.out.println("❌ No booking to cancel.");
            return;
        }

        String sql = "DELETE FROM booking WHERE movie_name = ? AND seat_label IN (?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String seatsToCancel = String.join(",", bookedSeats);

            stmt.setString(1, selectedMovie);
            stmt.setString(2, seatsToCancel);

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


        if (today.getDayOfMonth() == 18 && today.getMonthValue() == 2) {
            discount = 15; // 15% discount
            System.out.println("\n🎉 Special Offer: 15% Discount Today!");
        }

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




        String[] lines = {
                "🎬 Movie:     " + selectedMovie,
                "📅 Date:      " + selectedDate,
                "📍 Location:  " + selectedLocation,
                "🕒 Time:      " + selectedTime,
                "🏛 Hall:      " + assignedHall,
                "💺 Seats:     " + (bookedSeats.isEmpty() ? "No seats selected" : String.join(", ", bookedSeats)),
                "────────────────────────────────────────────────────────", // Separator line
                "🎟 Total Tickets: " + totalTickets,
                "🎟 Regular Seats: " + regularSeats + " x $4 = $" + (regularSeats * regularSeatPrice),
                "🎟 VIP Seats:     " + vipSeats + " x $10 = $" + (vipSeats * vipSeatPrice),
                "────────────────────────────────────────────────────────", // New separator before Total Price
                "💰 Total Price: $" + String.format("%.2f", totalPrice),
        };

        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length());
        }

        int boxWidth = maxLength + 36;
        String boxTopBottom = "╔" + "═".repeat(boxWidth - 2) + "╗";
        String boxBottom = "╚" + "═".repeat(boxWidth - 2) + "╝";
        String separator = "╠" + "═".repeat(boxWidth - 2) + "╣"; // Creates a separator line

        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a(boxTopBottom));
        System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("║                                    🎟 BOOKING RECEIPT 🎟                                 ║"));
        System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a(separator));

        for (String line : lines) {
            if (line.startsWith("───")) {  // Check for separator lines
                System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a(separator));
            } else {
                String paddedLine = "║ " + line + " ".repeat(boxWidth - 4 - line.length()) + " ║";
                System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a(paddedLine));
            }
        }

        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN).a(boxBottom));


        addPaymentToDB(selectedMovie, totalTickets, regularSeats, vipSeats, regularSeatPrice, vipSeatPrice,
                totalPrice, discount, discountAmount, finalPrice);

        System.out.print(" Do you want to pay by scanning the QR code? (yes): ");
        String paymentChoice = scanner.nextLine().trim().toLowerCase();
        if (paymentChoice.equals("yes")) {
            showQRCode();

            String topBorder = "╔══════════════════════════════════════════════════════════════════════════════════════════╗";
            String bottomBorder = "╚══════════════════════════════════════════════════════════════════════════════════════════╝";

            System.out.println(ansi().fg(Ansi.Color.GREEN).a(topBorder));
            System.out.println(ansi().fg(Ansi.Color.GREEN).a("║                                                                                          ║"));
            System.out.println(ansi().fg(Ansi.Color.YELLOW).a("║                             🎉Payment successful! Enjoy your movie!🍿                    ║"));
            System.out.println(ansi().fg(Ansi.Color.GREEN).a("║                                                                                          ║"));
            System.out.println(ansi().fg(Ansi.Color.GREEN).a(bottomBorder));
        }
        else {
            System.out.println(ansi().fg(Ansi.Color.RED).a("⚠️ Your booking is pending payment. Make sure to pay before your movie time!"));
        }
    }
    public static void showQRCode() {
        String qrData = "00020101021229200016hor_vanlida@aclb520459995802KH5911Hor Vanlida6010Phnom Penh991700131741360775515541100000000000530384062460109TRX12345602090772122530306Cinema0706POS-0263049412";  // The data you want to encode

        try {

            BitMatrix bitMatrix = generateQRCodeMatrix(qrData, 300, 300);
            BufferedImage qrCodeImage = convertToImage(bitMatrix);
            displayQRCodeInFrame(qrCodeImage);
        } catch (WriterException e) {
            System.out.println("Error generating QR code: " + e.getMessage());
        }
    }

    private static BitMatrix generateQRCodeMatrix(String data, int width, int height) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        return qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hintMap);
    }

    private static BufferedImage convertToImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        return bufferedImage;
    }

    private static void displayQRCodeInFrame(BufferedImage qrCodeImage) {

        JFrame mainFrame = new JFrame("Scan Here");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setSize(400, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ImageIcon qrCodeIcon = new ImageIcon(qrCodeImage);


        JLabel qrCodeLabel = new JLabel(qrCodeIcon);

        // Add the QR code JLabel to the JFrame
        mainFrame.add(qrCodeLabel);

        // Make the JFrame visible
        mainFrame.setVisible(true);
    }

    private static BufferedImage generateQRCodeImage(String data, int width, int height) throws WriterException {
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);  // Medium error correction

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hintMap);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF; // Black or White
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        return bufferedImage;
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

