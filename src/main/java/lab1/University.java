package lab1;

import java.util.ArrayList;
import java.util.Optional;

public class University {
    private ArrayList<Faculty> faculties;

    public University() {
        this.faculties = new ArrayList<Faculty>();
    }

    public void addFaculty(Faculty faculty) {
        // TODO check existing abbreviation
        this.faculties.add(faculty);
    }

    public Optional<Faculty> findFaculty(String abbreviation) {
        return this.faculties.stream().
            filter(faculty -> faculty.getAbbreviation().equals(abbreviation)).
            findFirst();
    }

    public ArrayList<Faculty> getFaculties() {
        // Copy List to prevent editing
        return new ArrayList<Faculty>(this.faculties);
    }

    public ArrayList<Faculty> getFaculties(StudyField studyField) {
        ArrayList<Faculty> result = new ArrayList<Faculty>();
        this.faculties.stream().
            filter(faculty -> faculty.getStudyField().equals(studyField)).
            forEach(faculty -> result.add(faculty));
        return result;
    }

    public Optional<Faculty> findStudentFaculty(String email) {
        return this.faculties.stream().
            filter(faculty -> faculty.isStudentHere(email)).
            findFirst();
    }
}
