package view;

import java.util.ArrayList;
import java.util.Scanner;

public class SeatHall {
    private static final int rows = 10;
    private static final int cols = 10;
    private static String[][] seats = new String[rows + 1][cols]; // +1 for VIP row
    private static ArrayList<String> bookedSeats = new ArrayList<>();

    private static final String RESET = "\u001B[0m";
    private static final String VIP_COLOR = "\u001B[32m";
    public static final String REGULAR_COLOR = "\u001B[34m";


    public SeatHall() {
        initializeSeats();
    }


    private void initializeSeats() {
        char rowLabel = 'J';
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = rowLabel + String.valueOf(j + 1) + "-AV"; // AV = Available
            }
            rowLabel--;
        }

        // Fill VIP seats in pairs (VIP1-VIP2, VIP3-VIP4, etc.)
        for (int j = 0; j < cols; j += 2) {
            seats[rows][j] = VIP_COLOR + "VIP" + (j + 1) + "-" + "VIP" + (j + 2) + RESET;
            seats[rows][j + 1] = ""; // Empty, since it's part of the pair
        }
    }

    // Reset all seats to available after a cancellation or reset
    public static void resetSeating() {
        // Reset all seats to available
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = (char) ('J' - i) + String.valueOf(j + 1) + "-AV"; // Reset to "Available"
            }
        }

        // Reset VIP seats to available as well
        for (int j = 0; j < cols; j += 2) {
            seats[rows][j] = VIP_COLOR + "VIP" + (j + 1) + "-" + "VIP" + (j + 2) + RESET;
            seats[rows][j + 1] = ""; // Empty, since it's part of the pair
        }

        // Clear the list of booked seats
        bookedSeats.clear();
        UserSelection.bookedSeats.clear();
    }

    public static void displaySeating() {
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                     Welcome to the Cinema!                             ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                                            Screen                                      ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════╣");

        for (int i = 0; i < rows; i++) {
            System.out.print("║  " + (char) ('J' - i) + "   ║ ");
            for (int j = 0; j < cols; j++) {
                String seat = seats[i][j];
                String displaySeat = REGULAR_COLOR + seat + RESET;
                System.out.printf(" %-11s ║", displaySeat); // Uniform width for seats
            }
            System.out.println();
            System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════╣");
        }

        // Display VIP row
        System.out.print("║ VIP  ║ ");
        for (int j = 0; j < cols; j += 2) { // Pairing VIP seats
            if (!seats[rows][j].isEmpty()) {
                String vipPair = "💜 " + seats[rows][j] + (j + 1 < cols && !seats[rows][j + 1].isEmpty() ? "-💜 " + seats[rows][j + 1] : "");
                System.out.printf(" %-20s  ║", vipPair); // Wider space for VIP pairs
            }
        }
        System.out.println();

        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void bookSeats() {
        Scanner scanner = new Scanner(System.in);
        boolean bookingMore = true;

        while (bookingMore) {
            System.out.print("Enter row (VIP-A) -> ");
            String rowInput = scanner.next().toUpperCase();

            System.out.print("Enter column -> ");
            int colInput = scanner.nextInt() - 1;

            if (rowInput.equals("VIP")) {
                System.out.print("Enter column (first seat number (e.g. VIP1, VIP3,)-> ");
                if (colInput % 2 == 0 && colInput < cols - 1) {
                    if (seats[rows][colInput].contains("BK")) {
                        System.out.println("❌ This VIP pair is already booked!");
                    } else {
                        seats[rows][colInput] = "VIP" + (colInput + 1) + "-VIP" + (colInput + 2) + "-BK";
                        String vipSeatPair = "VIP" + (colInput + 1) + "-VIP" + (colInput + 2);
                        bookedSeats.add(vipSeatPair);
                        UserSelection.bookedSeats.add(vipSeatPair); // ✅ FIX: Add VIP seats to receipt list

                        System.out.println("✅ VIP seats " + vipSeatPair + " booked successfully!");
                    }
                } else {
                    System.out.println("⚠️ Please enter the first seat number in the VIP pair (e.g., VIP1, VIP3, etc.).");
                }
//
            } else {
                int rowIndex = 'J' - rowInput.charAt(0);
                if (rowIndex >= 0 && rowIndex < rows && colInput >= 0 && colInput < cols) {
                    if (seats[rowIndex][colInput].contains("BK")) {
                        System.out.println("❌ This seat is already booked!");
                    } else {
                        seats[rowIndex][colInput] = rowInput + (colInput + 1) + "-BK";
                        bookedSeats.add(rowInput + (colInput + 1));
                        System.out.println("✅ Seat " + rowInput + (colInput + 1) + " booked successfully!");
                    }
                } else {
                    System.out.println("⚠️ Invalid seat selection. Please try again.");
                }
                UserSelection.bookedSeats.add(rowInput + (colInput+1));
            }

            System.out.print("Do you want to book more seats? (YES/NO)-> ");
            bookingMore = scanner.next().equalsIgnoreCase("YES");
        }


    }


    public static void printReceipt() {

        UserSelection.displaySummary();

    }
}



