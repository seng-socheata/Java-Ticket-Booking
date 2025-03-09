package Design;

public class ThreeDcolor {
    public static void main(String[] args) throws InterruptedException {
        String text = "\\033[1m" +  // Bold Blue
                """
                 _       __     __                             __           ____              __   _                _______      __        __     _____            __               
                | |     / /__  / /________  ____ ___  ___     / /_____     / __ )____  ____  / /__(_)___  ____ _   /_  __(_)____/ /_____  / /_   / ___/__  _______/ /____  ____ ___ 
                | | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\   / __/ __ \\   / __  / __ \\/ __ \\/ //_/ / __ \\/ __ `/    / / / / ___/ //_/ _ \\/ __/   \\__ \\/ / / / ___/ __/ _ \\/ __ `__ \\
                | |/ |/ /  __/ / /__/ /_/ / / / / / /  __/  / /_/ /_/ /  / /_/ / /_/ / /_/ / ,< / / / / / /_/ /    / / / / /__/ ,< /  __/ /_    ___/ / /_/ (__  ) /_/  __/ / / / / /
                |__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/   \\__/\\____/  /_____/___/\\____/_/|_/_/_/ /_/\\__, /    /_/ /_/\\___/_/|_|\\___/\\__/   /____/\\__, /____/\\__/\\___/_/ /_/ /_/ 
                                                                                               /____/                                       /____/                           
                """ + "\\033[1m"; // Reset color

        System.out.println(text);

        Thread.sleep(2000); // Pause for 2 seconds
        clearConsole(); // Clear screen

        System.out.println(text); // Reprint after clearing screen
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
