package Booking;

public class CarRentalBooking extends Booking {

    private String carType;
    private String carModel;
    private int rentLength;

    public CarRentalBooking(String id, String name, String location, double price, String startDate, String endDate,
                            String carType, String carModel, int rentLength) {
        super(id, name, location, price, startDate, endDate);
        this.carType = carType;
        this.carModel = carModel;
        this.rentLength = rentLength;
    }

    public String getCarType() {
        return carType;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getRentLength() {
        return rentLength;
    }

//      czy setRentLength jak chcemy narzucać?
//    public void setRentLength(int rentLength) {
//        this.rentLength = rentLength;
//    }

//    public boolean reserveService(){
//      tu będzie rezerwowanie samochodów
//    }

//    public boolean cancelBooking(){
//        tu anulowanie rezerwacji
//    }




}
