package lab1;

import java.io.IOException;

import lab1.log.Level;
import lab1.log.TXTLogger;

public class Main {
    public static void main(String[] args) throws IOException {
        TXTLogger.init("./log.txt", Level.DEBUG);



    }
}
