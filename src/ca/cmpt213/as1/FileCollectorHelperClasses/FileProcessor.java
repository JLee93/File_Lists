package ca.cmpt213.as1.FileCollectorHelperClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jonathan on 5/26/2016.
 */

/**
 * This class takes a text file that has a list of files and returns files that exist.
 * Assume that the text file has one path per line.
 */
public class FileProcessor {

    public static List<File> processFiles(File file) {

        String fileName;
        List<File> listOfFiles = new ArrayList<>();

        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                fileName = scanner.nextLine();

                File fileToAdd = new File(fileName);

                if (!fileToAdd.exists()) {

                    System.out.println("Warning: File does not exist (" + fileName + ").");

                }

                else {

                    listOfFiles.add(fileToAdd);

                }

            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("The file could not be found!");

        }

        return listOfFiles;

    }

}
