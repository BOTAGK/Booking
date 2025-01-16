package Booking;

import java.time.LocalDate;

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
    
    @Override
    public BookingId.Prefix getIdPrefix() {
        return BookingId.Prefix.APT;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Rating: " + rating + '\n'+
                "Room Count: " + roomCount+'\n';
    }
}
