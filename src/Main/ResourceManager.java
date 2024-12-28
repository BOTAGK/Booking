package Main;

import java.io.*;

public class ResourceManager {
    UserList userList = new UserList();

    public void seriaizeUsers(){
        String inputFilePath = "users_data.txt";
        String outputFilePath = "users_data.ser";

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

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFilePath))){
            oos.writeObject(userList.getUsers());
            System.out.println("Dane użytkowników zostały zapisane do pliku " + outputFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Błą plik" + outputFilePath + " nie został znaleziony." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Błąd podczas serializacji użytkowników." + e.getMessage());
        }
    }

    //Bardziej skomplikowany odczyt obietkow poprzez rozna ilosc skladowych klas ABooking, CBooking i ETBooking
    public void serializeBookings() {
        String inputFilePath = "bookings.txt";
        String outputFilePath = "bookings.ser";
    }

    public static void main(String[] args) {
        ResourceManager rm = new ResourceManager();
        rm.seriaizeUsers();
    }
}
