package User;

import User.MovieList.DisplayMovie;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.fusesource.jansi.Ansi;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import static User.MovieList.DisplayMovie.showMovies;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        System.out.print(" Do you pay by scan QR? (yes/no): ");
        String paymentChoice = scanner.nextLine().trim().toLowerCase();

        if (paymentChoice.equals("yes")) {
            showQRCode();
        } else {
            System.out.println("You choose to pay in cash.");
        }

    }


    public static void showQRCode() {
        String qrData = "00020101021229200016hor_vanlida@aclb520459995802KH5911Hor Vanlida6010Phnom Penh991700131741360775515541100000000000530384062460109TRX12345602090772122530306Cinema0706POS-0263049412";  // The data you want to encode

        try {
            // Generate the QR code matrix
            BitMatrix bitMatrix = generateQRCodeMatrix(qrData, 200, 200);

            // Convert BitMatrix to BufferedImage
            BufferedImage qrCodeImage = convertToImage(bitMatrix);

            // Display the QR code in the existing JFrame
            displayQRCodeInFrame(qrCodeImage);
        } catch (WriterException e) {
            System.out.println("Error generating QR code: " + e.getMessage());
        }
    }



    private static BitMatrix generateQRCodeMatrix(String data, int width, int height) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);  // Medium error correction

        return qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hintMap);
    }

    private static BufferedImage convertToImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Set black or white pixels based on the bit matrix
                int rgb = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF; // Black or White
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        return bufferedImage;
    }

    private static void displayQRCodeInFrame(BufferedImage qrCodeImage) {
        // Assuming you already have a JFrame with a JPanel to display content
        JFrame mainFrame = new JFrame("Scan Here");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setSize(400, 400);  // Adjust the size to your needs
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Convert BufferedImage to ImageIcon for display in JLabel
        ImageIcon qrCodeIcon = new ImageIcon(qrCodeImage);

        // JLabel to display the QR code image
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
}




