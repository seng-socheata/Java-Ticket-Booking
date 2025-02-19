package User;

import java.util.Scanner;

public class SeatHall {
    private static String[][] seats;
    private static final int rows = 10; // Rows J-A
    private static final int cols = 10; // Columns 1-10
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";

    public SeatHall() {
        // Initialize the seating arrangement
        seats = new String[rows + 1][cols]; // +1 for VIP row
        initializeSeats();
    }

    private void initializeSeats() {
        // Fill regular seats (J-A)
        char rowLabel = 'J';
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = rowLabel + String.valueOf(j + 1) + "-AV"; // Regular seats
            }
            rowLabel--;
        }

        // Fill VIP seats (2 columns, mix positions)
        for (int j = 0; j < cols; j++) {
            if (j % 5 == 0) {
                seats[rows][j] = "VIP" + (j + 1);
            } else {
                seats[rows][j] = ""; // Empty for non-VIP columns
            }
        }
    }

    public static void displaySeating() {
        System.out.println(GREEN + "╔" + "═".repeat(100) + "╗");
        System.out.println(GREEN + "║" + " ".repeat(100) + "Screen" + " ".repeat(20) + "║");
        System.out.println(GREEN + "╠" + "═".repeat(100) + "╣");

        // Display regular seats (J-A)
        for (int i = 0; i < rows; i++) {
            System.out.print(BLUE + "║  " + (char) ('J' - i) + "   " + RESET);
            for (int j = 0; j < cols; j++) {
                System.out.print(CYAN + " " + seats[i][j] + " " + RESET + BLUE + "║");
            }
            System.out.println();
            System.out.println(GREEN + "╠" + "═".repeat(100) + "╣");
        }

        // Display VIP seats
        System.out.print(BLUE + "║ VIP  " + RESET);
        for (int j = 0; j < cols; j++) {
            System.out.print(CYAN + " " + seats[rows][j] + " " + RESET + BLUE + "║");
        }
        System.out.println();
        System.out.println(GREEN + "╚" + "═".repeat(47) + "╝" + RESET);
    }
}
