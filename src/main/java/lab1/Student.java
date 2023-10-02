package lab1;

import java.util.Date;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Date   enrollmentDate;
    private Date   dateOfBirth;
    private boolean graduated;

    public Student(String firstName, String lastName, String email, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        
        this.enrollmentDate = new Date();
        this.graduated = false;
    }

    public String getName() {
        return this.lastName + this.firstName;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public boolean isGraduated() {
        return this.graduated;
    }

    public void graduate() {
        this.graduated = true;
    }
}