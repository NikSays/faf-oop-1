package lab1.universityStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

import lab1.batch.BatchLoader;

public class Faculty implements Serializable {
    private final String name;
    private final String abbreviation;
    private final ArrayList<Student> students;
    private final StudyField studyField;

    public Faculty(String name, String abbreviation, StudyField studyField) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("empty name");
        }
        if (abbreviation.isEmpty()) {
            throw new Exception("empty abbreviation");
        }
        if (studyField == null) {
            throw new Exception("empty study field");
        }

        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;

        this.students = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public StudyField getStudyField() {
        return this.studyField;
    }

    public void addStudent(Student newStudent) throws Exception {
        boolean exists = this.students.stream().
                anyMatch(student -> student.getEmail().equals(newStudent.getEmail()));
        if (exists) {
            throw new Exception("student exists");
        }

        this.students.add(newStudent);
    }

    public Optional<Student> findStudent(String email) {
        return this.students.stream().
                filter(student -> student.getEmail().equals(email)).
                findFirst();
    }

    public ArrayList<Student> getStudents(boolean graduated) {
        ArrayList<Student> result = new ArrayList<>();
        if (graduated) {
            this.students.stream().
                    filter(Student::isGraduated).
                    forEach(result::add);
        } else {
            this.students.stream().
                    filter(student -> !student.isGraduated()).
                    forEach(result::add);
        }
        return result;
    }

    public String toString() {
        return String.format(
                "%s (%s)\n" + "Study Field: %s",
                this.name, this.abbreviation, this.studyField.getName()
        );
    }
}
