package Booking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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

    public static List<CarRentalBooking> getCarRentalsFromFile(String fileName) {
        List<CarRentalBooking> bookings = new ArrayList<>();
        List<String[]> data = Booking.loadDataFromFile(fileName);
        for (String[] part : data) {
                bookings.add(new CarRentalBooking(
                        part[1], part[2], Double.parseDouble(part[3]), LocalDate.parse(part[4]),
                        LocalDate.parse(part[5]), part[6], part[7]
                ));
        }
        return bookings;
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
