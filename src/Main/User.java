package Main;

import Booking.Booking;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    String id;
    String name;
    String lastname;
    String email;

    ArrayList<Booking> bookings;

    public User(String id, String name, String lastname, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;

        bookings = new ArrayList<Booking>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
