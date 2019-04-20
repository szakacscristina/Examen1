package Service;

import Domain.Rent;
//import Domain.Transaction;
import Repository.IRepository;

import java.util.*;

public class RentService {
    private IRepository<Rent> repository;
   // private IRepository<Transaction> repositoryTransactions;
    private Stack<UndoRedoOperation<Rent>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Rent>> redoableOperations = new Stack<>();

    /**
     *  Instantiates a service
     * @param repository the repository used by the server
     */
   public RentService(IRepository<Rent> repository) {
       this.repository = repository;
    }

    /**
     * Add a rent to the repository
     * @param id the rent's id to add
     * @param carId the carId of the rent to add
     * @param numberOfDays the number of days of the rent to add
     * @param kilometers the kilometers of the rent to add
     */
    public void add(int id, String carId, double numberOfDays, double kilometers){
        Rent rent = new Rent(id, carId, numberOfDays, kilometers);
        repository.add(rent);
        undoableOperations.push(new AddOperation<>(repository, rent));
    }

    /**
     * Update a rent to the repository
     * * @param id the rent's id to add
     * @param carId the carId of the rent to add
     * @param numberOfDays the number of days of the rent to add
     * @param kilometers the kilometers of the rent to add

     */
    public void update(int id, String carId, double numberOfDays, double kilometers){
        Rent rentUpdate = new Rent(id, carId, numberOfDays, kilometers);
        Rent rentActual = repository.findById(id);
        repository.update(rentUpdate);
        undoableOperations.push(new UpdateOperation<>(repository, rentUpdate, rentActual));
    }

    /**
     * Remove the rent with the given id
     * @param id the id of the rent to remove
     */
    public void delete(Integer id) {
        Rent rent = repository.findById(id);
        repository.remove(id);
        undoableOperations.push(new RemoveOperation<>(repository, rent));
    }

    /**
     * Show the list with all the rents
     * @return the list with all rents
     */
    public List<Rent> getAll(){
        return repository.getAll();

    }

    /**
     * Search a rent after the given input
     * @param option the input to search the rent
     * @return the rents with the given input
     */
    public List<Rent> searchRent(String option){
        List<Rent> rentsFound = new ArrayList<>();
        for(Rent rent : repository.getAll()){
            if(rent.toString().contains(option))
                rentsFound.add(rent);
        }
        return rentsFound;
    }


    /**
     * Undo the last operation
     */
    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<Rent> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableOperations.add(lastOperation);

        }
    }

    /**
     * Redo the last operation
     */
    public void redo() {
        if (!redoableOperations.empty()) {
            UndoRedoOperation<Rent> lastOperation = redoableOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }
}
