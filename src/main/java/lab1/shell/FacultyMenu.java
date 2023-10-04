package lab1.shell;

import lab1.batch.BatchLoader;
import lab1.log.TXTLogger;
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
                "ba - Batch add students",
                "bg - Batch graduate students",
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
                this.displayStudents(false);
                break;
            case "dg":
                this.displayStudents(true);
                break;
            case "i":
                // TODO toString
                this.isStudent();
                break;
            case "ba":
                this.batchAdd();
                break;
            case "bg":
                this.batchGraduate();
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
            System.out.println("Successfully added student");
            TXTLogger.get().Info("Added student: " + email);
        } catch (Exception e) {
            System.out.println("Failed to add student: " + e.getMessage());
            TXTLogger.get().Info("Failed to add student " + email + ": " + e);
            return;
        }


        try {
            this.university.saveSession();
        } catch (Exception e) {
            System.out.println("Failed to save state to disk. See logs");
            TXTLogger.get().Error("Failed to save session: " + e);
        }
    }

    private void graduateStudent() {
        this.printPrompt("Input EMail");
        String email = this.scanner.nextLine();

        System.out.println();

        Optional<Student> student = this.faculty.findStudent(email);
        if (!student.isPresent()) {
            System.out.println("Student not found");
            return;
        }

        student.get().graduate();
        System.out.println("Student successfully graduated");
        TXTLogger.get().Info("Graduated student: " + student.get().getEmail());

        try {
            this.university.saveSession();
        } catch (Exception e) {
            System.out.println("Failed to save state to disk. See logs");
            TXTLogger.get().Error("Failed to save session: " + e);
        }
    }

    private void displayStudents(boolean graduated) {
        System.out.println("Students:");

        ArrayList<Student> students = this.faculty.getStudents(graduated);
        if (students.isEmpty()) {
            System.out.println("Empty...");
            return;
        }
        students.forEach(student -> {
            System.out.println("------------");
            System.out.println(student);
        });
    }

    private void isStudent() {
        this.printPrompt("Input EMail");
        String email = this.scanner.nextLine();

        System.out.println();

        Optional<Student> student = this.faculty.findStudent(email);
        if (student.isPresent()) {
            System.out.println("Found student:");
            System.out.println(student.get());
        } else {
            System.out.println("No such student");
        }
    }

    private void batchAdd() {
        this.printPrompt("Input filename");
        String filepath = this.scanner.nextLine();

        System.out.println();

        ArrayList<Student> students;
        try {
            students = BatchLoader.loadNewStudents(filepath);
        } catch (Exception e) {
            System.out.println("Failed to load file: " + e.getMessage());
            TXTLogger.get().Error("Failed to load batch enrollment file " + filepath + ": " + e);
            return;
        }
        students.forEach(student -> {
            try {
                this.faculty.addStudent(student);
                TXTLogger.get().Info("Added batch student: " + student.getEmail());
            } catch (Exception e) {
                TXTLogger.get().Warn("Failed to add batch student " + student.getEmail() + ": " + e);
            }
        });

        System.out.println("Successfully added students");

        try {
            this.university.saveSession();
        } catch (Exception e) {
            System.out.println("Failed to save state to disk. See logs");
            TXTLogger.get().Error("Failed to save session: " + e);
        }
    }

    private void batchGraduate() {
        this.printPrompt("Input filename");
        String filepath = this.scanner.nextLine();

        System.out.println();

        ArrayList<String> students;
        try {
            students = BatchLoader.loadGraduationEmails(filepath);
        } catch (Exception e) {
            System.out.println("Failed to load file " + e.getMessage());
            TXTLogger.get().Error("Failed to load batch graduation file " + filepath + ": " + e);
            return;
        }

        students.forEach(email -> {
            Optional<Student> student = this.faculty.findStudent(email);
            if (!student.isPresent()) {
                TXTLogger.get().Warn("Failed to graduate batch student " + email + ": not found");
                return;
            }

            student.get().graduate();
            TXTLogger.get().Info("Graduated batch student: " + email);

        });

        System.out.println("Successfully graduated students");

        try {
            this.university.saveSession();
        } catch (Exception e) {
            System.out.println("Failed to save state to disk. See logs");
            TXTLogger.get().Error("Failed to save session: " + e);
        }
    }
}
