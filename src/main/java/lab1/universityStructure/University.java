package lab1.universityStructure;

import java.util.ArrayList;
import java.util.Optional;

import lab1.session.SessionManager;

public class University {
    private ArrayList<Faculty> faculties;

    // Depends on any session manager, as long as it implements save and load.
    // Makes it easier to change format later.
    private SessionManager sessionManager;

    public University(SessionManager sessionManager) {
        this.sessionManager = sessionManager;

        try {
            this.faculties = this.sessionManager.load();
        } catch (Exception e) {
            // TODO log error
            this.faculties = new ArrayList<Faculty>();
        }
    }

    public void saveSession() throws Exception {
        this.sessionManager.save(faculties);
    }

    public void addFaculty(Faculty newFaculty) throws Exception {
        boolean exists = faculties.stream().
            anyMatch(faculty -> faculty.getAbbreviation().equals(newFaculty.getAbbreviation()));
        if (exists) {
            throw new Exception("faculty exists");
        }
        
        this.faculties.add(newFaculty);
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
