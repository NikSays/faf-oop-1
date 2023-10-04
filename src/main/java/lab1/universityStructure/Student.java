package lab1.universityStructure;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String enrollmentDate;
    private final String dateOfBirth;
    private boolean graduated;

    public Student(String firstName, String lastName, String email, String dateOfBirth) throws Exception {
        if (firstName.isEmpty()) {
            throw new Exception("empty first name");
        }
        if (lastName.isEmpty()) {
            throw new Exception("empty last name");
        }
        if (email.isEmpty()) {
            throw new Exception("empty email");
        }
        if (dateOfBirth.isEmpty()) {
            throw new Exception("empty date of birth");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;

        this.enrollmentDate = LocalDate.now().toString();
        this.graduated = false;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isGraduated() {
        return this.graduated;
    }

    public void graduate() {
        this.graduated = true;
    }

    public String toString() {
        return String.format(
                "Name: %s \n" + "EMail: %s \n" + "Date of birth: %s \n" +
                        "Enrollment date: %s \n" + "Graduated: %b",
                this.getName(),
                this.email, this.dateOfBirth,
                this.enrollmentDate, this.graduated);
    }
}