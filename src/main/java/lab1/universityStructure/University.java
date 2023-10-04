package lab1.universityStructure;

import java.util.ArrayList;
import java.util.Optional;

import lab1.log.TXTLogger;
import lab1.session.SessionManager;

public class University {
    private ArrayList<Faculty> faculties;

    // Depends on any session manager, as long as it implements save and load.
    // Makes it easier to change format later.
    private final SessionManager sessionManager;

    public University(SessionManager sessionManager) {
        this.sessionManager = sessionManager;

        try {
            this.faculties = this.sessionManager.load();
        } catch (Exception e) {
            TXTLogger.get().Error("Failed to load state: " + e.getMessage());
            this.faculties = new ArrayList<>();
        }
    }

    public void saveSession() throws Exception {
        TXTLogger.get().Debug("Saving state");
        this.sessionManager.save(this.faculties);
    }

    public void addFaculty(Faculty newFaculty) throws Exception {
        boolean exists = this.faculties.stream().
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
        return new ArrayList<>(this.faculties);
    }

    public ArrayList<Faculty> getFaculties(StudyField studyField) {
        ArrayList<Faculty> result = new ArrayList<>();
        this.faculties.stream().
                filter(faculty -> faculty.getStudyField().equals(studyField)).
                forEach(result::add);
        return result;
    }

    public Optional<Faculty> findStudentFaculty(String email) {
        return this.faculties.stream().
                filter(faculty -> faculty.findStudent(email).isPresent()).
                findFirst();
    }
}
