package Main;

import java.util.ArrayList;
import Booking.Booking;

public class UserList {

    ResourceManager rm; // nie wiem czy to tutaj powinno byc ???

    private static UserList instance;

    private ArrayList<User> users;

    private UserList() {
        this.users = new ArrayList<User>();
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public ArrayList<User> getUsers() { return users; }

    public void setUsers(ArrayList<User> users) { this.users = users; }

    public void createrUser(String name, String lastName, String email, String username, String password) {
        rm.deseriaizeUsers();
        getUsers().add(new User(name, lastName, email, username, password));
        rm.seriaizeUsers();
    }

    public void deleteUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }

}
