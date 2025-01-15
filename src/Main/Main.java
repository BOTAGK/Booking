package Main;


import java.time.LocalDate;

import Booking.*;
import FilterStrategy.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Booking testBooking1 = new ApartmentBooking("B1", "wakacje", "Radom", 9999.99, LocalDate.of(2025, 3, 5), LocalDate.of(2025, 3, 9), 1, 2);
//        Booking testBooking2= new ApartmentBooking("B2", "odpoczynek", "Kolobrzeg", 2137.69, LocalDate.of(2025, 7, 12), LocalDate.of(2025, 9, 5), 2, 4);
//        User testUser = new User("U1", "Janusz", "Kowalski", "januszkowalski@gmail.com", "pasłord");
//
//        testUser.addBooking(testBooking1);
//        testUser.addBooking(testBooking2);
//        testUser.cancelBooking();//rev powinno brac id albo index albo cokolwiek
//
        BookingService bookingService = BookingService.getInstance();
        UserList userList = UserList.getInstance();
        List<Booking> sampleBookings = List.of(
                new ApartmentBooking("Luxury Apartment", "New York", 150.0, LocalDate.of(2025,1, 10), LocalDate.of(2025,1,15), 3, 4),
                new ApartmentBooking("Budget Apartment", "London", 50.0, LocalDate.of(2025,2,1), LocalDate.of(2025,2,5), 1, 2),
                new CarRentalBooking("Car Rental A", "New York", 100.0, LocalDate.of(2025,1,12), LocalDate.of(2025,1,20), "SUV", "Toyota"),
                new CarRentalBooking("Car Rental B", "New York", 200.0, LocalDate.of(2025,3,1), LocalDate.of(2025,3,10), "Sedan", "BMW"),
                new EventTicketBooking("Concert", "New York", 75.0, LocalDate.of(2025,2,10), LocalDate.of(2025,2,10), "Music", "Coldplay", 500),
                new EventTicketBooking("Football Match", "London", 150.0, LocalDate.of(2025,4,1), LocalDate.of(2025,4,1), "Sports", "Chelsea FC", 200)
        );
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Adam", "Nowak", "adam@gmail.com", "adam12", "123"));
        userList.setUsers(users);
        bookingService.setBookings(sampleBookings);
        User currentUser = userList.login();
        bookingService.showBookings(bookingService.getBookings());
        System.out.println("Pick Category:\n" + "1. Car rental\n" + "2. Apartment booking\n" + "3. Event booking" );
        Scanner scanner = new Scanner(System.in);
        List<FilterStrategy> filters = new ArrayList<>();
        int choice;
        do {
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Select car types:\n"+ "1. SUV\n" + "2. Combi\n" + "3. Sedane\n" + "4. VAN\n" + "When you're done enter '0' ");
                    int typeNumber = scanner.nextInt();
                    List<String> carTypes = new ArrayList<>();
                    while(typeNumber != 0){
                        switch (typeNumber){
                            case 0:
                                break;
                            case 1:
                                carTypes.add("SUV");
                                break;
                            case 2:
                                carTypes.add("Combi");
                                break;
                            case 3:
                                carTypes.add("Sedane");
                                break;
                            case 4:
                                carTypes.add("VAN");
                                break;
                            default:
                                System.out.println("Please enter a valid number");
                                break;
                            }
                            typeNumber = scanner.nextInt();
                    }
                    if(carTypes.isEmpty()){
                        carTypes = null;
                    }
                    filters.add(new CarRentalFilterStrategy(carTypes));
                    break;
                case 2:
                    System.out.println("Please enter:\n"+ "min. number of rooms: ");
                    int minNumberOfRooms = scanner.nextInt();
                    while(minNumberOfRooms < 0) {
                        System.out.println("Please enter a valid number");
                        minNumberOfRooms = scanner.nextInt();
                    }
                    System.out.println("Please enter:\n"+ "min. rating: ");
                    int minRating = scanner.nextInt();
                    while(minRating < 0 || minRating > 5) {
                        System.out.println("Please enter a valid number");
                        minRating = scanner.nextInt();
                    }
                    filters.add(new ApartmentFilterStrategy(minNumberOfRooms,minRating));
                    break;
                case 3:
                    System.out.println("Select event types:\n"+ "1. Sports\n" + "2. Concert\n" + "3. Comedy\n" + "When you're done enter '0' ");
                    List<String> eventTypes = new ArrayList<>();
                    typeNumber = scanner.nextInt();
                    while(typeNumber != 0){

                        switch (typeNumber){
                            case 0:
                                break;
                            case 1:
                                eventTypes.add("Sports");
                                break;
                            case 2:
                                eventTypes.add("Concert");
                                break;
                            case 3:
                                eventTypes.add("Comedy");
                                break;
                            default:
                                System.out.println("Please enter a valid number");
                                break;
                        }
                        typeNumber = scanner.nextInt();
                    }
                    if(eventTypes.isEmpty()){
                      eventTypes = null;
                    }
                    filters.add(new EventTicketFilterStrategy(eventTypes));
                    break;
                default:
                    System.out.println("Wrong number, enter your choice again");
                    break;
            }
        }while(choice<1 || choice>3);

        System.out.println("Enter price range:\n" + "min. price: ");
        double minPrice = scanner.nextDouble();
        System.out.println("max. price: ");
        double maxPrice = scanner.nextDouble();
        filters.add(new PriceFilterStrategy(minPrice,maxPrice));
        System.out.println("Select locations:\n" + "1. New York\n" + "2. London\n" + "3. Warsaw\n"+ "4. Sydney\n" + "5. Liverpool\n" + "When you're done enter '0' ");
        int typeNumber = scanner.nextInt();
        List<String> locations = new ArrayList<>();
        while(typeNumber != 0){

            switch (typeNumber){
                case 0:
                    break;
                case 1:
                    locations.add("New York");
                    break;
                case 2:
                    locations.add("London");
                    break;
                case 3:
                    locations.add("Warsaw");
                    break;
                case 4:
                    locations.add("Sydney");
                    break;
                case 5:
                    locations.add("Liverpool");
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
            typeNumber = scanner.nextInt();
        }
        if(!locations.isEmpty()){
            filters.add(new LocationFilterStrategy(locations));
        }
        bookingService.setFilterStrategies(filters);
        bookingService.showBookings(bookingService.filterBookings());
        if(!bookingService.filterBookings().isEmpty()){
            bookingService.reserveBooking(currentUser);
        }
        else{
            System.out.println("Bookings with this filters not found");
        }

        currentUser.showBookings();



        //test filter
/*

        bookingService.getBookings().addAll(sampleBookings);

        List<FilterStrategy> filters = new ArrayList<>();
        filters.add(new PriceFilterStrategy(50.0, 150.0));
        filters.add(new LocationFilterStrategy(List.of("New York")));
        filters.add(new CarRentalFilterStrategy(List.of("SUV")));

        List<Booking> filteredBookings = bookingService.filterBookings(filters);

        for (Booking booking : filteredBookings) {
            System.out.println(booking);
        }

 */
    }
}