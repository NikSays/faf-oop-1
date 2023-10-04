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

    public Student(String firstName, String lastName, String email, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;

        this.enrollmentDate = LocalDate.now().toString();
        this.graduated = false;
    }

    public String getName() {
        return this.lastName + this.firstName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public boolean isGraduated() {
        return this.graduated;
    }

    public void graduate() {
        this.graduated = true;
    }
}