package Observers;

import BookingService.BookingId;

public interface Observable {
    public void addObserver(Observer observer, BookingId bookingId);
    public void removeObserver(Observer observer, BookingId bookingId);
    public void notifyObservers(BookingId bookingId);
}
