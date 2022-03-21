/*
 * Name: Tonia Le
 * PID:  A15662706
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Search Engine implementation.
 *
 * @author Tonia Le
 * @since  02-18-21
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     *
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
                                             ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String[] cast = scanner.nextLine().split(" ");
                String[]studios = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                // populate movie tree // cast , movies
                for (int ind = 0; ind < cast.length; ind++) {
                    populate(movieTree, cast[ind], movie);
                }
                // populate studio tree // studio , movies
                for (int ind = 0; ind < studios.length; ind++) {
                    populate(studioTree, studios[ind], movie);
                }
                // populate rating tree // cast , ratings
                for (int ind = 0; ind < cast.length; ind++) {
                    populate(ratingTree, cast[ind], rating);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Helper function to populate tree
     *
     * @param tree
     * @param key
     * @param value
     */
    private static void populate(BSTree<String> tree, String key, String value) {
        // insert if key not present
        if (!tree.findKey(key.toLowerCase())) {
            tree.insert(key.toLowerCase());
        }
        // insert the data at given key if not present
        if (!tree.findDataList(key.toLowerCase()).contains(value)) {
            tree.insertData(key.toLowerCase(), value);
       }
    }

    /**
     * Search a query in a BST
     *
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {
        // process query
        String[] keys = query.toLowerCase().split(" ");
        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful
        if (keys.length > 1) {
            String keyString = "";
            for (int ind = 0; ind < keys.length; ind++) {
                keyString += keys[ind] + " ";
            }
            LinkedList<String> document = new LinkedList<>();
            for (int ind = 0; ind < keys.length; ind++) {
                LinkedList<String> findList = searchTree.findDataList(keys[ind]);
                if (document.isEmpty()) {
                    document.addAll(findList);
                }
                // retain the overlapping elements
                else {
                    document.retainAll(findList);
                }
            }
            //
            print(keyString.substring(0, keyString.length() - 1), document);
            for (int j = 0; j < keys.length; j++) {
                LinkedList<String> lst3= searchTree.findDataList(keys[j]);
                boolean bol = lst3.removeAll(document);
                // print when list is empty
                if (!lst3.isEmpty()) {
                    print(keys[j], lst3);
                }
            }
        }

        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        else {
            for (int i = 0; i < keys.length; i++) {
                LinkedList<String> allElse = searchTree.findDataList(keys[i]);
                print(keys[i], allElse);
            }
        }
    }

    /**
     * Print output of query
     *
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                               + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // initialize search trees
        // process command line arguments
        BSTree<String> movieTree=new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        String query = "";

        for (int i = 2; i < args.length; i++) {
            query += args[i] + " ";
        }
        query =  query.substring(0, query.length()-1);

        // populate search trees
        populateSearchTrees(movieTree, studioTree, ratingTree, fileName);
        // choose the right tree to query
        if (searchKind == 0) {
            searchMyQuery(movieTree, query);
        }
        if (searchKind == 1) {
            searchMyQuery(studioTree, query);
        }
        if (searchKind == 2) {
            searchMyQuery(ratingTree, query);
        }
    }
}
