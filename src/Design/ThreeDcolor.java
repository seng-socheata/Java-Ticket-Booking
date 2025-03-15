package Design;

public class ThreeDcolor {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD_BLUE = "\u001B[1;34m";
    public static final String BOLD_RED = "\u001B[1;31m";
    public static final String BOLD_YELLOW = "\u001B[1;33m";
    public static final String BOLD_GREEN = "\u001B[1;32m";
    public static final String BOLD_CYAN = "\u001B[1;36m";
    public static void displayTitle() {
        String text ="""

                ██╗███████╗████████╗ █████╗ ██████╗     ████████╗██╗ ██████╗██╗  ██╗███████╗████████╗    ██████╗  ██████╗  ██████╗ ██╗  ██╗██╗███╗   ██╗ ██████╗\s
                ██║██╔════╝╚══██╔══╝██╔══██╗██╔══██╗    ╚══██╔══╝██║██╔════╝██║ ██╔╝██╔════╝╚══██╔══╝    ██╔══██╗██╔═══██╗██╔═══██╗██║ ██╔╝██║████╗  ██║██╔════╝\s
                ██║███████╗   ██║   ███████║██║  ██║       ██║   ██║██║     █████╔╝ █████╗     ██║       ██████╔╝██║   ██║██║   ██║█████╔╝ ██║██╔██╗ ██║██║  ███╗
                ██║╚════██║   ██║   ██╔══██║██║  ██║       ██║   ██║██║     ██╔═██╗ ██╔══╝     ██║       ██╔══██╗██║   ██║██║   ██║██╔═██╗ ██║██║╚██╗██║██║   ██║
                ██║███████║   ██║   ██║  ██║██████╔╝       ██║   ██║╚██████╗██║  ██╗███████╗   ██║       ██████╔╝╚██████╔╝╚██████╔╝██║  ██╗██║██║ ╚████║╚██████╔╝
                ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝        ╚═╝   ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝   ╚═╝       ╚═════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝\s              


        """;
        System.out.println(BOLD_CYAN+text);

    }

    // Method to clear the console screen
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing console.");
        }
    }
}
