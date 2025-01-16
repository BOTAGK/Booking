import BookingService.ResourceManager;
import BookingService.User;
import GUI.LoginGui;
import GUI.MainMenuGui;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {

        ResourceManager.getInstance().deseriaizeBookings();
        ResourceManager.getInstance().deseriaizeUsers();



        new LoginGui();


    }
}