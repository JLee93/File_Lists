package ca.cmpt213.as1;

import ca.cmpt213.as1.FileListerHelperClasses.FileListBuilder;
import ca.cmpt213.as1.FileListerHelperClasses.FileStatistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Jonathan on 5/23/2016.
 */

/**
 * This class takes a source directory, a target file, and a number of extensions separated by spaces as command line arguments
 * and then builds a list of files from the source directory which have any of the extensions provided. Finally it writes the
 * list into the target file.
 */
public class FileLister {

    public static void main(String args[]) {

        if (args.length <= 1) {
            System.out.println("A source path AND a target path is required.");
            return;
        }

        File sourcePath = new File(args[0]);
        File targetPath = new File(args[1]);

        //The first two arguments will always be the source path and target path
        String[] extensions = new String[args.length - 2];

        //Check if the directory exists
        if (!sourcePath.exists()) {
            System.out.println("Directory: " + sourcePath + " doesn't exist!");
            return;
        }

        //Check if the first argument is a directory
        if (!sourcePath.isDirectory()) {
            System.out.println("Please provide a directory, not a file.");
            return;
        }

        //Convert all the extension arguments to lower case
        for (int i = 0; i < args.length - 2; i++) {
            extensions[i] = args[i + 2].toLowerCase();
        }

        List<File> fileList;
        fileList = FileListBuilder.buildFileList(sourcePath, extensions);
        FileStatistics statistics = FileStatistics.buildStatistics(sourcePath, targetPath, extensions, fileList);
        statistics.display();
        writeToFile(targetPath, fileList);

    }

    public static void writeToFile(File targetPath, List<File> fileList) {

        //Find the
        try {
            PrintWriter writer = new PrintWriter(targetPath);

            System.out.println("Files found: ");
            System.out.println("*****************");

            for (File file : fileList) {
                System.out.println(file.getAbsolutePath());
            }

            System.out.println();
            System.out.println("Writing file list to output file : " + targetPath);

            for (File file : fileList) {
                writer.println(file.getAbsolutePath());
            }

            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("That file doesn't exist!");
        }

    }

}
