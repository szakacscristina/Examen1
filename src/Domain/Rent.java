package Domain;

import java.util.Objects;

public class Rent extends Entity {
     String carId;
    double  numberOfDays;
    double kilometers;

    public Rent(int id, String carId, double numberOfDays, double kilometers) {
        super(id);
        this.carId = carId;
        this.numberOfDays = numberOfDays;
        this.kilometers = kilometers;

    }

    @Override
    public String toString() {
        return carId + " " + numberOfDays + " " + kilometers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCarId(), getNumberOfDays(), getKilometers());
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }


    public double getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(double number) {
        this.numberOfDays = numberOfDays;
    }
    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }


    }


