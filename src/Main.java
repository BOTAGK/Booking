import BookingService.ResourceManager;
import BookingService.User;
import GUI.LoginGui;
import GUI.MainMenuGui;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        ResourceManager.getInstance().readFileEventTicketBooking();
        ResourceManager.getInstance().readFileApartmentBooking();
        ResourceManager.getInstance().readFileCarRentalBooking();
        ResourceManager.getInstance().readFileUser();
        ResourceManager.getInstance().seriaizeUsers();
        ResourceManager.getInstance().seriaizeBookings();
        ResourceManager.getInstance().deseriaizeBookings();
        ResourceManager.getInstance().deseriaizeUsers();



        new LoginGui();


    }
}