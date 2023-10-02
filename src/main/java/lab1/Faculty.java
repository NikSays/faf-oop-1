package lab1;

import java.util.ArrayList;

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
}
