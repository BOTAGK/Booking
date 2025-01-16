package Booking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import BookingService.BookingId;

public class CarRentalBooking extends Booking {

    private String carType;
    private String carModel;
    
    public CarRentalBooking() {}

    public CarRentalBooking(String name, String location, double price, LocalDate startDate, LocalDate endDate,
                            String carType, String carModel) {
        super(name, location, price, startDate, endDate);
        this.carType = carType;
        this.carModel = carModel;
    }

    public String getCarType() {
        return carType;
    }

    public String getCarModel() {
        return carModel;
    }

    public long getRentLength() {
        return ChronoUnit.DAYS.between(getStartDate(), getEndDate());
    }

    //public static List<CarRentalBooking> getCarRentalsFromFile(String fileName) {
      //  List<CarRentalBooking> bookings = new ArrayList<>();
       // List<String[]> data = Booking.loadDataFromFile(fileName);
       // for (String[] parts : data) {
              //  bookings.add(new CarRentalBooking(
                 //   parts[0], parts[1], Double.parseDouble(parts[2]), LocalDate.parse(parts[3]),
                //    LocalDate.parse(parts[4]), parts[5], parts[6]
              //  ));
      //  }
       // return bookings;
  //  }

   // public static List<String> getCarTypesFromFile() {
     //   Set<String> eventTypes = new HashSet<>();
     //   try (BufferedReader br = new BufferedReader(new FileReader("CarRentalBookingData.txt"))) {
       //     String line;
      //      while ((line = br.readLine()) != null) {
         //       String[] parts = line.split(",");
        //        if (parts.length > 5) {
         //           eventTypes.add(parts[5].trim());
        //        }
          //  }
      //  } catch (IOException e) {
      //      e.printStackTrace();
//}
     //   return new ArrayList<>(eventTypes);
   // }

    @Override
    public BookingId.Prefix getIdPrefix() {
        return BookingId.Prefix.CRT;
    }

    @Override
    public String toString() {
        return carModel + ", "+ carType + ", "+
                super.toString();
    }
}
