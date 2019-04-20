package Domain;

public class CarValidator implements IValidator<Car> {

    /**
     * Validates a car
     * @param car the car to validate
     * @throws RuntimeException if there are validation errors
     */
    public void validate(Car car){
        String errors = "";
        if(!car.getModel().matches( "[A-Z][a-zA-Z]*" )){
            errors +="The model contains only letters and starts with a big letter\n";
        }
        if(car.getBuyKilometers() < 0){
            errors += "The kilometers must be positive!\n";
        }
        if(car.getRentPerDay() < 0){
            errors += "The rent per day must be positive!\n";
        }

        if(!errors.isEmpty()){
            throw new ExceptionCar("Nu s-a validat"+errors);
        }
    }
}
