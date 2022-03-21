/*

 * NAME: Tonia Le

 * PID: A15662706

*/
/**

 * The class Startup includes nine different methods:
 *                                                  1) arrEvenOdd
 *                                                  2) removeVowels
 *                                                  3) compoundMass
 *                                                  4) binarizeMatrix
 *                                                  5) encryptString
 *                                                  6) rotateArray
 *                                                  7) rotateTriplets
 *                                                  8) subsetChecker
 *                                                  9) numpadSRC

 *

 * @author Tonia Le

 * @since ${01-08-21}

 */

public class Startup {
    /* Declare constants and magic numbers */
    private final static int DIVISOR = 2;

    /**
     * The method arrEvenOdd takes in an array of integers and returns a boolean array. It checks
     * if odd positions contain an odd element and if even positions contain an even element.
     * If the index and value have the same parity, the element in the boolean array will be
     * true, else it will be false.
     *
     * @param arr An array of integers
     * @return Return an array of boolean, boolArray
     */

    public static boolean[] arrEvenOdd(int[] arr) {
        boolean[] boolArray = new boolean[arr.length];
        // loop through the array
        for (int ind = 0; ind < arr.length; ind++) {
            // check if odds are at even indices
            if (ind % DIVISOR == 0) {
                if (arr[ind] % DIVISOR == 0)
                    boolArray[ind] = true;
                else boolArray[ind] = false;
            }
            // check if evens are at odd indices
            if (ind % DIVISOR != 0) {
                if (arr[ind] % DIVISOR != 0)
                    boolArray[ind] = true;
                else boolArray[ind] = false;
            }
        }
        return boolArray;
    }

    /**
     * The method removeVowels takes in an input of string type and returns a string where all
     * vowels are removed.
     *
     * @param input is of string type
     * @return Returns the string input without vowels, output
     */

    public static String removeVowels(String input) {
        String output = "";
        // char array of both upper and lower case vowels
        char[] vowels = {'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'};
        // loop through individual elements of vowels array
        for (char c : vowels) {
            // loop through the string input
            for (int ind = 0; ind < input.length(); ind++) {
                // locate a character if it contains a character like in vowels array
                if (input.charAt(ind) == c) {
                    // remove the vowels
                    output = input.substring(0, ind) + input.substring(ind + 1);
                }
            }
        }
        return output;
    }

    /**
     * The method compoundMass takes in an input of string type, calculates the compound's mass
     * and returns it as an integer.
     *
     * @param compound is a string of the elements
     * @return Returns an integer which is the given compound's mass, totalMass
     */

    public static int compoundMass(String compound) {
        int totalMass = 0;
        int maxLetter = 64;
        int element = 2;
        for (int ind = 0; ind < compound.length(); ind = ind + element) {
            char currentLetter = compound.charAt(ind);
            int elementMass = currentLetter - maxLetter;
            int elementCount = Character.getNumericValue(compound.charAt(ind + 1));
            totalMass = totalMass + (elementMass * elementCount);
        }
        return totalMass;
    }

    /**
     * The method binarizeMatrix takes in a 2-dimensional matrix and a threshold.
     * It returns the binarized matrix.
     *
     * @param matrix    is a 2-dimensional matrix of doubles
     * @param threshold is of a double type
     * @return returns a new matrix with elements of values 0 or 1 based on its relationship to
     * the given threshold, newMatrix
     */

    public static int[][] binarizeMatrix(double[][] matrix, double threshold) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] newMatrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] < threshold) {
                    newMatrix[i][j] = 0;
                } else {
                    newMatrix[i][j] = 1;
                }
            }
        }
        return newMatrix;
    }

    public static String encryptString(String input){
        return null;
    }
    /**
     * The method encryptString takes a string input and encrypts it by applying the Atbash
     * cipher on both upper and lowercase letters. The reversed encrypted string is returned.
     *
     * @param input is a string
     * @return returns a string, encryptedString
     */

//    public static String encryptString(String input){
//        String encryptedString = "";
//        // lower case letters
//        String alphabet = "abcdefghijklmnopqrstuvwxyz";
//        // loop through characters of input string
//        for (int i = input.length() - 1; i >= 0; i--){
//            // character at an index
//            char c = input.charAt(i);
//            // distances
//            int fromUpperA =  c - ('A');
//            int fromLowerA = c - ('a');
//            // if uppercase
//            if (Character.isUpperCase(c)){
//                char n = ('Z') - fromUpperA;
//                encryptedString.append(n);
//
//            }
//
//        }
//        return encryptedString;

    /**
     * The method rotateArray takes an array of doubles, arr, and a boolean direction.
     * It rotates the array in a specified direction one position.
     * If the boolean direction is true, it rotates left. Otherwise if false, it rotates right.
     * An array of these doubles will be returned.
     *
     * @param arr       is a an array containing elements of the double type
     * @param direction is a boolean specifying the direction
     * @return returns a rotated array, newArray
     */

    public static double[] rotateArray(double[] arr, boolean direction) {
        double[] newArray = new double[arr.length];
        // shifts to the right if direction is false
        if (!direction) {
            for (int ind = 0; ind < arr.length; ind++) {
                // store the last element
                double last = arr[arr.length - 1];
                // goes through in reverse
                for (int j = arr.length - 1; j > 0; j--) {
                    newArray[j] = newArray[j - 1];
                }
                // first element in the array is the original array's last element when
                // shifted right
                newArray[0] = last;
            }
        }
        // shifts left if true
        else {
            for (int ind = 0; ind < arr.length; ind++) {
                // store the first element
                double first = arr[0];
                int j;
                for (j = 0; j < arr.length - 1; j++) {
                    newArray[j] = newArray[j + 1];
                }
                // array's first element goes to the end
                newArray[j] = first;
            }
        }
        return newArray;
    }

        /**
         * The method rotateTriplets takes an array and rotates every 3 consecutive numbers.
         * The rotation pattern starts with rotating the first three numbers left, the following
         * three to the right and this continues in groups of threes.
         * If the amount of elements in the array cannot be divided by three without remainders
         * (having one or two remainders), there will be new rules.
         * If the last group only has 2 numbers, they will be swapped.
         * If only one number remains, it will remain unchanged.
         *
         * @param arr is an array of doubles
         * @return an array containing elements of double type will be returned after the rotation
         * operation pattern is applied
         */
//        public static double[] rotateTriplets (double[] arr){
//            int arrLength = arr.length - 1;
//            int set = arr.length % 6;
//            double[] rotatedArray = new double[arrLength - 1];
//
//            if (set == 0 || set == 3) {
//                for (int ind = 0; ind == (arrLength - 5); ind = ind + 6) {
//                    double beginRotate = arr[ind];
//                    rotatedArray[ind] = arr[ind + 1];
//                    for (int j = 5; j == (arrLength); j = j + 6) {
//                        double endRotate = arr[j];
//                        rotatedArray[j] = arr[j + 1];
//                    }
//                }
//            }
//
//            if (set == 1) {
//                for (int ind = 0; ind <= (arrLength - 5); ind = ind + 6) {
//                    double beginRotate = arr[ind];
//                    rotatedArray[ind] = arr[ind + 1];
//                    for (int j = 5; j <= (arrLength - 1); j = j + 6) {
//                        double endRotate = arr[j];
//                        rotatedArray[j] = arr[j + 1];
//                    }
//                }
//                rotatedArray[arrLength] = arr[arrLength];
//            }
//
//            if (set == 2) {
//                for (int ind = 0; ind <= (arrLength - 6); ind = ind + 6) {
//                    double beginRotate = arr[ind];
//                    rotatedArray[ind] = arr[ind + 1];
//                    for (int j = 5; j < (arrLength - 1); j = j + 6) {
//                        double endRotate = arr[j];
//                        rotatedArray[j] = arr[j + 1];
//                    }
//                }
//                double swap = arr[arrLength];
//                rotatedArray[arrLength] = arr[arrLength - 1];
//                rotatedArray[arrLength - 1] = swap;
//            }
//            return rotatedArray;
//        }

//        // tester for q1
//        public static void main (String[]args){
//            double[] tester = {1.1, 2.2, 3.3, 4.4, 5.5};
//            double[] result = rotateTriplets(tester);
//            for (int ind = 0; ind < result.length; ind++) {
//                System.out.println(result[ind]);
//            }
        //for (double doo : result)
//        boolean[] newArray = new boolean[5];
//        newArray = arrEvenOdd(new int[]{0, 1, 2, 2, 1, 0});
//        for (boolean boo : newArray) {
//            System.out.println(boo);


        /**
         * The method numpadSRC checks if all digits in the input integer are in the same row or
         * column of the given numpad.
         *
         * @param num is an integer that needs to be checked
         * @return a boolean is returned if all the digits of num are in the same row or columns of
         * the numpad
         */
//    public static boolean numpadSRC(int num){
//        if (num == 0){
//            return true;
//        }
//    }
    public static double[] rotateTriplets (double[] arr){
        return null;
    }
    public static boolean subsetChecker(int[] set1, int[] set2){
        return false;
    }
   public static boolean numpadSRC(int num){
       return false;
   }
}
