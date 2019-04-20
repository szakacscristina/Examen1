package Domain;

import java.util.Objects;

public class Car extends Entity{
    private String model;
    double buyKilometers;
    double rentPerDay;

    public Car(int id, String model, double buyKilometers, double rentPerDay) {
        super(id);
        this.model = model;
        this.buyKilometers = buyKilometers;
        this.rentPerDay = rentPerDay;
    }

    @Override
    public String toString() {
        return model + " " + buyKilometers + " " + rentPerDay + " ";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getModel(), getBuyKilometers(), getRentPerDay());
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getBuyKilometers() {
        return buyKilometers;
    }

    public void setBuyKilometers(double buyKilometers) {
        this.buyKilometers = buyKilometers;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

    public void setRentPerDay(double rentPerDay) {
        this.rentPerDay = rentPerDay;
    }


}