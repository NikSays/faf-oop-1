package lab1.batch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import lab1.universityStructure.Student;

public class BatchLoader {
    public static ArrayList<Student> loadNewStudents(String filepath) throws Exception {
        ArrayList<Student> students = new ArrayList<>();

        FileReader reader = new FileReader(filepath);
        BufferedReader bufReader = new BufferedReader(reader);
        String studentLine;

        while ((studentLine = bufReader.readLine()) != null) {
            // Any amount of spaces after the comma
            String[] studentValues = studentLine.split(",\\s*");
            if (studentValues.length != 4) {
                // TODO log and skip?
                continue;
            }

            students.add(new Student(studentValues[0], studentValues[1], studentValues[2], studentValues[3]));
        }

        bufReader.close();
        return students;
    }

    public static ArrayList<String> loadGraduationEmails(String filepath) throws IOException {
        ArrayList<String> emails = new ArrayList<>();

        FileReader reader = new FileReader(filepath);
        BufferedReader bufReader = new BufferedReader(reader);
        String email;

        while ((email = bufReader.readLine()) != null) {
            emails.add(email);
        }

        bufReader.close();
        return emails;
    }
}
