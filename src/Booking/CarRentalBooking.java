package Booking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CarRentalBooking extends Booking {

    private String carType;
    private String carModel;

    public CarRentalBooking(String id, String name, String location, double price, LocalDate startDate, LocalDate endDate,
                            String carType, String carModel) {
        super(id, name, location, price, startDate, endDate);
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

    /*
    public boolean reserveService(){
      tu będzie rezerwowanie samochodów
    }


    public boolean cancelBooking(){
        tu anulowanie rezerwacji
    }
    */
}
