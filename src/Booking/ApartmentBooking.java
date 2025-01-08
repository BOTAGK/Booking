package Booking;

import java.time.LocalDate;

public class ApartmentBooking extends Booking {
    private int roomCount;
    private double rating;
    
    public ApartmentBooking(String id, String name, String location, double price, LocalDate startDate, LocalDate endDate, int roomCount,double rating) {
        super(id, name, location, price,startDate,endDate );
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

    @Override
    public String toString() {
        return super.toString()+
                "Rating: " + rating + '\n'+
                "Room Count: " + roomCount+'\n';
    }
}
