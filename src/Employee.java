public class Employee {
    public String getEmployeID() {
        return EmployeID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEducationalBAckground() {
        return EducationalBAckground;
    }

    public String getGender() {
        return Gender;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public String getAdress() {
        return Adress;
    }

    public String getPassword() {
        return Password;
    }

    String EmployeID;
    String FirstName;
    String LastName;
    String EducationalBAckground;
    String Age;
    String Gender;
    String DateOfBirth;
    String Password;

    public String getAge() {
        return Age;
    }

    public Employee(String employeID, String firstName, String lastName,
                    String educationalBAckground, String gender,
                    String dateOfBirth, String adress, String password, String age) {
        this.EmployeID = "Teach/" + employeID;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.EducationalBAckground = educationalBAckground;
        this.Gender = gender;
        this.DateOfBirth = dateOfBirth;
        this.Adress = adress;
        this.Password = password;
        this.Age = age;
    }
    public Employee(String employeID){
        this.EmployeID = employeID;
    }

    String Adress;

    public Employee() {

    }
}
