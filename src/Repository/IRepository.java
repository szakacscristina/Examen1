package Repository;

import Domain.Entity;

import java.util.List;

public interface IRepository <T extends Entity>{

        T findById(Integer id);
        void add(T entity);
        void update(T entity);
        void remove(Integer id);
        List<T> getAll();
}
