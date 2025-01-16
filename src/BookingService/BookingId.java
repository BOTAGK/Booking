package BookingService;

import java.io.Serializable;

public class BookingId implements Serializable {
    public enum Prefix {
        APT,
        CRT,
        EVT
    }

    private Prefix prefix;
    private long index;

    // Creates an invalid id of a specific type
    public BookingId(Prefix prefix) {
        this(prefix, -1);
    }

    public BookingId(Prefix prefix, long index) {
        this.prefix = prefix;
        this.index = index;
    }

    public boolean matchesType(BookingId other) {
        return this.prefix.equals(other.prefix);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookingId other = (BookingId) obj;
        return this.index == other.index && this.prefix == other.prefix;
    }

    @Override
    public int hashCode() {
        return prefix.hashCode() * 31 + Long.hashCode(index);
    }

    @Override
    public String toString() {
        return this.prefix.toString() + this.index;
    }
}