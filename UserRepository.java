import java.util.List;

public interface UserRepository extends CrudRepository<User>{
    List<User> findByAge(Integer age);
    List<User> findByEmail(String email);
    List<User> findByPassword(String password);
    List<User> findByGender(String gender);
    List<User> findByDrivingExperience(int drivingExperience);
    List<User> findByCarBrand(String carBrand);
}

