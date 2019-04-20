package Service;

import Domain.Car;
import Domain.Entity;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CarService<T extends Entity> {

    private IRepository<Car> repository;
    private Stack<UndoRedoOperation<Car>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Car>> redoableOperations = new Stack<>();

    /**
     *  Instantiates a service
     * @param repository the repository used by the server
     */
    public CarService(IRepository<Car> repository) {
        this.repository = repository;
    }

    /**
     * Add a car to the repository
     * @param id the car's id to add
     * @param model the car's model to add
     * @param buyKilometers the car's kilometers to add
     * @param rentPerDay the car's rentPerDay to add
     */
    public void add(Integer id, String model, double buyKilometers, double rentPerDay){
        Car car = new Car(id, model, buyKilometers, rentPerDay);
        List<Car> Idcheck= repository.getAll();
        for (Car c: Idcheck) {
            if (c.getId().equals(id)) {
                throw new RuntimeException("error: existent Id");
            }
        }
        repository.add(car);
        undoableOperations.push(new AddOperation<>(repository, car));
    }

    /**
     * Update a car to the repository
     * @param id the car's id to add
     * @param model the car's model to add
     * @param buyKilometers the car's kilometers to add
     * @param rentPerDay the car's rentPerDay to add
     */
    public void update(Integer id, String model, double  buyKilometers, double rentPerDay) {
        Car carActual = repository.findById(id);
        Car carUpdate = new Car(id, model, buyKilometers,rentPerDay);
        List<Car> cars = new ArrayList<>(repository.getAll());
        for (Car c : cars) {
            if (id.equals(c.getId()) && !id.equals(carUpdate.getId())) {
                throw new RuntimeException("error: existent CNP");
            }
        }
        repository.update(carUpdate);
        undoableOperations.push(new UpdateOperation<>(repository, carUpdate, carActual));
    }
    /**
     * Remove the car with the given id
     * @param id the id of the client to remove
     */
    public void delete(Integer id) {
        Car car = repository.findById(id);
        repository.remove(id);
        undoableOperations.push(new RemoveOperation<>(repository, car));
    }

    /**
     * Show all the cars
     * @return the list with all the cars
     */
    public List<Car> getAll(){
        return repository.getAll();
    }

    /**
     * Search the car after the given input
     * @param option the input to search after
     * @return the cars which contain the given input
     */
    public List<Car> searchCar(String option){
        List<Car> carsFound = new ArrayList<>();
        for(Car car : repository.getAll()){
            if(car.toString().contains(option))
               carsFound.add(car);
        }
        return carsFound;
    }
    /**
     * Undo the last operation
     */
    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<Car> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableOperations.add(lastOperation);

        }
    }

    /**
     * Redo the last operation
     */
    public void redo() {
        if (!redoableOperations.empty()) {
            UndoRedoOperation<Car> lastOperation = redoableOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }
}
