import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    List<T> findAll();
    Optional<T> findById(Long id);

    void save(T t);
    void delete(T t);
    void removeById(int id);
    void update(T t);
}
