package Tests.DomainTests.ValidatorTests;

import Domain.Car;
import Domain.CarValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CarValidatorTest {

    @Test
    void validateErrorsShouldNotThrowExceptions() {
        CarValidator carV= new CarValidator();
        Car car= new Car(1, "bmw", 675, 23.5);
        Car car1= new Car(2,"mercedes",785, 79);
        carV.validate(car);
        assertDoesNotThrow(() -> carV.validate(car));
        assertDoesNotThrow(() -> carV.validate(car1));
    }

}