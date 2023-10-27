package lab2.fileInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TXTInfo extends FileInfo {
    public TXTInfo (File file) throws IOException {
        super(file);
    }

    @Override
    public void printExtra() {
        int charsCount = 0;
        int wordsCount = 0;
        int linesCount = 0;

        Scanner sc;
        try {
            sc = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); //ew
        }

        while (sc.hasNext()) {
            String tmpStr = sc.nextLine();
            if (!tmpStr.isEmpty()) {
                charsCount += tmpStr.length();
                wordsCount += tmpStr.split(" ").length;
            }
            ++linesCount;
        }

        //display the count of characters, words, and lines
        System.out.println("Number of characters: " + charsCount);
        System.out.println("Number of words:      " + wordsCount);
        System.out.println("Number of lines:      " + linesCount);
    }
}
