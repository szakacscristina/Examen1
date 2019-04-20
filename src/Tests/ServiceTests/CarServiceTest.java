package Tests.ServiceTests;

import Domain.Car;
import Domain.CarValidator;
import Repository.InMemoryRepository;
import Service.CarService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    @Test
    void addAndUpdateServiceShouldAddAndUpdateClients() {
        CarValidator validator = new CarValidator();
        InMemoryRepository repository = new InMemoryRepository(validator);
        CarService carService = new CarService(repository);
        Car car1 = new Car(1, "bmw",78, 10);
        Car car2 = new Car(2, "mercedes",78, 9);

        assertEquals(car1, carService.getAll().get(0));
        assertEquals(car2, carService.getAll().get(1));
        assertEquals(2, carService.getAll().size());
    }

    @Test
    void deleteServiceShouldRemoveClient() {
        CarValidator validator = new CarValidator();
        InMemoryRepository repository = new InMemoryRepository(validator);
        CarService carService = new CarService(repository);

        Car car1 = new Car(1, "bmw",78, 10);
        Car car2 = new Car(2, "mercedes",78, 9);
        carService.delete(1);
        carService.delete(2);
        assertEquals(0, carService.getAll().size());
        assertFalse(carService.getAll().size() != 0);
    }

    @Test
    void getAllServiceShouldShowAllClients() {
        CarValidator validator = new CarValidator();
        InMemoryRepository repository = new InMemoryRepository(validator);
        CarService clientService = new CarService(repository);

        Car car1 = new Car(1, "bmw",78, 10);
        Car car2 = new Car(2, "mercedes",78, 9);


        assertEquals(car1, clientService.getAll().get(0));
        assertEquals(car2, clientService.getAll().get(1));
        assertTrue(clientService.getAll().size() == 2);
    }
}