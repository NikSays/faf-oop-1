package lab1.batch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import lab1.universityStructure.Student;

public class BatchLoader {
    public static ArrayList<Student> loadNewStudents(String filepath) throws Exception{
        ArrayList<Student> students = new ArrayList<Student>();

        FileReader reader = new FileReader(filepath);
        BufferedReader bufReader = new BufferedReader(reader);
        String studentLine = null;
        
        while ((studentLine = bufReader.readLine()) != null) {   
            String[] studentValues = studentLine.split(",");
            if (studentValues.length != 4) {
                bufReader.close();
                throw new Error("malformed student in file");
            }
            
            LocalDate dateOfBirth = LocalDate.parse(studentValues[3]);
            students.add(new Student(studentValues[0], studentValues[1], studentValues[2], dateOfBirth));
        } 

        bufReader.close();
        return students;
    }

    public static ArrayList<String> loadGraduationEmails(String filepath) throws IOException{
        ArrayList<String> emails = new ArrayList<String>();

        FileReader reader = new FileReader(filepath);
        BufferedReader bufReader = new BufferedReader(reader);
        String email = null;
        
        while ((email = bufReader.readLine()) != null) {   
            emails.add(email);
        } 

        bufReader.close();
        return emails;
    }
}
