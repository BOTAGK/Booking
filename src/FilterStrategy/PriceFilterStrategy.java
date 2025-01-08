package FilterStrategy;

import Booking.Booking;

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
    public List<Booking> filter(List<Booking> bookings) {
        return bookings.stream()
                .filter(booking -> booking.getPrice()>=minPrice)
                .filter(booking -> booking.getPrice()<= maxPrice)
                .collect(Collectors.toList());
    }
}
