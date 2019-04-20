package Tests.DomainTests;

import Domain.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void settersShouldSetFieldsCorrectly() {
        Car car1 = new Car(1,"bmw",678, 67);
        car1.setModel("Test");
        car1.setBuyKilometers(675);
        car1.setRentPerDay(67);

        assertEquals(1, car1.getId());
        assertEquals("Test", car1.getModel());
        assertEquals("Test", car1.getBuyKilometers());
        assertEquals("1234567891234", car1.getRentPerDay());

    }

    @Test
    void toStringShouldReturnALongEnoughString(){
        Car client1 = new Car(1, "bmw",786,67);

        assertTrue(client1.toString().length() > 10);
    }
}