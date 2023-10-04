package lab1.session;

import java.util.ArrayList;

import lab1.universityStructure.Faculty;

// Allows extending session functionality.
// For example, session can be stored in Redis, and this cannot be abstracted with an 
// abstract class since no fields of functionality is common with BinaryFileManager
public interface SessionManager {
    void save(ArrayList<Faculty> faculties) throws Exception;

    ArrayList<Faculty> load() throws Exception;
}
