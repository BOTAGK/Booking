package BookingService;

import java.util.ArrayList;
import java.util.Scanner;

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

    //Zwraca szukanego użytkownika lub null jeśli takowy nie istnieje
    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    //Próbuje się zalogować na dane konto, jeśli się nie uda wyrzuci błąd
    public void loginAttempt(String username, String password) throws Exception {
        User user = findUser(username);
        if(user == null) {
            throw(new Exception("User not found"));
        }
        if(!user.getPassword().equals(password)) {
            throw(new Exception("Wrong password"));
        }
        Main.currentUser = user;
    }

}
