package Service;

import Domain.Client;
import Domain.Entity;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClientService <T extends Entity> {

    private IRepository<Client> repository;
    private Stack<UndoRedoOperation<Client>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Client>> redoableOperations = new Stack<>();

    /**
     *  Instantiates a service
     * @param repository the repository used by the server
     */
    public ClientService(IRepository<Client> repository) {
        this.repository = repository;
    }

    /**
     * Add a client to the repository
     * @param id the client's id to add
     * @param name the client's name to add
     * @param firstName the client's first name to add
     * @param CNP the client's CNP to add
     * @param dateOfBirth the client's date of birth to add
     * @param dateOfRegistration the client's date of registration to add
     */
    public void add(Integer id, String name, String firstName, String CNP, String dateOfBirth, String dateOfRegistration){
        Client client = new Client (id, name, firstName, CNP, dateOfBirth, dateOfRegistration);
        List<Client> CNPcheck= repository.getAll();
        for (Client c: CNPcheck) {
            if (c.getCNP().equals(CNP)) {
                throw new RuntimeException("error: existent CNP");
            }
        }
        repository.add(client);
        undoableOperations.push(new AddOperation<>(repository, client));
    }

    /**
     * Update a client to the repository
     * @param id the client's id to update
     * @param name the client's name to update
     * @param firstName the client's first name to update
     * @param CNP the client's CNP to update
     * @param dateOfBirth the client's date of birth to update
     * @param dateOfRegistration the client's date of registration to update
     */
    public void update(Integer id, String name, String firstName, String CNP, String dateOfBirth, String dateOfRegistration) {
        Client clientActual = repository.findById(id);
        Client clientUpdate = new Client(id, name, firstName, CNP, dateOfBirth, dateOfRegistration);
        List<Client> clients = new ArrayList<>(repository.getAll());
        for (Client c : clients) {
            if (CNP.equals(c.getCNP()) && !CNP.equals(clientUpdate.getCNP())) {
                throw new RuntimeException("error: existent CNP");
            }
        }
        repository.update(clientUpdate);
        undoableOperations.push(new UpdateOperation<>(repository, clientUpdate, clientActual));
    }
    /**
     * Remove the client with the given id
     * @param id the id of the client to remove
     */
    public void delete(Integer id) {
        Client client = repository.findById(id);
        repository.remove(id);
        undoableOperations.push(new RemoveOperation<>(repository, client));
    }

    /**
     * Show all the clients
     * @return the list with all the clients
     */
    public List<Client> getAll(){
        return repository.getAll();
    }

    /**
     * Search the client after the given input
     * @param option the input to search after
     * @return the clients which contain the given input
     */
    public List<Client> searchClient(String option){
        List<Client> clientsFound = new ArrayList<>();
        for(Client client : repository.getAll()){
            if(client.toString().contains(option))
               clientsFound.add(client);
        }
        return clientsFound;
    }
    /**
     * Undo the last operation
     */
    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<Client> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableOperations.add(lastOperation);

        }
    }

    /**
     * Redo the last operation
     */
    public void redo() {
        if (!redoableOperations.empty()) {
            UndoRedoOperation<Client> lastOperation = redoableOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }
}
