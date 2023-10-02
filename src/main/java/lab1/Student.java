package lab1;

import java.util.Date;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Date   enrollmentDate;
    private Date   dateOfBirth;

    public Student(String firstName, String lastName, String email, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;

        this.enrollmentDate = new Date();
    }
}