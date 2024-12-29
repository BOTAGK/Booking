package Main;

import Booking.Booking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Serializable {

    private String username;
    private String name;
    private String lastname;
    private String email;
    private String password;

    ArrayList<Booking> bookings;

    public User( String name, String lastname, String email, String username, String password) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

        bookings = new ArrayList<Booking>();
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) {this.lastname = lastname; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    public void showBookings(){
        for(Booking booking : bookings){
            System.out.println(booking.toString());
            System.out.println();
        }
    }
    public void addBooking(Booking booking){
        bookings.add(booking);
    }
    public void cancelBooking(){

        System.out.println("Which booking do you want to remove?\n");

        Scanner sc = new Scanner(System.in);
        String choice;
        boolean deleted = false;

        while(!deleted){

            showBookings();
            System.out.print("Insert the correct booking Id: ");
            choice = sc.nextLine();

            for(int i = 0; i < bookings.size(); i++){
                Booking b = bookings.get(i);
                if(b.getId().equals(choice)){
                    bookings.remove(i);
                    deleted = true;
                    System.out.println("Booking has been removed\n");
                    showBookings();
                    break;
                }
                else{
                    System.out.println("Not a valid booking ID\n");
                }
            }

        }

    }
}
