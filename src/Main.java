import BookingService.ResourceManager;
import GUI.LoginGui;

public class Main {
    public static void main(String[] args) { 
        new Main().run();
    }

    public void run() {
        // ResourceManager.getInstance().deseriaizeBookings();
        // ResourceManager.getInstance().deseriaizeUsers();
        ResourceManager.getInstance().readFileUser();
        ResourceManager.getInstance().readFileApartmentBooking();
        ResourceManager.getInstance().readFileCarRentalBooking();
        ResourceManager.getInstance().readFileEventTicketBooking();

        new LoginGui();
    }
}