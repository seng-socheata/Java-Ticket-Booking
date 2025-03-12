package User.BookingSeat;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.*;

class bookingseat {
    private String seatNumber;
    private String seatType;
    private boolean booked;

    public bookingseat(String seatNumber, String seatType) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.booked = false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public boolean isBooked() {
        return booked;
    }

    public void bookSeat() {
        if (!booked) {
            booked = true;
            System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN)
                    .a("✅ Seat " + seatNumber + " booked successfully.").reset());
        } else {
            System.out.println(Ansi.ansi().fg(Ansi.Color.RED)
                    .a("⚠️ Seat " + seatNumber + " is already booked.").reset());
        }
    }

    public void cancelBooking() {
        if (booked) {
            booked = false;
            System.out.println(Ansi.ansi().fg(Ansi.Color.RED)
                    .a("❌ Booking for seat " + seatNumber + " has been canceled.").reset());
        } else {
            System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW)
                    .a("⚠️ Seat " + seatNumber + " is not booked.").reset());
        }
    }
}

class Booking {
    private String bookingId;
    private String showId;
    private int totalTickets;
    private double totalPrice;
    private String bookingTime;
    private String paymentStatus;

    // Constructor
    public Booking(String bookingId, String showId, int totalTickets, double totalPrice, String bookingTime, String paymentStatus) {
        this.bookingId = bookingId;
        this.showId = showId;
        this.totalTickets = totalTickets;
        this.totalPrice = totalPrice;
        this.bookingTime = bookingTime;
        this.paymentStatus = paymentStatus;

        // Call the display method after assigning values
        displayBookingDetails();
    }

    // Display booking details
    public void displayBookingDetails() {
        System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN)
                .a("──────────────── BOOKING DETAILS ────────────────")
                .reset());

        // Correct usage of printf:
        //   1) Provide a format string.
        //   2) Provide the arguments in the correct order.
        System.out.printf(
                Ansi.ansi().fg(Ansi.Color.YELLOW).a("Booking ID: %-36s\n").reset().toString(),
                bookingId
        );
        System.out.printf(
                Ansi.ansi().fg(Ansi.Color.YELLOW).a("Show ID: %-36s\n").reset().toString(),
                showId
        );
        System.out.printf(
                Ansi.ansi().fg(Ansi.Color.YELLOW).a("Total Tickets: %-6d\n").reset().toString(),
                totalTickets
        );
        System.out.printf(
                Ansi.ansi().fg(Ansi.Color.YELLOW).a("Total Price: $%-6.2f\n").reset().toString(),
                totalPrice
        );
        System.out.printf(
                Ansi.ansi().fg(Ansi.Color.YELLOW).a("Booking Time: %-36s\n").reset().toString(),
                bookingTime
        );
        System.out.printf(
                Ansi.ansi().fg(Ansi.Color.YELLOW).a("Payment Status: %-10s\n").reset().toString(),
                paymentStatus
        );

        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN)
                .a("──────────────────────────────────────────────")
                .reset());
    }
}

public class booking {
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        Scanner scanner = new Scanner(System.in);
        List<bookingseat> seats = new ArrayList<>();

        // Initialize VIP seats (VIP1 - VIP10)
        for (int i = 1; i <= 10; i++) {
            seats.add(new bookingseat("VIP" + i, "VIP"));
        }


        // Initialize Regular seats (A1-AV to J10-AV)
        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (char row : rows) {
            for (int seatNum = 1; seatNum <= 10; seatNum++) {
                seats.add(new bookingseat(row + seatNum + "-AV", "Regular"));
            }
        }

        while (true) {
            System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN)
                    .a("\n════════════ SEAT BOOKING MENU ════════════").reset());
            System.out.println("1️⃣ View Seats");
            System.out.println("2️⃣ Book a Seat(s)");
            System.out.println("3️⃣ Cancel Booking");
            System.out.println("4️⃣ Exit");
            System.out.print(Ansi.ansi().fg(Ansi.Color.CYAN)
                    .a("👉 Enter your choice: ").reset());

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline character
            } else {
                System.out.println(Ansi.ansi().fg(Ansi.Color.RED)
                        .a("⚠️ Invalid input! Please enter a number.").reset());
                scanner.next(); // clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    displaySeatsInRows(seats);
                    break;
                case 2:
                    System.out.print(Ansi.ansi().fg(Ansi.Color.CYAN)
                            .a("🎫 How many seats do you want to book? ").reset());
                    int seatsToBook;
                    if (scanner.hasNextInt()) {
                        seatsToBook = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                    } else {
                        System.out.println(Ansi.ansi().fg(Ansi.Color.RED)
                                .a("⚠️ Invalid number. Please try again.").reset());
                        scanner.next();
                        break;
                    }

                    List<bookingseat> bookedSeats = new ArrayList<>();
                    double totalPrice = 0;

                    for (int i = 0; i < seatsToBook; i++) {
                        System.out.print(Ansi.ansi().fg(Ansi.Color.CYAN)
                                .a("🎫 Enter seat number to book (e.g., VIP5, A3-AV, J10-AV): ").reset());
                        String seatToBook = scanner.nextLine().toUpperCase();
                        boolean foundToBook = false;
                        for (bookingseat seat : seats) {
                            if (seat.getSeatNumber().equalsIgnoreCase(seatToBook)) {
                                foundToBook = true;
                                if (!seat.isBooked()) {
                                    seat.bookSeat();
                                    bookedSeats.add(seat);
                                    double price = seat.getSeatType().equalsIgnoreCase("VIP") ? 10.0 : 5.0;
                                    totalPrice += price;
                                } else {
                                    System.out.println(Ansi.ansi().fg(Ansi.Color.RED)
                                            .a("⚠️ Seat " + seatToBook + " is already booked. Please choose another seat.").reset());
                                    i--; // decrement to retry booking for this count
                                }
                                break;
                            }
                        }
                        if (!foundToBook) {
                            System.out.println(Ansi.ansi().fg(Ansi.Color.RED)
                                    .a("⚠️ Seat " + seatToBook + " not found. Please try again.").reset());
                            i--; // decrement to retry booking for this count
                        }
                    }


                    if (!bookedSeats.isEmpty()) {
                        // Create a new booking
                        Booking booking = new Booking(
                                UUID.randomUUID().toString(),
                                "SHOW123",
                                bookedSeats.size(),
                                totalPrice,
                                new Date().toString(),
                                "Paid"
                        );
                        // booking.displayBookingDetails(); // Already called in constructor
                    }
                    break;
                case 3:
                    System.out.print(Ansi.ansi().fg(Ansi.Color.CYAN)
                            .a("❌ Enter seat number to cancel booking: ").reset());
                    String seatToCancel = scanner.nextLine().toUpperCase();
                    boolean foundToCancel = false;
                    for (bookingseat seat : seats) {
                        if (seat.getSeatNumber().equalsIgnoreCase(seatToCancel)) {
                            seat.cancelBooking();
                            foundToCancel = true;
                            break;
                        }
                    }
                    if (!foundToCancel) {
                        System.out.println(Ansi.ansi().fg(Ansi.Color.RED)
                                .a("⚠️ Seat not found. Please try again.").reset());
                    }
                    break;
                case 4:
                    System.out.println(Ansi.ansi().fg(Ansi.Color.CYAN)
                            .a("🚪 Exiting... Thank you for using the booking system!").reset());
                    scanner.close();
                    AnsiConsole.systemUninstall();
                    return;
                default:
                    System.out.println(Ansi.ansi().fg(Ansi.Color.RED)
                            .a("⚠️ Invalid choice. Please enter a number between 1 and 4.").reset());
            }
        }
    }

    private static void displaySeatsInRows(List<bookingseat> seats) {
        System.out.println(Ansi.ansi().fg(Ansi.Color.MAGENTA)
                .a("\n════════════ AVAILABLE SEATS ════════════").reset());
        System.out.println("------------------------------------------------");
        System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("🎭 VIP SEATS:").reset());
        displaySeatsByType(seats, "VIP");

        System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("\n🎫 REGULAR SEATS:").reset());
        displaySeatsByType(seats, "Regular");
        System.out.println("------------------------------------------------");
    }

    private static void displaySeatsByType(List<bookingseat> seats, String type) {
        int count = 0;
        for (bookingseat seat : seats) {
            if (seat.getSeatType().equalsIgnoreCase(type)) {
                System.out.print(Ansi.ansi().fg(Ansi.Color.BLUE)
                        .a(seat.getSeatNumber() + (seat.isBooked() ? " [✅]\t" : " [❌]\t"))
                        .reset());
                count++;
                if (count % 10 == 0) {
                    System.out.println();
                }
            }
        }
        System.out.println();
    }
}