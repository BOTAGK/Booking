package Main;

import Booking.ApartmentBooking;
import Booking.Booking;
import Booking.CarRentalBooking;
import Booking.EventTicketBooking;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;



public class ResourceManager {
    UserList userList =  UserList.getInstance();
    BookingService bookingList = BookingService.getInstance();

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
                if(data.length == 8){
                    String id = data[0].trim();
                    String name = data[1].trim();
                    String location = data[2].trim();
                    int price = Integer.parseInt(data[3].trim());
                    LocalDate startDate = LocalDate.parse(data[4].trim());
                    LocalDate endDate = LocalDate.parse(data[5].trim());
                    int roomCount =  Integer.parseInt(data[6].trim());
                    double rating = Double.parseDouble(data[7].trim());
                    bookingList.getBookings().add(new ApartmentBooking(id, name, location, price, startDate, endDate, roomCount, rating));
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
                if(data.length == 8){
                    String id = data[0].trim();
                    String name = data[1].trim();
                    String location = data[2].trim();
                    int price = Integer.parseInt(data[3].trim());
                    LocalDate startDate = LocalDate.parse(data[4].trim());
                    LocalDate endDate = LocalDate.parse(data[5].trim());
                    String carType = data[6].trim();
                    String carModel = data[7].trim();
                    bookingList.getBookings().add(new CarRentalBooking(id, name, location, price, startDate, endDate, carType, carModel));
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
                if(data.length == 9){
                    String id = data[0].trim();
                    String name = data[1].trim();
                    String location = data[2].trim();
                    int price = Integer.parseInt(data[3].trim());
                    LocalDate startDate = LocalDate.parse(data[4].trim());
                    LocalDate endDate = LocalDate.parse(data[5].trim());
                    String eventType = data[6].trim();
                    String artistOrTeam = data[7].trim();
                    int availableTickets = Integer.parseInt(data[8].trim());
                    bookingList.getBookings().add(new EventTicketBooking(id, name, location, price, startDate, endDate, eventType, artistOrTeam, availableTickets));
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
            System.out.println("Dane użytkowników zostały zapisane do pliku " + outputFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Błąd plik" + outputFilePath + " nie został znaleziony." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Błąd podczas serializacji użytkowników." + e.getMessage());
        }
    }

    public void deseriaizeBookings(){
        String filePath = "bookings_data.ser";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            ArrayList<Booking> bookings = (ArrayList<Booking>) ois.readObject();

            bookingList.setBookings(bookings);

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
