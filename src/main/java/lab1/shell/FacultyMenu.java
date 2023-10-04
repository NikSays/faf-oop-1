package lab1.shell;

import lab1.universityStructure.Faculty;
import lab1.universityStructure.Student;
import lab1.universityStructure.University;

import java.util.*;

public class FacultyMenu extends Menu {
    private final University university;
    private final Faculty faculty;

    public FacultyMenu(Scanner scanner, University university, Faculty faculty) {
        this.scanner = scanner;
        this.university = university;
        this.faculty = faculty;

        this.menuPrompt = "Faculty " + faculty.getAbbreviation();
        this.options = new ArrayList<>(List.of(
                "n  - Create a new student",
                "g  - Graduate student",
                "dc - Display current students",
                "dg - Display graduated students",
                "i  - Is a student in the faculty",
                "q  - Quit the faculty"));
    }

    protected boolean handleChoice(String choice) {
        switch (choice) {
            case "n":
                this.newStudent();
                break;
            case "g":
                this.graduateStudent();
                break;
            case "dc":
                // TODO toString
                this.displayStudents(false);
                break;
            case "dg":
                // TODO toString
                this.displayStudents(true);
                break;
            case "i":
                // TODO toString
                this.isStudent();
                break;
            case "q":
                System.out.println("Exiting faculty...");
                return false;
            default:
                this.printInvalid();
        }
        return true;
    }

    private void newStudent() {
        this.printPrompt("Input first name");
        String firstName = this.scanner.nextLine();

        this.printPrompt("Input last name");
        String lastName = this.scanner.nextLine();

        this.printPrompt("Input EMail");
        String email = this.scanner.nextLine();

        this.printPrompt("Input Date of Birth (YYYY-MM-DD)");
        String dateOfBirth = this.scanner.nextLine();

        System.out.println();

        try {
            this.faculty.addStudent(new Student(firstName, lastName, email, dateOfBirth));
        } catch (Exception e) {
            System.out.println("Failed to add student: " + e.getMessage());
            return;
        }

        System.out.println("Successfully added student");

        try {
            this.university.saveSession();
        } catch (Exception e) {
            System.out.println("Failed to save state to disk. See logs");
        }
    }

    private void graduateStudent() {
        this.printPrompt("Input EMail");
        String email = this.scanner.nextLine();

        System.out.println();

        Optional<Student> student = this.faculty.findStudent(email);
        if (student.isPresent()) {
            student.get().graduate();
            System.out.println("Student successfully graduated");
        } else {
            System.out.println("Student not found");
            return;
        }

        try {
            this.university.saveSession();
        } catch (Exception e) {
            System.out.println("Failed to save state to disk. See logs");
        }
    }

    private void displayStudents(boolean graduated) {
        System.out.println("Students:");

        ArrayList<Student> students = this.faculty.getStudents(graduated);
        if (students.isEmpty()) {
            System.out.println("Empty...");
            return;
        }
        System.out.println(students);
    }

    private void isStudent() {
        this.printPrompt("Input EMail");
        String email = this.scanner.nextLine();

        System.out.println();

        Optional<Student> student = this.faculty.findStudent(email);
        if (student.isPresent()) {
            System.out.println("Found student:");
            System.out.println(student);
        } else {
            System.out.println("No such student");
        }
    }
}
