package lab1.universityStructure;

import java.time.LocalDate;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate   enrollmentDate;
    private LocalDate   dateOfBirth;
    private boolean graduated;

    public Student(String firstName, String lastName, String email, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        
        this.enrollmentDate = LocalDate.now();
        this.graduated = false;
    }

    public String getName() {
        return this.lastName + this.firstName;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDate getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public boolean isGraduated() {
        return this.graduated;
    }

    public void graduate() {
        this.graduated = true;
    }
}