package Service;

import Domain.Entity;
import Repository.IRepository;

public class RemoveOperation<T extends Entity> extends UndoRedoOperation<T>{

        private T removeEntity;

        public RemoveOperation(IRepository<T> repository, T removeEntity) {
            super(repository);
            this.removeEntity = removeEntity;
        }

        @Override
        public void doUndo() {
            repository.add(removeEntity);
        }

        @Override
        public void doRedo() {
            repository.remove(removeEntity.getId());
        }
}
