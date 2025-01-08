package Main;

import java.io.*;
import java.util.ArrayList;



public class ResourceManager {
    UserList userList = new UserList();

    public void readFile(){
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

    public void writeFile(){

    }
    public void serializeBookings() {
        String inputFilePath = "bookings.txt";
        String outputFilePath = "bookings.ser";
    }

    public static void main(String[] args) {
        ResourceManager rm = new ResourceManager();
        rm.deseriaizeUsers();
    }
}
