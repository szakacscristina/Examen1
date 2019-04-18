package Service;

import Domain.Medicine;
//import Domain.Transaction;
import Repository.IRepository;

import java.util.*;

public class MedicineService {
    private IRepository<Medicine> repository;
   // private IRepository<Transaction> repositoryTransactions;
    private Stack<UndoRedoOperation<Medicine>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Medicine>> redoableOperations = new Stack<>();

    /**
     *  Instantiates a service
     * @param repository the repository used by the server
     */
   public MedicineService(IRepository<Medicine> repository) {
       this.repository = repository;
    }

    /**
     * Add a medicine to the repository
     * @param id the medicine's id to add
     * @param name the name of the medicine to add
     * @param firstName the first name of the medicine to add
     * @param producer the producer of the medicine to add
     * @param price the price of the medine to add
     * @param recipe the variable which decide if there is any recipe or not
     */
    public void add(int id, String name, String firstName, String producer, double price, boolean recipe){
        Medicine medicine = new Medicine(id, name, firstName, producer, price, recipe);
        repository.add(medicine);
        undoableOperations.push(new AddOperation<>(repository, medicine));
    }

    /**
     * Update a medicine to the repository
     * @param id the medicine's id to update
     * @param name the name of the medicine to update
     * @param firstName the first name of the medicine to update
     * @param producer the producer of the medicine to update
     * @param price the price of the medine to update
     * @param recipe the variable which decide if there is any recipe or not
     */
    public void update(int id, String name, String firstName, String producer, double price, boolean recipe){
        Medicine medicineUpdate = new Medicine(id, name, firstName, producer, price, recipe);
        Medicine medicineActual = repository.findById(id);
        repository.update(medicineUpdate);
        undoableOperations.push(new UpdateOperation<>(repository, medicineUpdate, medicineActual));
    }

    /**
     * Remove the medicine with the given id
     * @param id the id of the medicine to remove
     */
    public void delete(Integer id) {
        Medicine medicine = repository.findById(id);
        repository.remove(id);
        undoableOperations.push(new RemoveOperation<>(repository, medicine));
    }

    /**
     * Show the list with all the medicines
     * @return the list with all medicines
     */
    public List<Medicine> getAll(){
        return repository.getAll();

    }

    /**
     * Search a medicine after the given input
     * @param option the input to search the medicine
     * @return the medicines with the given input
     */
    public List<Medicine> searchMedicine(String option){
        List<Medicine> medicinesFound = new ArrayList<>();
        for(Medicine medicine : repository.getAll()){
            if(medicine.toString().contains(option))
                medicinesFound.add(medicine);
        }
        return medicinesFound;
    }

    /**
     *
     * @param givenPrice
     * @return
     */
    public List<Medicine> medicinesExpensive (Double givenPrice, Integer percent){
        for(Medicine medicine : repository.getAll()){
            double price = medicine.getPrice();
            if(price < givenPrice){
                price += price * percent/100;
                medicine.setPrice(price);
            }
        }
        return repository.getAll();
    }

    /**
     * Undo the last operation
     */
    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<Medicine> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableOperations.add(lastOperation);

        }
    }

    /**
     * Redo the last operation
     */
    public void redo() {
        if (!redoableOperations.empty()) {
            UndoRedoOperation<Medicine> lastOperation = redoableOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }
}
