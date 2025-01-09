package Main;

import java.util.ArrayList;
import java.util.Scanner;

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

    public User login(){
        boolean result = false;
        System.out.println("Please log in");
        while(!result){
            System.out.println("Username: ");
            Scanner sc = new Scanner(System.in);
            String username = sc.nextLine();
            System.out.println("Password: ");
            String password = sc.nextLine();
            for (User user : getUsers()) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    System.out.println("Successfully logged in");
                    result = true;
                    return user;
                }
            }
            System.out.println("Wrong username or password\n" + "Please try again");
        }
        return null;
    }

    public void deleteUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }

}
