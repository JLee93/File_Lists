package ca.cmpt213.as1.FileListerHelperClasses;

import java.io.File;
import java.util.List;

/**
 * Created by Jonathan on 5/23/2016.
 */

/**
 * This class takes the source path, target path, extensions and a list of files to build a list of statistics
 * and formats it for display
 */
public class FileStatistics {

    private static final int BYTESTOMEGABYTE = 1048576;

    private File sourcePath;
    private File targetPath;
    private String[] extensions;
    private int filesFound;
    private long sizeOfFiles;

    public FileStatistics(File sourcePath, File targetPath, String[] extensions, int filesFound, long sizeOfFiles) {

        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        this.extensions = extensions;
        this.filesFound = filesFound;
        this.sizeOfFiles = sizeOfFiles;

    }

    public static FileStatistics buildStatistics(File sourcePath, File targetPath, String[] extensions, List<File> files) {

        int filesFound = files.size();
        long sizeOfFiles = 0;

        for (File file : files) {

            sizeOfFiles = sizeOfFiles + file.length();

        }

        return new FileStatistics(sourcePath, targetPath, extensions, filesFound, sizeOfFiles);


    }

    public void display() {

        float sizeOfFilesMiB = (float) this.sizeOfFiles / BYTESTOMEGABYTE;

        System.out.println("Statistics found on files found: ");
        System.out.println("********************************");

        System.out.println("Source path: " + this.sourcePath);
        System.out.println("Target path: " + this.targetPath);
        System.out.print("Extensions: ");

        for (String extension : this.extensions) {

            System.out.print(extension + " ");

        }

        System.out.println();
        System.out.println("Files found: " + this.filesFound);
        System.out.printf("Total size: %.2f MiB (%,d bytes)%n", sizeOfFilesMiB, this.sizeOfFiles);
        System.out.println();


    }

}
