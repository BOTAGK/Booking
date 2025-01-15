package Main;


import java.time.LocalDate;

import Booking.*;
import FilterStrategy.*;

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

//        Booking testBooking1 = new ApartmentBooking("B1", "wakacje", "Radom", 9999.99, LocalDate.of(2025, 3, 5), LocalDate.of(2025, 3, 9), 1, 2);
//        Booking testBooking2= new ApartmentBooking("B2", "odpoczynek", "Kolobrzeg", 2137.69, LocalDate.of(2025, 7, 12), LocalDate.of(2025, 9, 5), 2, 4);
//        User testUser = new User("U1", "Janusz", "Kowalski", "januszkowalski@gmail.com", "pasłord");
//
//        testUser.addBooking(testBooking1);
//        testUser.addBooking(testBooking2);
//        testUser.cancelBooking();//rev powinno brac id albo index albo cokolwiek
//
        BookingService bookingService = BookingService.getInstance();
        bookingService.printBookings();

        //test filter

        List<Booking> sampleBookings = List.of(
                new ApartmentBooking("Luxury Apartment", "New York", 150.0, LocalDate.of(2025,1, 10), LocalDate.of(2025,1,15), 3, 4),
                new ApartmentBooking("Budget Apartment", "London", 50.0, LocalDate.of(2025,2,1), LocalDate.of(2025,2,5), 1, 2),
                new CarRentalBooking("Car Rental A", "New York", 100.0, LocalDate.of(2025,1,12), LocalDate.of(2025,1,20), "SUV", "Toyota"),
                new CarRentalBooking("Car Rental B", "New York", 200.0, LocalDate.of(2025,3,1), LocalDate.of(2025,3,10), "Sedan", "BMW"),
                new EventTicketBooking("Concert", "New York", 75.0, LocalDate.of(2025,2,10), LocalDate.of(2025,2,10), "Music", "Coldplay", 500),
                new EventTicketBooking("Football Match", "London", 150.0, LocalDate.of(2025,4,1), LocalDate.of(2025,4,1), "Sports", "Chelsea FC", 200)
        );

        bookingService.createBookings(sampleBookings);

        List<FilterStrategy> filters = new ArrayList<>();
        filters.add(new PriceFilterStrategy(50.0, 150.0));
        filters.add(new LocationFilterStrategy(List.of("New York")));
        filters.add(new CarRentalFilterStrategy(List.of("SUV")));

        List<Booking> filteredBookings = bookingService.filterBookings(filters);

        for (Booking booking : filteredBookings) {
            System.out.println(booking);
        }
    }
}