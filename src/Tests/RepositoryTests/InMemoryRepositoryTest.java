package Tests.RepositoryTests;

import Domain.Car;
import Domain.CarValidator;
import Domain.Entity;
import Repository.IRepository;
import Repository.InMemoryRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryRepositoryTest <T extends Entity>{

    @Test
    void addAndUpdateShouldAddAndUpdateCars() {
        IRepository<Car> repository = new InMemoryRepository<>(new CarValidator());
        Car car1 = new Car(1, "bmw",78, 10);
        Car car2 = new Car(2, "mercedes",78, 9);
        Car car1Duplicate = new Car(1, "TestFirst", 78, 10);

        repository.add(car1);
        repository.add(car2);
        assertEquals(car1, repository.getAll().get(0));
        assertEquals(2, repository.getAll().size());

        try{
            repository.add(car1Duplicate);
            fail("Exception not throw for client duplicates");
        } catch (RuntimeException rex){
            assertTrue(true);
        }
    }


    @Test
    void deleteShouldRemoveCars() {
        IRepository<Car> repository = new InMemoryRepository<>(new CarValidator());
        Car car1 = new Car(1, "bmw",78, 10);
        Car car2 = new Car(2, "mercedes",78, 9);

        repository.add(car1);
        repository.add(car2);
        repository.remove(car1.getId());
        repository.remove(car2.getId());
        assertEquals(0, repository.getAll().size());
        assertFalse(repository.getAll().size() != 0);
    }

    @Test
    void getAll() {
        IRepository<Car> repository = new InMemoryRepository<>(new CarValidator());
        Car car1 = new Car(1, "bmw",78, 10);
        Car car2 = new Car(2, "mercedes",78, 9);

        repository.add(car1);
        repository.add(car2);
        assertEquals(car1, repository.getAll().get(0));
        assertEquals(car2, repository.getAll().get(1));
        assertTrue(repository.getAll().size() == 2);
    }
}
