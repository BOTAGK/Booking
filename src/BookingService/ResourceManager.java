package BookingService;

import Booking.ApartmentBooking;
import Booking.Booking;
import Booking.CarRentalBooking;
import Booking.EventTicketBooking;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ResourceManager {
    private UserList userList =  UserList.getInstance();
    private BookingService bookingList = BookingService.getInstance();
    private static ResourceManager instance;
    private List<ApartmentBooking> apartmentBookings = new ArrayList<>();
    private List<CarRentalBooking> carRentalBookings = new ArrayList<>();
    private List<EventTicketBooking> eventTicketBookings = new ArrayList<>();


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

    @SuppressWarnings("unchecked")
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
                    String name = data[0].trim();
                    String location = data[1].trim();
                    double price = Double.parseDouble(data[2].trim());
                    LocalDate startDate = LocalDate.parse(data[3].trim());
                    LocalDate endDate = LocalDate.parse(data[4].trim());
                    int roomCount =  Integer.parseInt(data[5].trim());
                    double rating = Double.parseDouble(data[6].trim());
                    String path = data[7].trim();
                    bookingList.createBooking(new ApartmentBooking(name, location, price, startDate, endDate, roomCount, rating, path));
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
                    String name = data[0].trim();
                    String location = data[1].trim();
                    double price = Double.parseDouble(data[2].trim());
                    LocalDate startDate = LocalDate.parse(data[3].trim());
                    LocalDate endDate = LocalDate.parse(data[4].trim());
                    String carType = data[5].trim();
                    String carModel = data[6].trim();
                    String path =data[7].trim();
                    bookingList.createBooking(new CarRentalBooking(name, location, price, startDate, endDate, carType, carModel, path));
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
                    LocalDate endDate = LocalDate.parse(data[3].trim());
                    String eventType = data[4].trim();
                    String artistOrTeam = data[5].trim();
                    int availableTickets = Integer.parseInt(data[6].trim());
                    String path =data[7].trim();
                    bookingList.createBooking(new EventTicketBooking(name, location, price, startDate, endDate, eventType, artistOrTeam, availableTickets, path));
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
            oos.writeObject(bookingList);
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
        ArrayList<Booking> deserializedBookigns;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            Object object = ois.readObject();
            bookingList = (BookingService)object;
            //deserializedBookigns = (ArrayList<Booking>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*for(Booking booking: deserializedBookigns) {
            if(booking instanceof ApartmentBooking){
                apartmentBookings.add((ApartmentBooking) booking);
            } else if(booking instanceof CarRentalBooking){
                carRentalBookings.add((CarRentalBooking) booking);
            } else if(booking instanceof EventTicketBooking){
                eventTicketBookings.add((EventTicketBooking) booking);
            }
        }*/

    }

    public List<ApartmentBooking> getApartmentBookings() {
        List<ApartmentBooking> availableApartments = new ArrayList<>();
        for(int i=0; i<apartmentBookings.size(); i++){
            if(apartmentBookings.get(i).getAvailable()){
                availableApartments.add(apartmentBookings.get(i));
            }
        }
        return availableApartments;
    }

    public List<CarRentalBooking> getCarRentalBookings() {
        List<CarRentalBooking> availableCars = new ArrayList<>();
        for(int i=0; i<carRentalBookings.size(); i++){
            if(carRentalBookings.get(i).getAvailable()){
                availableCars.add(carRentalBookings.get(i));
            }
        }
        return availableCars;
    }
    public List<EventTicketBooking> getEventTicketBookings() {
        List<EventTicketBooking> availableEvents = new ArrayList<>();
        for(int i=0; i<eventTicketBookings.size(); i++){
            if(eventTicketBookings.get(i).getAvailable()){
                availableEvents.add(eventTicketBookings.get(i));
            }
        }
        return availableEvents;
    }
    public List<String> getCarTypes() {
        List<String> carTypes = new ArrayList<>();
        for (CarRentalBooking carRentalBooking : carRentalBookings) {
            if(!carTypes.contains(carRentalBooking.getCarType())){
            carTypes.add(carRentalBooking.getCarType());
            }
        }
        return carTypes;
    }
    public List<String> getEventTypes() {
        List<String> eventTypes = new ArrayList<>();
        for (EventTicketBooking eventTicketBooking : eventTicketBookings) {
            if(!eventTypes.contains(eventTicketBooking.getEventType())){
            eventTypes.add(eventTicketBooking.getEventType());
            }
        }
        return eventTypes;
    }




    public static void main(String[] args) {
        ResourceManager rm = new ResourceManager();
        rm.seriaizeBookings();
        rm.deseriaizeBookings();
    }
}
