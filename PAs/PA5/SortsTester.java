/*
 * NAME: Tonia Le
 * PID:  A15662706
 */
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SortsTester {
    Sorts<Integer> intArray;
    Sorts<Character> charArray;

    @org.junit.Before
    public void setUp() throws Exception {
        intArray = new Sorts<Integer>();
        charArray = new Sorts<Character>();

    }

    @org.junit.Test
    public void insertionIntSort() {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(7);
        nums.add(5);
        nums.add(6);
        nums.add(9);
        nums.add(4);
        nums.add(2);
        nums.add(3);
        System.out.println("Before Insertion Sort: " + nums);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(9);
        System.out.println("After Insertion Sort: " + expected);

        intArray.InsertionSort(nums, 0, 7);
        assertEquals(expected, nums);
    }

    @org.junit.Test
    public void insertionCharSort() {
        ArrayList<Character> charList = new ArrayList<Character>();
        charList.add('C');
        charList.add('D');
        charList.add('Y');
        charList.add('X');
        charList.add('A');
        charList.add('Z');
        charList.add('B');
        System.out.println("Before Insertion Sort: " + charList);
        ArrayList<Character> expected = new ArrayList<Character>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');
        expected.add('X');
        expected.add('Y');
        expected.add('Z');
        assertNotEquals(expected, charList);
        charArray.InsertionSort(charList, 0, 6);
        System.out.println("After Insertion Sort: " + expected);
        assertEquals(expected, charList);
    }

    @org.junit.Test
    public void subInsertionSort() {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(2);
        intList.add(3);
        intList.add(10);
        intList.add(9);
        intList.add(4);
        intList.add(12);
        intList.add(1);
        intList.add(3);
        System.out.println("Before Insertion Sort: " + intList);
        intArray.InsertionSort(intList, 2, 5);
        System.out.println("After Insertion Sort: " + intList);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(9);
        expected.add(10);
        expected.add(12);
        expected.add(1);
        expected.add(3);

        assertEquals(expected, intList);
    }

    @org.junit.Test
    public void reverseInsertionSort() {
        ArrayList<Character> charList = new ArrayList<Character>();
        charList.add('Z');
        charList.add('Y');
        charList.add('X');
        charList.add('D');
        charList.add('C');
        charList.add('B');
        charList.add('A');
        System.out.println("Before Insertion Sort: " + charList);
        ArrayList<Character> expected = new ArrayList<Character>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');
        expected.add('X');
        expected.add('Y');
        expected.add('Z');
        assertNotEquals(expected, charList);
        charArray.InsertionSort(charList, 0, 6);
        System.out.println("After Insertion Sort: " + expected);
        assertEquals(expected, charList);
    }

    @org.junit.Test
    public void mergeCharSort() {
        ArrayList<Character> charList = new ArrayList<Character>();
        charList.add('C');
        charList.add('D');
        charList.add('Y');
        charList.add('X');
        charList.add('A');
        charList.add('Z');
        charList.add('B');
        System.out.println("Before Merge Sort: " + charList);
        charArray.MergeSort(charList, 0, 6);

        ArrayList<Character> expected = new ArrayList<Character>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');
        expected.add('X');
        expected.add('Y');
        expected.add('Z');
        System.out.println("After Merge Sort: " + charList);
        assertEquals(expected, charList);
    }

    @org.junit.Test
    public void intMerge() {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(5);
        intList.add(2);
        intList.add(7);
        intList.add(6);
        intList.add(1);
        intList.add(9);
        intList.add(3);
        intList.add(5);
        System.out.println("Before Merge Sort: " + intList);
        intArray.MergeSort(intList, 0, 7);
        System.out.println("After Merge Sort: " + intList);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(9);
        assertEquals(expected, intList);
    }

    @org.junit.Test
    public void sortedMergeTest() {
        ArrayList<Character> charList = new ArrayList<Character>();
        charList.add('A');
        charList.add('B');
        charList.add('C');
        charList.add('D');
        charList.add('X');
        charList.add('Y');
        charList.add('Z');
        System.out.println("Before TimSort: " + charList);
        charArray.MergeSort(charList, 0, 6);
        System.out.println("After TimSort: " + charList);

        ArrayList<Character> expected = new ArrayList<Character>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');
        expected.add('X');
        expected.add('Y');
        expected.add('Z');
        assertEquals(expected, charList);
    }

    @org.junit.Test
    public void quickSort() {
        ArrayList<Character> charList = new ArrayList<Character>();
        charList.add('C');
        charList.add('D');
        charList.add('Y');
        charList.add('X');
        charList.add('A');
        charList.add('Z');
        charList.add('B');
        System.out.println("Before Quick Sort: " + charList);
        charArray.QuickSort(charList, 0, 6);

        ArrayList<Character> expected = new ArrayList<Character>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');
        expected.add('X');
        expected.add('Y');
        expected.add('Z');
        System.out.println("After Quick Sort: " + charList);
        assertEquals(expected, charList);
    }

    @org.junit.Test
    public void intQuickSort() {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(5);
        intList.add(2);
        intList.add(7);
        intList.add(6);
        intList.add(1);
        intList.add(9);
        intList.add(3);
        intList.add(5);
        System.out.println("Before Quick Sort: " + intList);
        intArray.QuickSort(intList, 0, 7);
        System.out.println("After Quick Sort: " + intList);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(9);
        assertEquals(expected, intList);
    }

    @org.junit.Test
    public void reversedQuickSortTest() {
        ArrayList<Character> charList = new ArrayList<Character>();
        charList.add('Z');
        charList.add('Y');
        charList.add('X');
        charList.add('D');
        charList.add('C');
        charList.add('B');
        charList.add('A');
        System.out.println("Before QuickSort: " + charList);
        charArray.QuickSort(charList, 0, 6);
        System.out.println("After QuickSort: " + charList);

        ArrayList<Character> expected = new ArrayList<Character>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');
        expected.add('X');
        expected.add('Y');
        expected.add('Z');
        assertEquals(expected, charList);
    }

    @org.junit.Test
    public void modified_QuickSort() {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(5);
        intList.add(2);
        intList.add(7);
        intList.add(6);
        intList.add(1);
        intList.add(9);
        intList.add(3);
        intList.add(5);
        System.out.println("Before Modified Quick Sort: " + intList);
        intArray.Modified_QuickSort(intList, 0, 7, 3);
        System.out.println("After Modified Quick Sort: " + intList);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(9);
        assertEquals(expected, intList);
    }

    @org.junit.Test
    public void reversedModifiedQuickSortTest() {
        ArrayList<Character> charList = new ArrayList<Character>();
        charList.add('Z');
        charList.add('Y');
        charList.add('X');
        charList.add('D');
        charList.add('C');
        charList.add('B');
        charList.add('A');
        System.out.println("Before Modified QuickSort: " + charList);
        charArray.Modified_QuickSort(charList, 0, 6, 6);
        System.out.println("After Modified QuickSort: " + charList);

        ArrayList<Character> expected = new ArrayList<Character>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');
        expected.add('X');
        expected.add('Y');
        expected.add('Z');
        assertEquals(expected, charList);
    }

    @org.junit.Test
    public void shortModiQuick() {
        ArrayList<Integer> smol = new ArrayList<Integer>();
        smol.add(1);
        smol.add(2);
        smol.add(42);
        smol.add(4);
        smol.add(39);
        System.out.println("Before Modified QuickSort: " + smol);
        intArray.Modified_QuickSort(smol, 2, 4, 2);
        System.out.println("After Modified QuickSort: " + smol);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(4);
        expected.add(39);
        expected.add(42);

        assertEquals(expected, smol);

    }

    @org.junit.Test
    public void timCharSort() {
        ArrayList<Character> charList = new ArrayList<Character>();
        charList.add('C');
        charList.add('D');
        charList.add('Y');
        charList.add('X');
        charList.add('A');
        charList.add('Z');
        charList.add('B');
        System.out.println("Before TimSort: " + charList);
        charArray.TimSort(charList, 0, 6, 2);

        ArrayList<Character> expected = new ArrayList<Character>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');
        expected.add('X');
        expected.add('Y');
        expected.add('Z');
        System.out.println("After TimSort: " + expected);
        assertEquals(expected, charList);
    }

    @org.junit.Test
    public void timIntSort() {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(38);
        intList.add(27);
        intList.add(43);
        intList.add(3);
        intList.add(9);
        intList.add(82);
        intList.add(10);
        System.out.println("Before TimSort: " + intList);
        intArray.TimSort(intList, 0, 6, 2);

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(3);
        expected.add(9);
        expected.add(10);
        expected.add(27);
        expected.add(38);
        expected.add(43);
        expected.add(82);
        System.out.println("After TimSort: " + intList);
        assertEquals(expected, intList);
    }

    @org.junit.Test
    public void reversedTimSortTest() {
        ArrayList<Character> charList = new ArrayList<Character>();
        charList.add('Z');
        charList.add('Y');
        charList.add('X');
        charList.add('D');
        charList.add('C');
        charList.add('B');
        charList.add('A');
        System.out.println("Before TimSort: " + charList);
        charArray.TimSort(charList, 0, 6, 2);
        System.out.println("After TimSort: " + charList);

        ArrayList<Character> expected = new ArrayList<Character>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');
        expected.add('X');
        expected.add('Y');
        expected.add('Z');
        assertEquals(expected, charList);
    }

    @org.junit.Test
    public void sortedTimSortTest() {
        ArrayList<Character> charList = new ArrayList<Character>();
        charList.add('A');
        charList.add('B');
        charList.add('C');
        charList.add('D');
        charList.add('X');
        charList.add('Y');
        charList.add('Z');
        System.out.println("Before TimSort: " + charList);
        charArray.TimSort(charList, 0, 6, 2);
        System.out.println("After TimSort: " + charList);

        ArrayList<Character> expected = new ArrayList<Character>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        expected.add('D');
        expected.add('X');
        expected.add('Y');
        expected.add('Z');
        assertEquals(expected, charList);
    }
}
