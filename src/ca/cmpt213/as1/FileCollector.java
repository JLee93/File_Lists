package ca.cmpt213.as1;

import ca.cmpt213.as1.FileCollectorHelperClasses.CollectionOfFiles;
import ca.cmpt213.as1.FileCollectorHelperClasses.FileProcessor;

import java.io.File;
import java.util.*;

/**
 * Created by Jonathan on 5/26/2016.
 */

/**
 * This Class takes the number of collections, size of each collection, and source file as arguments and builds
 * a list of collections of files. Any files that cannot fit into any of the collections will be put in the extra collection
 */
public class FileCollector {

    private static final int INFINITESIZE = -1;
    private static final int BYTESTOMEGABYTE = 1048576;

    public static void main(String args[]) {

        if (args.length <= 2) {

            System.out.println("More arguments are required!");
            return;

        }

        int collections = Integer.parseInt(args[0]);
        long collectionSize = Long.parseLong(args[1]);
        File sourceFile = new File(args[2]);

        if (collections < 0 || collections > 1000) {

            System.out.println("The number of collections must be between 0 and 1000");
            return;

        }

        if (collectionSize < 0 || collectionSize > 1000000000000L) {

            System.out.println("The size of each collection must be between 0 and 1000000000000");
            return;

        }

        if (!sourceFile.exists() || !sourceFile.isFile()) {

            System.out.println("That path does not provide a file or the file doesn't exist!");
            return;

        }

        List<CollectionOfFiles> fileCollections = buildCollections(collections, collectionSize, sourceFile);

    }

    public static List<CollectionOfFiles> buildCollections(int collections, long collectionSize, File sourceFile) {

        List<CollectionOfFiles> fileCollections = new ArrayList<>();

        System.out.println("Now building collection:");
        System.out.println("**************************");

        System.out.printf("%-25s%d%n", "# Collections: ", collections);
        System.out.printf("%-25s%d%n", "Size per Collection: ", collectionSize);
        System.out.printf("%-25s%s%n", "Source file list: ", sourceFile.getAbsolutePath());
        System.out.println();

        //initialize the list of collections
        for (int i = 0; i < collections; i++) {

            //Collections are named by numbers, start at 1
            String name = "Collection " + (i + 1);

            List<File> files = new ArrayList<>();
            fileCollections.add(new CollectionOfFiles(name, files, 0, collectionSize));

        }

        List<File> extraFiles = new ArrayList<>();
        fileCollections.add(new CollectionOfFiles("Extra Files", extraFiles, 0, INFINITESIZE));

        List<File> listOfFiles = FileProcessor.processFiles(sourceFile);

        organizeCollections(fileCollections, listOfFiles);

        //print out the collections
        for (CollectionOfFiles fileCollection : fileCollections) {

            float sizeOfFilesMiB = (float) fileCollection.getSizeOfFiles() / BYTESTOMEGABYTE;

            System.out.printf(fileCollection.getName() + ": %.2f MiB (%,d bytes)%n", sizeOfFilesMiB, fileCollection.getSizeOfFiles());
            System.out.println("**************************************************");

            for (File file : fileCollection.getFiles()) {

                System.out.println(file.getAbsolutePath());

            }

            System.out.println();

        }

        return fileCollections;

    }

    public static void organizeCollections(List<CollectionOfFiles> fileCollections, List<File> listOfFiles) {

        Comparator<File> fileSortBySize = new Comparator<File>() {

            @Override
            public int compare(File f1, File f2) {

                return Long.compare(f2.length(), f1.length());

            }

        };

        Collections.sort(listOfFiles, fileSortBySize);
        List<Integer> indices = new ArrayList<>();

        for (File file : listOfFiles) {

            //Need to subtract 1 to prevent adding files to extra collection before other collections are full
            for (int i = 0; i < fileCollections.size() - 1; i++) {

                indices.add(i);

            }

            long tracker = indices.size();

            Collections.shuffle(indices);

            for (Integer indice : indices) {

                if (fileCollections.get(indice).hasRoom(file)) {

                    fileCollections.get(indice).addFile(file);

                    //Removing an indice indicates that a file was added to one of the collections
                    indices.remove(indice);
                    break;

                }

            }

            //The number of indices didn't decrease, indicating that the file couldn't be added to any collection
            //add the file into the extra collection
            if (tracker == indices.size()) {

                fileCollections.get(fileCollections.size() - 1).addFile(file);

            }

            indices.clear();


        }


    }

}
