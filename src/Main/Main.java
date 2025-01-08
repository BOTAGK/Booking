package Main;

import java.time.LocalDate;

import Booking.*;
import SearchStrategy.PriceRangeStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // System.out.println("Hello world!");
        // System.out.println("Hello Wojtek sie melduje");
        // System.out.println("Hello world!");
        // System.out.println("Hello od Adama");
        // System.out.println("Hello od Bogacz");
        // System.out.println("Hello imxantek");
        // System.out.println("Hello kgazda");

        Booking testBooking1 = new ApartmentBooking("B1", "wakacje", "Radom", 9999.99, LocalDate.of(2025, 3, 5), LocalDate.of(2025, 3, 9), 1, 2);
        Booking testBooking2= new ApartmentBooking("B2", "odpoczynek", "Kolobrzeg", 2137.69, LocalDate.of(2025, 7, 12), LocalDate.of(2025, 9, 5), 2, 4);
        User testUser = new User("U1", "Janusz", "Kowalski", "januszkowalski@gmail.com", "pasłord");

        testUser.addBooking(testBooking1);
        testUser.addBooking(testBooking2);
        testUser.cancelBooking();//rev powinno brac id albo index albo cokolwiek

        BookingService bookingService = BookingService.getInstance();
        bookingService.showBookings();
    }
}