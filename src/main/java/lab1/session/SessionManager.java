package lab1.session;

import java.util.ArrayList;

import lab1.universityStructure.Faculty;

public interface SessionManager {
    public void save(ArrayList<Faculty> faculties) throws Exception;
    public ArrayList<Faculty> load() throws Exception;
}
