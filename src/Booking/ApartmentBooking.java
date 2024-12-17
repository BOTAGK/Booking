package Booking;

public class ApartmentBooking extends Booking {
    private int roomCount;
    private int rating;

    public ApartmentBooking(String id, String name, String location, double price, Boolean status, int roomCount, int rating) {
        super(id, name, location, price, status);
        this.roomCount = roomCount;
        this.rating = rating;
    }
    public int getRoomCount() {
        return roomCount;
    }
    public int getRating() {
        return rating;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
}
