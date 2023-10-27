package lab2.fileInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CodeInfo extends FileInfo {
    public CodeInfo(File file) {
        super(file);
    }

    @Override
    public void printExtra() {
        int classCount = 0;
        int linesCount = 0;

        Scanner sc;
        try {
            sc = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        while (sc.hasNext()) {
            String tmpStr = sc.nextLine();
            if (tmpStr.matches("^(private |public |protected )?class.+")) {
                ++classCount;
            }
            ++linesCount;
        }

        System.out.println("Number of classes:    " + classCount);
        System.out.println("Number of lines:      " + linesCount);
    }
}
