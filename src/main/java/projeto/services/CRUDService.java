package projeto.services;

public interface CRUDService<T, ID> {
	
    Iterable<T> listAll();

    T getById(ID id);

    T save(T entity);
    
    void delete(ID id);
    
    T update(T entity);
}
