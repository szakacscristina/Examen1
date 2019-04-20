package Domain;

public class RentValidator implements IValidator<Rent> {

    /**
     *Validates a rent
     * @param rent the rent to validate
     * @throws RuntimeException if there are validation errors
     */
    public void validate(Rent rent){
        String errors = "";
        if(!rent.getCarId().matches( "[A-Z][a-zA-Z]*" )){
            errors +="The carId contains only letters and starts with a big letter\n";
        }
        if(rent.getNumberOfDays() < 0){
            errors += "The number of days must be  positive!\n";
        }
        if(rent.getKilometers() < 0){
            errors += "The kilometers must be  positive!\n";
        }
        if(!errors.isEmpty()){
            throw new ExceptionRent("Nu s-a validat"+errors);
        }
    }
}
