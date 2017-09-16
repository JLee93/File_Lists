# File_Lists

There are two main java programs here.

## File_Lister

This program is used for building a list of files with certain extensions and placing them into an output file.

It takes a source directory, an output file, and a number of extensions separated by spaces as command line arguments and then builds a list of files from the source directory which have any of the extensions provided. Finally it writes the list into the target file.

The prompt format would be like so
```
java ca.cmpt213.as1.FileLister {source directory} {output file} [list of extensions separated by spaces]
```
Example:
```
java ca.cmpt213.as1.FileLister C:/Test/ManyExtensions C:/Test/output.txt .mp3 .wav
```

## File_Collector

This program is used for organzing a list of files into separate collections(or containers) of a certain size. Any files that cannot fit into any of the collections will be put in the extra collection. All of this is output into the console. The program does not create any files.

It takes a number of collections, size of each collection, and source file (which has the list of files) as arguments and builds a list of collections of files. A source file that has a list of files? That's right, the program is supposed to be used on the output file created by File_lister!

The prompt format would be like so
```
java ca.cmpt213.as1.FileCollector {number of collections} {size of collections in bytes} {source file}
```
Example:
```
java ca.cmpt213.as1.FileCollector 3 30000000 C:/Test/output.txt
```
