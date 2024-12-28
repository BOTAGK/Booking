package Main;

import java.util.ArrayList;
import Booking.Booking;

public class UserList {

    private static UserList instance;

    private ArrayList<User> users;

    public UserList() {
        this.users = new ArrayList<User>();
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public ArrayList<User> getUsers() { return users; }

    public void createrUser(String name, String lastName, String email, String username, String password) {
        users.add(new User(name, lastName, email, username, password));
    }

    public void deleteUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }

}
