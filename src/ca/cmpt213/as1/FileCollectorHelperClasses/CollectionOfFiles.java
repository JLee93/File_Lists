package ca.cmpt213.as1.FileCollectorHelperClasses;

import java.io.File;
import java.util.List;

/**
 * Created by Jonathan on 5/27/2016.
 */

/**
 * This class provides an object which holds the information about a collection of files.
 */
public class CollectionOfFiles {

    private static final int INFINITESIZE = -1;

    private String name;
    private List<File> files;
    private long sizeOfFiles;
    private long maxSize;

    public CollectionOfFiles(String name, List<File> files, long sizeOfFiles, long maxSize) {

        this.name = name;
        this.files = files;
        this.sizeOfFiles = sizeOfFiles;
        this.maxSize = maxSize;

    }

    public String getName() {

        return this.name;

    }

    public List<File> getFiles() {

        return this.files;

    }

    public long getSizeOfFiles() {

        return this.sizeOfFiles;
    }

    public void addFile(File file) {

        if ((this.sizeOfFiles + file.length() > this.maxSize) && this.maxSize != INFINITESIZE) {

            System.out.println("The maximum size of the collection has been reached. " + file.getAbsolutePath() +
                    " cannot be added to " + this.name);

            return;

        }

        this.files.add(file);
        this.sizeOfFiles = this.sizeOfFiles + file.length();

    }

    public boolean hasRoom(File file) {

        if (this.maxSize == INFINITESIZE) {

            return true;

        }

        else if (this.sizeOfFiles + file.length() > this.maxSize) {

            return false;

        }

        else {

            return true;

        }

    }

}
