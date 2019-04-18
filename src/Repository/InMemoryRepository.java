package Repository;

import Domain.Entity;
import Domain.IValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<T extends Entity> implements IRepository<T> {
    private Map<Integer, T> storage = new HashMap<>();
    private IValidator<T> validator;

    /**
     * Instantiates a repository
     * @param validator the validator for this repository
     */
    public InMemoryRepository(IValidator<T> validator){
        this.validator = validator;
    }

    /**
     * Find the entity with a given id
     * @param id the id of the entity to find
     * @return the id of the entity searched
     */
    public T findById(Integer id){
        return  storage.get(id);
    }

    /**
     * Add an entity to the repository
     * @param entity the entity to add
     * @throws ExceptionRepository if there already is an entity with the given id or the entity fails validation
     */
    public void add(T entity){
        if(storage.containsKey(entity.getId())) {
            throw new ExceptionRepository(String.format("The entity with id=%s exist", entity.getId()));
        }
        validator.validate(entity);
        storage.put(entity.getId(), entity);
    }
    /**
     *Update an entity to the repository
     * @param entity the entity to update
     * @throws ExceptionRepository if there already is an entity with the given id or the entity fails validation
     */
    public void update(T entity){
        if(!(storage.containsKey(entity.getId()))) {
            throw new ExceptionRepository(String.format("The entity with id=%s exist", entity.getId()));
        }
        validator.validate(entity);
        storage.put(entity.getId(), entity);
    }

    /**
     * Remove an entity to the repository
     * @param id the entity's id to remove
     * @throws ExceptionRepository if there is no entity with the given id or the entity fails validation
     */
    public void remove(Integer id) {
        if (!storage.containsKey(id)) {
            throw new ExceptionRepository("There is no entity with the given id to remove");
        }
        storage.remove(id);
    }

    /**
     * Show the list with all entities
     * @return all the entities
     */
    public List<T> getAll(){
        return  new ArrayList<>(storage.values());
    }

}
