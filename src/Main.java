import BookingService.BookingService;
import BookingService.ResourceManager;
import BookingService.User;
import Booking.Booking;
import GUI.LoginGui;
import GUI.MainMenuGui;

public class Main {
    public static void main(String[] args) {
        new Main().run();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Program is closing. Perform cleanup tasks here.");
            ResourceManager.getInstance().seriaizeUsers();
            ResourceManager.getInstance().seriaizeBookings();
        }));
    }

    public void run() {
        ResourceManager.getInstance().readFileApartmentBooking();
        ResourceManager.getInstance().readFileCarRentalBooking();
        ResourceManager.getInstance().readFileEventTicketBooking();
        ResourceManager.getInstance().readFileUser();
        ResourceManager.getInstance().deseriaizeBookings();
        ResourceManager.getInstance().deseriaizeUsers();

        new LoginGui();
    }
}