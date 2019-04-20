package Tests.DomainTests;

import Domain.Rent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentTest {

    @Test
    void getIdShouldReturnCorrectId() {
        Rent rent1 = new Rent(1,"D",8, 896);
        assertEquals(1, rent1.getId(), String.format("Returned: %s, Expected: %s", rent1.getId(), 1));
        Rent rent2 = new Rent(2, "G",78, 675);
        assertEquals(2, rent2.getId(), String.format("Returned: %s, Expected: %s", rent2.getId(), 2));
    }

    @Test
    void setIdShouldSetTheGivenId() {
        Rent rent1 = new Rent(1, "H",8, 896);
        Integer setId = 7;
        assertEquals(setId, rent1.getId(), String.format("Returned: %s, Expected: %s", rent1.getId(), setId));
    }

    @Test
    void constructorShouldSetAllTheFildsCorectly(){
        Rent rent1 = new Rent(1, "H",8, 9765);

        assertEquals(1, rent1.getId());
        assertEquals("Test", rent1.getCarId());
        assertEquals("Test", rent1.getNumberOfDays());
        assertEquals("Test", rent1.getKilometers());

    }

    @Test
    void settersShouldSetFieldsCorrectly(){
        Rent rent = new Rent(1, "U",9, 8765);
        rent.setCarId("Test");
        rent.setNumberOfDays(5);
        rent.setKilometers(8765);


        assertEquals(1, rent.getId());
        assertEquals("Test", rent.getCarId());
        assertEquals("Test", rent.getNumberOfDays());
        assertEquals("Test", rent.getKilometers());

    }
}