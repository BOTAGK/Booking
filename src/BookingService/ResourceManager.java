package BookingService;

import Booking.ApartmentBooking;
import Booking.Booking;
import Booking.CarRentalBooking;
import Booking.EventTicketBooking;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;



public class ResourceManager {
    private UserList userList =  UserList.getInstance();
    private BookingService bookingList = BookingService.getInstance();
    private static ResourceManager instance;

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    public void readFileUser(){
        String inputFilePath = "users_data.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data.length == 5){
                    String name = data[0].trim();
                    String lastName = data[1].trim();
                    String email = data[2].trim();
                    String username = data[3].trim();
                    String password = data[4].trim();
                    userList.getUsers().add(new User(name, lastName, email, username, password));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Błąd plik " + inputFilePath + " nie został znaleziony." + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void seriaizeUsers(){
        String outputFilePath = "users_data.ser";

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFilePath))){
            oos.writeObject(userList.getUsers());
            System.out.println("Dane użytkowników zostały zapisane do pliku " + outputFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Błąd plik" + outputFilePath + " nie został znaleziony." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Błąd podczas serializacji użytkowników." + e.getMessage());
        }
    }

    public void deseriaizeUsers(){
        String filePath = "users_data.ser";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            ArrayList<User> users = (ArrayList<User>) ois.readObject();

            userList.setUsers(users);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFileApartmentBooking(){
        String inputFilePath = "ApartmentBookingData.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data.length == 7){
                    String name = data[0].trim();
                    String location = data[1].trim();
                    double price = Double.parseDouble(data[2].trim());
                    LocalDate startDate = LocalDate.parse(data[3].trim());
                    LocalDate endDate = LocalDate.parse(data[4].trim());
                    int roomCount =  Integer.parseInt(data[5].trim());
                    double rating = Double.parseDouble(data[6].trim());
                    bookingList.createBooking(new ApartmentBooking(name, location, price, startDate, endDate, roomCount, rating));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Błąd plik " + inputFilePath + " nie został znaleziony." + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFileCarRentalBooking(){
        String inputFilePath = "CarRentalBookingData.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data.length == 7){
                    String name = data[0].trim();
                    String location = data[1].trim();
                    double price = Double.parseDouble(data[2].trim());
                    LocalDate startDate = LocalDate.parse(data[3].trim());
                    LocalDate endDate = LocalDate.parse(data[4].trim());
                    String carType = data[5].trim();
                    String carModel = data[6].trim();
                    bookingList.createBooking(new CarRentalBooking(name, location, price, startDate, endDate, carType, carModel));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Błąd plik " + inputFilePath + " nie został znaleziony." + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFileEventTicketBooking(){
        String inputFilePath = "EventTicketBookingData.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data.length == 8){
                    String name = data[0].trim();
                    String location = data[1].trim();
                    double price = Double.parseDouble(data[2].trim());
                    LocalDate startDate = LocalDate.parse(data[3].trim());
                    LocalDate endDate = LocalDate.parse(data[4].trim());
                    String eventType = data[5].trim();
                    String artistOrTeam = data[6].trim();
                    int availableTickets = Integer.parseInt(data[7].trim());
                    bookingList.createBooking(new EventTicketBooking(name, location, price, startDate, endDate, eventType, artistOrTeam, availableTickets));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Błąd plik " + inputFilePath + " nie został znaleziony." + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void seriaizeBookings(){
        String outputFilePath = "bookings_data.ser";

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFilePath))){
            oos.writeObject(bookingList.getBookings());
            System.out.println("Dane bookingow zostały zapisane do pliku " + outputFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Błąd plik" + outputFilePath + " nie został znaleziony." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Błą przy serializacji bookingow" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void deseriaizeBookings(){
        String filePath = "bookings_data.ser";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            ArrayList<Booking> bookings = (ArrayList<Booking>) ois.readObject();

            bookingList.createBookings(bookings);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ResourceManager rm = new ResourceManager();
        rm.readFileApartmentBooking();
        rm.readFileCarRentalBooking();
        rm.readFileEventTicketBooking();
        rm.seriaizeBookings();
    }
}
