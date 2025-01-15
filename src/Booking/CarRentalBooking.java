package Booking;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CarRentalBooking extends Booking implements Serializable {

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
    public String getIdType() {
        return "CRT";
    }

    @Override
    public String toString() {
        return "Model: " + carModel + '\n'+
                "Car type: " + carType + '\n'+
                super.toString();
    }
}
