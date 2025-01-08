package Main;

import Booking.*;
import FilterStrategy.*;
import SearchStrategy.PriceRangeStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
        System.out.println("Hello world!");
        System.out.println("Hello Wojtek sie melduje");
        System.out.println("Hello world!");
        System.out.println("Hello od Adama");
        System.out.println("Hello od Bogacz");
        System.out.println("Hello imxantek");
        System.out.println("Hello kgazda");
        */

        /*
        Booking testBooking1 = new Booking("B1", "wakacje", "Radom", 9999.99, "21.03.2025", "29.03.2025");
        Booking testBooking2= new Booking("B2", "odpoczynek", "Kolobrzeg", 2137.69, "21.06.2025", "23.06.2025");
        User testUser = new User("U1", "Janusz", "Kowalski", "januszkowalski@gmail.com");

        testUser.addBooking(testBooking1);
        testUser.addBooking(testBooking2);
        testUser.cancelBooking();

        BookingService bookingService = new BookingService();
        bookingService.showBookings();
        BookingService bookingService = BookingService.getInstance();

//test filter

        List<Booking> sampleBookings = List.of(
                new ApartmentBooking("1", "Luxury Apartment", "New York", 150.0, "2025-01-10", "2025-01-15", 3, 4),
                new ApartmentBooking("2", "Budget Apartment", "London", 50.0, "2025-02-01", "2025-02-05", 1, 2),
                new CarRentalBooking("3", "Car Rental A", "New York", 100.0, "2025-01-12", "2025-01-20", "SUV", "Toyota", 7),
                new CarRentalBooking("4", "Car Rental B", "New York", 200.0, "2025-03-01", "2025-03-10", "Sedan", "BMW", 10),
                new EventTicketBooking("5", "Concert", "New York", 75.0, "2025-02-10", "2025-02-10", "Music", "Coldplay", 500, null),
                new EventTicketBooking("6", "Football Match", "London", 150.0, "2025-04-01", "2025-04-01", "Sports", "Chelsea FC", 200, null)
        );

        bookingService.getBookings().addAll(sampleBookings);

        List<BookingFilterStrategy> filters = new ArrayList<>();
        filters.add(new PriceFilterStrategy(50.0, 150.0));
        filters.add(new LocationFilterStrategy(List.of("New York")));
        filters.add(new CarRentalFilterStrategy(List.of("SUV")));


        bookingService.setFilterStrategies(filters);
        List<Booking> filteredBookings = bookingService.filterBookings();

        for (Booking booking : filteredBookings) {
            System.out.println(booking);
        }
         */
    }
}