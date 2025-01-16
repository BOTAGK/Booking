package BookingService.FilterStrategy;

import Booking.Booking;
import BookingService.BookingId;
import Util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class PriceFilterStrategy implements FilterStrategy {
    private double minPrice;
    private double maxPrice = Double.MAX_VALUE;


    public PriceFilterStrategy(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("minPrice cannot be greater than maxPrice");
        }

        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
//    tutaj by mozna chyba wlasny blad, że max<min (ale nw czy to po tabakowowemu)

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    @Override
    public List<Pair<Booking, BookingId>> filter(List<Pair<Booking, BookingId>> entries) {
        return entries.stream()
            .filter(entry -> entry.first.getPrice()>=minPrice)
            .filter(entry -> entry.first.getPrice()<=maxPrice)
            .collect(Collectors.toList());
    }
}
