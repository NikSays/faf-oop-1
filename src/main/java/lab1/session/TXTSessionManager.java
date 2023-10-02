package lab1.session;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import lab1.universityStructure.Faculty;

public class TXTSessionManager implements SessionManager{
    private String filepath;

    public TXTSessionManager(String filepath) {
        this.filepath = filepath;
    }

    public void save(ArrayList<Faculty> faculties) throws IOException{
        FileOutputStream writeData = new FileOutputStream(filepath);
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
    
        writeStream.writeObject(faculties);
        writeStream.flush();
        writeStream.close();
    }

    public ArrayList<Faculty> load()  throws IOException, ClassNotFoundException {
        FileInputStream readData = new FileInputStream(filepath);
        ObjectInputStream readStream = new ObjectInputStream(readData);
    
        ArrayList<Faculty> result = (ArrayList<Faculty>) readStream.readObject();
        readStream.close();
        
        return result;
    }
}
