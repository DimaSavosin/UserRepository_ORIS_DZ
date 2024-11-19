import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainRepository {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "vimer_401";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ORIS_PRACTICA";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        UserRepository userRepository = new UserRepositoryJDBCImpl(connection);
        System.out.println("Введите число пользователей");
        int count = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < count; i++) {
            System.out.println("Введите данные пользователя:");

            System.out.print("Имя: ");
            String firstName = scanner.nextLine();

            System.out.print("Фамилия: ");
            String secondName = scanner.nextLine();

            System.out.print("Возраст: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Пароль: ");
            String password = scanner.nextLine();

            System.out.print("Пол: ");
            String gender = scanner.nextLine();

            System.out.print("Марка машины: ");
            String carBrand = scanner.nextLine();

            System.out.print("Опыт работы водителем (в годах): ");
            int drivingExperience = scanner.nextInt();
            scanner.nextLine();

            User user = new User(null, firstName, secondName, age, email, password, gender, carBrand, drivingExperience);

            userRepository.save(user);
            System.out.println("Пользователь успешно добавлен!");

        }

        System.out.println("\nВсе пользователи:");
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach(System.out::println);


        System.out.println("\nВведите email пользователя для удаления:");
        String emailToDelete = scanner.nextLine();

        User userToDelete = allUsers.stream()
                .filter(user -> user.getEmail().trim().equalsIgnoreCase(emailToDelete.trim()))
                .findFirst()
                .orElse(null);

        if (userToDelete != null) {
            userRepository.delete(userToDelete);
            System.out.println("Пользователь с email " + emailToDelete + " успешно удален.");
        } else {
            System.out.println("Пользователь с таким email не найден.");
        }



        System.out.println("\nПользователи с возрастом 20:");
        List<User> usersByAge = userRepository.findByAge(20);
        usersByAge.forEach(System.out::println);


        System.out.println("\nПользователь с email 'svs@gmail.com':");
        List<User> usersByEmail = userRepository.findByEmail("svs@gmail.com");
        usersByEmail.forEach(System.out::println);


        System.out.println("\nПользователи женского пола:");
        List<User> usersByGender = userRepository.findByGender("Женщина");
        usersByGender.forEach(System.out::println);


        System.out.println("\nПользователи с паролем '123':");
        List<User> usersByPassword = userRepository.findByPassword("123");
        usersByPassword.forEach(System.out::println);


        System.out.println("\nПользователь с ID 1:");
        Optional<User> userById = userRepository.findById(1L);
        userById.ifPresent(System.out::println);

        System.out.println("\nПользователи с маркой машины 'Toyota':");
        List<User> usersByCarBrand = userRepository.findByCarBrand("Toyota");
        usersByCarBrand.forEach(System.out::println);

        System.out.println("\nПользователи с опытом работы водителем 5 лет и более:");
        List<User> usersByExperience = userRepository.findByDrivingExperience(5);
        usersByExperience.forEach(System.out::println);



        if (!allUsers.isEmpty()) {
            User userToUpdate = allUsers.get(0);
            userToUpdate.setFirstName("UpdatedFirstName");
            userToUpdate.setSecondName("UpdatedLastName");
            userRepository.update(userToUpdate);
            System.out.println("\nПользователь обновлен:");
            userRepository.findById(userToUpdate.getId()).ifPresent(System.out::println);
        }


        if (allUsers.size() > 1) {
            long userIdToDelete = allUsers.get(1).getId();
            userRepository.removeById(userIdToDelete);
            System.out.println("\nПользователь с ID " + userIdToDelete + " удален.");
        }



        System.out.println("\nОставшиеся пользователи:");
        List<User> remainingUsers = userRepository.findAll();
        remainingUsers.forEach(System.out::println);





    }
}
