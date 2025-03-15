package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import MVC.Config.Database;


public class SeatHall {
    private static final int rows = 10;
    private static final int cols = 10;
    private static String[][] seats = new String[rows + 1][cols];
    private static ArrayList<String> bookedSeats = new ArrayList<>();
    private static final String RESET = "\u001B[0m";
    private static final String VIP_COLOR = "\u001B[32m";
    private static final String REGULAR_COLOR = "\u001B[34m";

    public SeatHall() {
        initializeSeats();
    }

    private void initializeSeats() {
        char rowLabel = 'J';
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = rowLabel + String.valueOf(j + 1) + "-AV";
            }
            rowLabel--;
        }
        for (int j = 0; j < cols; j += 2) {
            seats[rows][j] = VIP_COLOR + "VIP" + (j + 1) + "-" + "VIP" + (j + 2) + RESET;
            seats[rows][j + 1] = "";
        }
    }


    public static void resetSeating() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = (char) ('J' - i) + String.valueOf(j + 1) + "-AV";
            }
        }

        for (int j = 0; j < cols; j += 2) {
            seats[rows][j] = VIP_COLOR + "VIP" + (j + 1) + "-" + "VIP" + (j + 2) + RESET;
            seats[rows][j + 1] = "";
        }
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
                System.out.printf(" %-11s ║", displaySeat);
            }
            System.out.println();
            System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════╣");
        }

        // Display VIP row
        System.out.print("║ VIP  ║ ");
        for (int j = 0; j < cols; j += 2) {
            if (!seats[rows][j].isEmpty()) {
                String vipPair = "💜 " + seats[rows][j] + (j + 1 < cols && !seats[rows][j + 1].isEmpty() ? "-💜 " + seats[rows][j + 1] : "");
                System.out.printf(" %-20s  ║", vipPair);
            }
        }
        System.out.println();

        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════╝");
    }


    public static void bookSeats() {
        Scanner scanner = new Scanner(System.in);
        boolean bookingMore = true;

        while (bookingMore) {
            String rowInput;
            int colInput;

            while (true) {
                System.out.print("Enter row (VIP, A-J) -> ");
                rowInput = scanner.next().toUpperCase();

                if (rowInput.equals("VIP") || rowInput.matches("[A-J]")) {
                    break;
                } else {
                    System.out.println("❌ Invalid row! Please enter 'VIP' or a letter between A and J.");
                }
            }

            while (true) {
                System.out.print("Enter column (1-10) -> ");
                if (scanner.hasNextInt()) {
                    colInput = scanner.nextInt();
                    if (colInput >= 1 && colInput <= 10) {
                        colInput -= 1;
                        break;
                    } else {
                        System.out.println("❌ Invalid column! Please enter a number between 1 and 10.");
                    }
                } else {
                    System.out.println("❌ Invalid input! Please enter a number.");
                    scanner.next();
                }
            }

            if (rowInput.equals("VIP")) {
                if (colInput % 2 == 0 && colInput < cols - 1) {
                    if (seats[rows][colInput].contains("BK")) {
                        System.out.println("❌ This VIP pair is already booked!");
                    } else {
                        seats[rows][colInput] = "VIP" + (colInput + 1) + "-VIP" + (colInput + 2) + "-BK";
                        String vipSeatPair = "VIP" + (colInput + 1) + "-VIP" + (colInput + 2);
                        bookedSeats.add(vipSeatPair);
                        UserSelection.bookedSeats.add(vipSeatPair);

                       // add database
                        addBookingToDB( UserSelection.selectedMovie, "VIP", colInput + 1, vipSeatPair, true);
                        System.out.println("✅ VIP seats " + vipSeatPair + " booked successfully!");
                    }
                } else {
                    System.out.println("⚠️ Please enter the first seat number in the VIP pair (e.g., VIP1, VIP3, etc.).");
                }

            }
            else {
                int rowIndex = 'J' - rowInput.charAt(0);
                if (rowIndex >= 0 && rowIndex < rows && colInput >= 0 && colInput < cols) {
                    if (seats[rowIndex][colInput].contains("BK")) {
                        System.out.println("❌ This seat is already booked!");
                    } else {
                        seats[rowIndex][colInput] = rowInput + (colInput + 1) + "-BK";
                        bookedSeats.add(rowInput + (colInput + 1));

                        addBookingToDB(UserSelection.selectedMovie, rowInput, colInput + 1, rowInput + (colInput + 1), false);
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

    // Method to add booking to the database
    public static void addBookingToDB(String movieName, String row, int columnNumber, String seatLabel, boolean isVip) {
        String sql = "INSERT INTO booking (movie_name, row, column_number, seat_label, is_vip, booked_at) " +
                "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movieName);
            stmt.setString(2, row);
            stmt.setInt(3, columnNumber);
            stmt.setString(4, seatLabel);
            stmt.setBoolean(5, isVip);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Seat " + seatLabel + " booked successfully in database!");
            } else {
                System.out.println("❌ Failed to book the seat in the database.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public static void printReceipt() {
        UserSelection.displaySummary();

    }
}