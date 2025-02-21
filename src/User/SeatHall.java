package User;

public class SeatHall {
    private static final int rows = 10; // Regular rows (J-A)
    private static final int cols = 10; // Columns 1-10
    private static String[][] seats = new String[rows + 1][cols]; // +1 for VIP row

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String VIP_COLOR = "\u001B[32m"; // Green for VIP
    private static final String REGULAR_COLOR = "\u001B[34m"; // Blue for regular

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

        // Fill VIP seats (in pairs)
        for (int j = 0; j < cols; j++) {
            if (j % 2 == 0) {
                seats[rows][j] = VIP_COLOR + "VIP" + (j + 1) + RESET; // First seat in pair
            } else {
                seats[rows][j] = VIP_COLOR + "VIP" + (j) + "-" + (j + 1) + RESET; // Second seat in pair
            }
        }
    }

    public static void displaySeating() {
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                     Welcome to the Cinema!                                     ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                                            Screen                                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");

        for (int i = 0; i < rows; i++) {
            System.out.print("║  " + (char) ('J' - i) + "   ║ ");
            for (int j = 0; j < cols; j++) {
                String seat = seats[i][j];
                String displaySeat = REGULAR_COLOR + seat + RESET;
                System.out.printf(" %-9s ║", displaySeat); // Uniform width for seats
            }
            System.out.println();
            System.out.println("╠═══════════════════════════════════════════════════════════════════════════════════════════╣");
        }

        // Display VIP row
        System.out.print("║ VIP  ║ ");
        for (int j = 0; j < cols; j += 2) { // Pairing VIP seats
            if (!seats[rows][j].isEmpty()) {
                String vipPair = seats[rows][j] + (j + 1 < cols && !seats[rows][j + 1].isEmpty() ? "-" + seats[rows][j + 1] : "");
                System.out.printf(" %-24s ║", vipPair); // Wider space for VIP pairs
            }
        }
        System.out.println();

        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════╝");
    }


}
