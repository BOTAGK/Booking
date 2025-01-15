package Booking;

import java.io.Serializable;

public class BookingId implements Serializable {
    private String type;
    private long index;

    public BookingId(String type, long index) {
        this.type = type;
        this.index = index;
    }

    public String getType()
    { return this.type; }

    public boolean matchesType(BookingId other)
    { return this.type.equals(other.type); }

    public boolean equals(BookingId other)
    { return this.index == other.index && this.type == other.type; }

    public String toString()
    { return this.type + this.index; }
}