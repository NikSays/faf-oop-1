package lab1.shell;

import java.util.*;

import lab1.universityStructure.Faculty;
import lab1.universityStructure.StudyField;
import lab1.universityStructure.University;

public class UniversityMenu extends Menu {
    private final University university;
    private final HashMap<String, StudyField> studyFieldOptions;

    public UniversityMenu(Scanner scanner, University university) {
        this.scanner = scanner;
        this.university = university;

        this.menuPrompt = "University";
        this.options = new ArrayList<>(List.of(
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
                this.newFaculty();
                break;

            case "s":
                this.searchStudent();
                break;

            case "a":
                // TODO toString
                System.out.println("All Faculties:");

                if (this.university.getFaculties().isEmpty()) {
                    System.out.println("Empty...");
                    break;
                }

                this.university.getFaculties().
                        forEach(faculty -> System.out.println(faculty.getName()));
                break;

            case "f":
                // TODO toString
                this.printStudyFieldOptions();
                this.printPrompt("Input study field");
                String sfString = this.scanner.nextLine();
                StudyField sf = this.studyFieldOptions.get(sfString);

                System.out.println();

                System.out.println("Faculties in " + sf.getName() + ":");
                if (this.university.getFaculties().isEmpty()) {
                    System.out.println("Empty...");
                    break;
                }

                this.university.getFaculties(sf).
                        forEach(faculty -> System.out.println(faculty.getName()));
                break;

            case "e":
                this.enterFaculty();
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
        this.studyFieldOptions.forEach((key, value) -> System.out.println(key + " - " + value.getName()));
        System.out.println();
    }

    private void printFacultyOptions() {
        System.out.println("Available Faculty:");
        this.university.getFaculties().
                forEach(faculty -> System.out.println(faculty.getAbbreviation() + " - " + faculty.getName()));
        System.out.println();
    }

    private void newFaculty() {
        this.printPrompt("Input faculty name");
        String name = this.scanner.nextLine();

        this.printPrompt("Input faculty abbreviation");
        String abbr = this.scanner.nextLine();

        System.out.println();
        this.printStudyFieldOptions();
        this.printPrompt("Input study field");
        String sfString = this.scanner.nextLine();
        StudyField sf = this.studyFieldOptions.get(sfString);

        System.out.println();

        try {
            this.university.addFaculty(new Faculty(name, abbr, sf));
            System.out.println("Successfully added faculty");
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

        System.out.println();

        Optional<Faculty> studentsFaculty = this.university.getFaculties().stream().
                filter(faculty -> faculty.findStudent(email).isPresent()).
                findFirst();
        if (!studentsFaculty.isPresent()) {
            System.out.println("Student not found in any faculty");
            return;
        }

        System.out.println("Student found in faculty: " + studentsFaculty.get().getName());
    }

    private void enterFaculty() {
        this.printFacultyOptions();
        this.printPrompt("Input faculty abbreviation");
        String abbreviation = this.scanner.nextLine();

        Optional<Faculty> selectedFaculty = this.university.getFaculties().stream().
                filter(faculty -> faculty.getAbbreviation().equals(abbreviation)).
                findFirst();
        if (!selectedFaculty.isPresent()) {
            System.out.println("No such faculty abbreviation");
            return;
        }

        new FacultyMenu(this.scanner, this.university, selectedFaculty.get()).run();
    }
}
