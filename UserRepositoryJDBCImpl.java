import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJDBCImpl implements UserRepository{
    private final String SQL_FIND_BY_AGE="select * from driver where age=?";
    private final String SQL_FIND_BY_EMAIL = "select * from driver where email=?";
    private final String SQL_FIND_BY_PASSWORD = "select * from driver where password=?";
    private final String SQL_FIND_BY_GENDER = "select * from driver where gender=?";
    private final String SQL_FIND_ALL = "select * from driver";
    private final String SQL_FIND_ID = "select * from driver where id = ?";
    private final String SQL_SAVE = "insert into driver(first_name,last_name,age,email,password,gender,car_brand,driving_experience) values(?,?,?,?,?,?,?,?)";
    private final String SQL_DELETE_BY_ID = "delete from driver where id = ?";
    private final String SQL_UPDATE = "update driver set first_name=?,last_name=?,age=?,email=?,password=?,gender=?,car_brand=?,driving_experience=? where id=?";
    private final String SQL_DELETE_BY_EMAIL = "DELETE FROM driver WHERE email = ?";
    private final String SQL_FIND_BY_CAR_BRAND = "select * from driver where car_brand=?";
    private final String SQL_FIND_BY_DRIVING_EXPERIENCE = "select * from driver where driving_experience>=?";
    private Connection connection;
    public UserRepositoryJDBCImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<User> findByAge(Integer age) {
      List<User> users = new ArrayList<>();
      try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_AGE)){
          preparedStatement.setInt(1,age);
          ResultSet resultSet = preparedStatement.executeQuery();
          while(resultSet.next()){
              User user = new User(
                      resultSet.getLong("id"),
                      resultSet.getString("first_name"),
                      resultSet.getString("last_name"),
                      resultSet.getInt("age"),
                      resultSet.getString("email"),
                      resultSet.getString("password"),
                      resultSet.getString("gender"),
                      resultSet.getString("car_brand"),
                      resultSet.getInt("driving_experience")


              );
              users.add(user);
          }

      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
      return users;
    }

    @Override
    public List<User> findByEmail(String email) {
        List<User> users = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_EMAIL)) {
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("gender"),
                        resultSet.getString("car_brand"),
                        resultSet.getInt("driving_experience")

                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;

    }

    @Override
    public List<User> findByPassword(String password) {
        List<User> users = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_PASSWORD)) {
            preparedStatement.setString(1,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("gender"),
                        resultSet.getString("car_brand"),
                        resultSet.getInt("driving_experience")

                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;

    }

    @Override
    public List<User> findByGender(String gender) {
        List<User> users = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_GENDER)) {
            preparedStatement.setString(1,gender);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("gender"),
                        resultSet.getString("car_brand"),
                        resultSet.getInt("driving_experience")

                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("gender"),
                        resultSet.getString("car_brand"),
                        resultSet.getInt("driving_experience")

                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(Long id){
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ID)) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet  = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("gender"),
                        resultSet.getString("car_brand"),
                        resultSet.getInt("driving_experience")

                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    @Override
    public List<User> findByCarBrand(String carBrand) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CAR_BRAND)) {
            preparedStatement.setString(1, carBrand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("gender"),
                        resultSet.getString("car_brand"),
                        resultSet.getInt("driving_experience")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


    @Override
    public List<User> findByDrivingExperience(int drivingExperience) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_DRIVING_EXPERIENCE)) {
            preparedStatement.setInt(1, drivingExperience);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("gender"),
                        resultSet.getString("car_brand"),
                        resultSet.getInt("driving_experience")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void save(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getCarBrand());
            preparedStatement.setInt(8, user.getDrivingExperience());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_EMAIL)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void removeById(Long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getCarBrand());
            preparedStatement.setInt(8, user.getDrivingExperience());
            preparedStatement.setLong(9, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
