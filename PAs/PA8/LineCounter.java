/*
 * NAME: Tonia Le
 * PID: A15662706
 */

import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * Class LineCounter
 * 
 * @author Tonia Le
 * @since 03-04-21
 */
public class LineCounter {

    /* Constants */
    private static final int MIN_INIT_CAPACITY = 10;
    private static final int PERCENTAGE_MULT = 100;

    /**
     * Method to print the filename to the console
     *
     * @param filename filename to print
     */
    public static void printFileName(String filename) {
        System.out.println("\n" + filename + ":");
    }

    /**
     * Method to print the statistics to the console
     *
     * @param compareFileName name of the file being compared
     * @param percentage      similarity percentage
     */
    public static void printStatistics(String compareFileName, int percentage) {
        System.out.println(percentage + "% of lines are also in " + compareFileName);
    }

    /**
     * Main method.
     *
     * @param args names of the files to compare
     */

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Invalid number of arguments passed");
            return;
        }
        // Preprocessing: Read every file and create a HashTable
        int numArgs = args.length;
        // Create a hash table for every file
        HashTable[] tableList = new HashTable[numArgs];
        for (int i = 0; i < numArgs; i++) {

            HashTable newTable = new HashTable();
            File file = new File(args[i]);
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String read = scanner.nextLine();
                    newTable.insert(read);
                }
                scanner.close();
                tableList[i] = newTable;
            }
            catch(IOException e){
                System.out.println("IOException!: Cannot find file.");
            }
        }

        // Find similarities across files

        for (int i = 0; i < numArgs; i++) {

            printFileName(args[i]);
            for (int j = 0; j < numArgs; j++) {
                double similarities = 0;
                double numLines = 0;
                HashTable current = tableList[j];
                File file = new File(args[i]);
                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        numLines++;
                        String read = scanner.nextLine();
                        if (current.lookup(read)){
                            similarities++;
                        }
                    }
                    scanner.close();
                    // calculate matches
                    int percent = (int)(similarities/ numLines * PERCENTAGE_MULT);
                    if (j != i){
                        printStatistics(args[j], percent);
                    }
                }
                catch (Exception ignored){

                }
            }

        }
    }
}
