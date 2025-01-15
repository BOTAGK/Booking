package Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public static List<ApartmentBooking> getApartmentsFromFile(String fileName) {
        List<ApartmentBooking> bookings = new ArrayList<>();
        List<String[]> data = Booking.loadDataFromFile(fileName);
        for (String[] part : data) {
            bookings.add(new ApartmentBooking(
                    part[1], part[2], Double.parseDouble(part[3]), LocalDate.parse(part[4]),
                    LocalDate.parse(part[5]), Integer.parseInt(part[6]), Double.parseDouble(part[7])
            ));
        }
        return bookings;
    }
    
    @Override
    public String getIdType() {
        return "APT";
    }

    @Override
    public String toString() {
        return super.toString()+
                "Rating: " + rating + '\n'+
                "Room Count: " + roomCount+'\n';
    }
}
