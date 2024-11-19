public class User {
    private String firstName;
    private int age;
    private String secondName;
    private Long id;
    private String email;
    private String password;
    private String gender;
    private String carBrand;
    private int drivingExperience;


    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(Long id, String firstName, String secondName, int age, String email, String password, String gender, String carBrand, int drivingExperience) {
       this.id = id;
        this.firstName = firstName;
        this.age = age;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.carBrand = carBrand;
        this.drivingExperience = drivingExperience;

    }


    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getSecondName() {
        return secondName;
    }

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getGender() {
        return gender;
    }
    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                ", secondName='" + secondName + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", drivingExperience=" + drivingExperience +
                '}';
    }

}
