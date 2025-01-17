package BookingService;

import java.util.ArrayList;
import java.util.List;

import Exceptions.InvalidLoginException;

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
        getUsers().add(new User(name, lastName, email, username, password));
    }

    public void deleteUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }

    //Zwraca szukanego użytkownika lub null jeśli takowy nie istnieje
    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // Attempts to find a user with given username and password
    // Returns whether the operation was successful
    public User loginAttempt(String username, String password) throws InvalidLoginException {
        User user = findUser(username);
        if(user == null) {
            throw(new InvalidLoginException("User not found"));
        }
        if(!user.getPassword().equals(password)) {
            throw(new InvalidLoginException("Wrong password"));
        }
        
        return user;
    }

    public List<String> getTakenUsernames(){
        List<String> takenUsernames = new ArrayList<>();
        for (User user : users) {
            takenUsernames.add(user.getUsername());
        }
        return takenUsernames;
    }

    public List<String> getTakenEmails() {
        List<String> takenEmails = new ArrayList<>();
        for (User user : users) {
            takenEmails.add(user.getEmail());
        }
        return takenEmails;
    }
}
