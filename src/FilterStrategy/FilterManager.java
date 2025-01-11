package FilterStrategy;

import java.util.ArrayList;
import java.util.List;

public class FilterManager {
    public static ArrayList<String> getFiltersForCategory(String category){
        switch (category){
            case "Car Rent":
                return new ArrayList<>(List.of("Car Rental Filter", "Location Filter", "Price Filter"));
            case "Apartment Rent":
                return new ArrayList<>(List.of("Apartment Filter", "Location Filter", "Price Filter"));
            case "Event Ticket":
                return new ArrayList<>(List.of("Event Ticket Filter", "Location Filter", "Price Filter"));
            default:
                return new ArrayList<>();
        }
    };
}
