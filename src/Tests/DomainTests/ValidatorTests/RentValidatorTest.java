package Tests.DomainTests.ValidatorTests;

import Domain.Rent;
import Domain.RentValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RentValidatorTest {

    @Test
    public void validateRent() {
        RentValidator rentV= new RentValidator();
        Rent rent = new Rent(1, "A",2, 987);
        rentV.validate(rent);
        assertDoesNotThrow(() -> rentV.validate(rent));

    }
}