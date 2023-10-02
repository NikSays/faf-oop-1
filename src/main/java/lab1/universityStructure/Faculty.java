package lab1.universityStructure;

import java.util.ArrayList;
import java.util.Optional;

public class Faculty {
    private String name;
    private String abbreviation;
    private ArrayList<Student> students;
    private StudyField studyField;

    public Faculty(String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;

        this.students = new ArrayList<Student>();
    }

    public String getName() {
        return this.name;
    }

    public String getAbbreviation(){
        return this.abbreviation;
    }

    public StudyField getStudyField() {
        return this.studyField;
    }

    public void addStudent(Student student) {
        // TODO check existing email
        this.students.add(student);
    }

    public Optional<Student> findStudent(String email) {
        return this.students.stream().
            filter(student -> student.getEmail().equals(email)).
            findFirst();
    }

    public ArrayList<Student> getStudents(boolean graduated) {
        ArrayList<Student> result = new ArrayList<Student>();
        if (graduated) {
            this.students.stream().
                filter(student -> student.isGraduated()).
                forEach(student -> result.add(student));
        } else {
            this.students.stream().
                filter(student -> !student.isGraduated()).
                forEach(student -> result.add(student));
        }
        return result;
    }

    public boolean isStudentHere(String email) {
        return this.findStudent(email).isPresent();
    }
}
