package Booking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import BookingService.BookingId;

public class CarRentalBooking extends Booking {

    private String carType;
    private String carModel;
    
    public CarRentalBooking() {}

    public CarRentalBooking(String name, String location, double price, LocalDate startDate, LocalDate endDate,
                            String carType, String carModel) {
        super(name, location, price, startDate, endDate);
        this.carType = carType;
        this.carModel = carModel;
    }

    public String getCarType() {
        return carType;
    }

    public String getCarModel() {
        return carModel;
    }

    public long getRentLength() {
        return ChronoUnit.DAYS.between(getStartDate(), getEndDate());
    }

    @Override
    public BookingId.Prefix getIdPrefix() {
        return BookingId.Prefix.CRT;
    }

    @Override
    public String toString() {
        return "Model: " + carModel + '\n'+
                "Car type: " + carType + '\n'+
                super.toString();
    }
}
