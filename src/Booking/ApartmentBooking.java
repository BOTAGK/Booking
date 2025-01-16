package Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BookingService.BookingId;

public class ApartmentBooking extends Booking {
    private int roomCount;
    private double rating;

    public ApartmentBooking() {}
    
    public ApartmentBooking(String name, String location, double price, LocalDate startDate, LocalDate endDate, int roomCount,double rating) {
        super(name, location, price, startDate, endDate);
        this.roomCount = roomCount;
        this.rating = rating;
    }

    public int getRoomCount() {
        return roomCount;
    }
    public double getRating() {
        return rating;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    //public static List<ApartmentBooking> getApartmentsFromFile(String fileName) {
       // List<ApartmentBooking> bookings = new ArrayList<>();
       // List<String[]> data = Booking.loadDataFromFile(fileName);
        //for (String[] parts : data) {
            //bookings.add(new ApartmentBooking(
                   // parts[0], parts[1], Double.parseDouble(parts[2]), LocalDate.parse(parts[3]),
                    //LocalDate.parse(parts[4]), Integer.parseInt(parts[5]), Double.parseDouble(parts[6])
            //));
       // }
        //return bookings;
   // }
    
    @Override
    public BookingId.Prefix getIdPrefix() {
        return BookingId.Prefix.APT;
    }

    @Override
    public String toString() {
        return         getName()+ ", "+super.toString()+
                ", Rating: " + rating + ", Room Count: " + roomCount;
    }
}
