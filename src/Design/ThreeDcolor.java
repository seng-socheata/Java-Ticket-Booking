package Design;

import com.github.lalyos.jfiglet.FigletFont;

public class ThreeDcolor {
    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";

    public static void main(String[] args) {
        try {
            // Generate ASCII art
            String ascii = FigletFont.convertOneLine("ISTAD");

            // Center the ASCII art in the terminal with a box
            printBox(ascii, GREEN, CYAN);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to center text and add a box
    public static void printBox(String text, String textColor, String boxColor) {
        String[] lines = text.split("\n");
        int consoleWidth = 180; // Adjust this value based on your terminal width
        int boxWidth = consoleWidth - 10;

        // Top border
        System.out.println(boxColor + "╔" + "═".repeat(boxWidth) + "╗" + RESET);

        // Print each line inside the box, centered
        for (String line : lines) {
            int padding = (boxWidth - line.length()) / 2;
            if (padding < 0) padding = 0; // Prevent negative padding
            System.out.println(boxColor + "║" + " ".repeat(padding) + textColor + line + " ".repeat(padding) + boxColor + "║" + RESET);
        }

        // Bottom border
        System.out.println(boxColor + "╚" + "═".repeat(boxWidth) + "╝" + RESET);
    }
}
