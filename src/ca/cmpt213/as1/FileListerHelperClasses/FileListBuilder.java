package ca.cmpt213.as1.FileListerHelperClasses;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jonathan on 5/24/2016.
 */

/**
 * This class has a static factory method to build a list of files given a directory and accepted extensions.
 */
public class FileListBuilder {

    public static List<File> buildFileList(File sourcePath, String[] extensions) {

        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File file) {

                String filename = file.getName().toLowerCase();

                if (extensions.length <= 0) {

                    if (file.isDirectory()) {

                        return false;

                    }

                    return true;

                }

                for (String extension : extensions) {

                    if (filename.endsWith(extension)) {

                        return true;

                    }

                }

                return false;

            }

        };

        List<File> listOfFiles = new ArrayList<>();

        //add all the accepted files in the source folder first
        Collections.addAll(listOfFiles, sourcePath.listFiles(filter));

        return buildFileListHelper(sourcePath, listOfFiles, filter);

    }

    //Recursive helper method
    private static List<File> buildFileListHelper(File sourcePath, List<File> listOfFiles, FileFilter filter) {


        if (sourcePath.listFiles() == null) {

            return listOfFiles;

        }

        for (File file : sourcePath.listFiles()) {

            if (file.isDirectory()) {

                Collections.addAll(listOfFiles, file.listFiles(filter));
                buildFileListHelper(file, listOfFiles, filter);

            }

        }

        return listOfFiles;

    }


}
