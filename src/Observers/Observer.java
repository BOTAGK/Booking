package Observers;

import BookingService.BookingId;

public interface Observer {
    void update(BookingId bookingId);
}
