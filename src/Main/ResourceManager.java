package Main;

import Booking.ApartmentBooking;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;



public class ResourceManager {
    UserList userList = new UserList();
    BookingService bookingList = new BookingService();

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
            System.out.println("Błą plik" + outputFilePath + " nie został znaleziony." + e.getMessage());
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
                    int rating = Integer.parseInt(data[7].trim());
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
        String inputFilePath = "CarRentalBooking.txt";
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
                    int rating = Integer.parseInt(data[7].trim());
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
                if(data.length == 11){
                    String id = data[0].trim();
                    String name = data[1].trim();
                    String location = data[2].trim();
                    int price = Integer.parseInt(data[3].trim());
                    LocalDate startDate = LocalDate.parse(data[4].trim());
                    LocalDate endDate = LocalDate.parse(data[5].trim());
                    String eventType = data[6].trim();
                    String artistOrTeam = data[7].trim();
                    int availableTickets = Integer.parseInt(data[8].trim());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Błąd plik " + inputFilePath + " nie został znaleziony." + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ResourceManager rm = new ResourceManager();
        rm.readFileUser();
    }
}
