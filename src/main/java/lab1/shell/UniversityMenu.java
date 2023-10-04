package lab1.shell;

import java.util.*;

import lab1.universityStructure.Faculty;
import lab1.universityStructure.StudyField;
import lab1.universityStructure.University;

public class UniversityMenu extends Menu {
    private University university;
    private HashMap<String, StudyField> studyFieldOptions;

    public UniversityMenu(Scanner scanner, University university) {
        this.scanner = scanner;
        this.university = university;

        this.menuPrompt = "University";
        this.options = new ArrayList<String>(List.of(
                "n - Create a new faculty",
                "s - Search a student's faculty",
                "a - Print all faculties",
                "f - Print all faculties in a study field",
                "e - Enter a faculty's menu",
                "q - Quit the program"));

        this.studyFieldOptions = new HashMap<>(Map.of(
            "f", StudyField.FOOD_TECHNOLOGY,
            "m", StudyField.MECHANICAL_ENGINEERING,
            "s", StudyField.SOFTWARE_ENGINEERING,
            "u", StudyField.URBANISM_ARCHITECTURE,
            "v", StudyField.VETERINARY_MEDICINE
        ));
    }

    protected boolean handleChoice(String choice) {
        switch (choice) {
            case "n":
                newFaculty();
                break;

            case "s":
                searchStudent();
                break;

            case "a":
                this.university.getFaculties().
                        forEach(faculty -> System.out.println(faculty.getName()));
                break;

            case "f":
                printStudyFieldOptions();
                this.printPrompt("Input study field");
                String sfString = this.scanner.nextLine();
                StudyField sf = this.studyFieldOptions.get(sfString);
                this.university.getFaculties(sf).
                        forEach(faculty -> System.out.println(faculty.getName()));
                break;

            case "e":
                break;

            case "q":
            System.out.println("Exiting...");
            return false;

            default:
            this.printInvalid();
        }
        return true;
    }

    private void printStudyFieldOptions() {
        System.out.println("Available Study Fields:");
        this.studyFieldOptions.entrySet().
        forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue().getName()));
    }

    private void newFaculty() {
        this.printPrompt("Input faculty name");
        String name = this.scanner.nextLine();

        this.printPrompt("Input faculty abbreviation");
        String abbr = this.scanner.nextLine();

        this.printStudyFieldOptions();
        this.printPrompt("Input study field");
        String sfString = this.scanner.nextLine();
        StudyField sf = this.studyFieldOptions.get(sfString);

        try {
            this.university.addFaculty(new Faculty(name, abbr, sf));
        } catch (Exception e) {
            System.out.println("Failed to add faculty: " + e.getMessage());
        }

        try {
            this.university.saveSession();
        } catch (Exception e) {
            System.out.println("Failed to save state to disk. See logs");
        }
    }

    private void searchStudent() {
        this.printPrompt("Input EMail");
        String email = this.scanner.nextLine();
        Optional<Faculty> studentsFaculty = this.university.getFaculties().stream().
                filter(faculty -> faculty.isStudentHere(email)).
                findFirst();
        if (studentsFaculty.isPresent()) {
            System.out.println("Student found in faculty: " + studentsFaculty.get().getName());
        } else {
            System.out.println("Student not found in any faculty");
        }
    }
}
