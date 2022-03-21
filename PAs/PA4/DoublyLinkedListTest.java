/*
 * NAME: Tonia Le
 * PID: A15662706
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> firstList;
    private DoublyLinkedList<Integer> secondList;
    private DoublyLinkedList<Integer> thirdList;
    private DoublyLinkedList<String> stringList;
    private DoublyLinkedList<String> otherStringList;
    private DoublyLinkedList emptyList;
    private DoublyLinkedList anotherEmptyList;

    @Before
    public void setUp() throws Exception {
        firstList = new DoublyLinkedList<>();
        firstList.add(1);
        firstList.add(2);
        firstList.add(3);
        secondList = new DoublyLinkedList<>();
        secondList.add(2);
        secondList.add(4);
        secondList.add(6);
        thirdList = new DoublyLinkedList<>();
        thirdList.add(3);
        thirdList.add(6);
        thirdList.add(9);
        stringList = new DoublyLinkedList<>();
        stringList.add("this");
        stringList.add("is");
        stringList.add("stringy");
        otherStringList = new DoublyLinkedList<>();
        otherStringList.add("hee");
        otherStringList.add("hoo");
        otherStringList.add("haw");
        emptyList = new DoublyLinkedList<>();
    }

    @Test
    public void add() {
        assertTrue(emptyList.add(1));
        assertEquals(new Integer(1), emptyList.get(0));
        assertTrue(emptyList.add(2));
        assertEquals(new Integer(2), emptyList.get(1));
        assertTrue(emptyList.add(3));
        assertEquals(3, emptyList.size());
    }

    // Tests that a null pointer exception
    @Test(expected = NullPointerException.class)
    public void addNullPointerException() {
        firstList.add(-9);
        firstList.add(200);
        firstList.add(null);
    }

    // add at index
    @Test
    public void addAtIndex() {
        firstList.add(0, 10);
        assertEquals(new Integer(10), firstList.get(0));
        firstList.add(1, 20);
        assertEquals(new Integer(20), firstList.get(1));
        firstList.remove(0);
        assertEquals(new Integer(20), firstList.get(0));
        firstList.add(3, 10);
    }

    // Tests that an IndexOutOfBoundsException is thrown when adding at an invalid index
    @Test(expected = IndexOutOfBoundsException.class)
    public void iobAddAtIndex() {
        emptyList.add(12); //ind 0
        emptyList.add(1); //ind 1
        // can only add to 2nd index of emptyList because we just added 12, 1
        emptyList.add(3, 3); // ind 3
        //firstList only has 3 elements, the next has to be at index 3
        firstList.add(4, 2);
        secondList.add(-10, 2);
    }

    // Tests that a NullPointerException is thrown when adding a null value at an index
    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionAddAtIndex() {
        firstList.add(2, 8); // no exception
        emptyList.add(0, null); // exception thrown
        firstList.add(2, null); // exception thrown
    }

    @Test
    public void clear() {
        // firstList contains 1,2,3 from set up
        assertEquals(3, firstList.size());
        firstList.clear();
        assertEquals(0, firstList.size());
        firstList.add(0, 0);
        firstList.add(1, 1);
        firstList.add(2, 2);
        firstList.add(3,3);
        assertEquals(4, firstList.size());
        firstList.clear();
        assertEquals(0, firstList.size());
    }

    @Test
    public void contains() {
        assertTrue(firstList.contains(1));
        assertFalse(emptyList.contains(1));
        assertFalse(firstList.contains(6));
        assertTrue(secondList.contains(2));
    }

    @Test(expected = NullPointerException.class)
    public void containsNullExc() {
        assertTrue(firstList.contains(1));
        assertFalse(emptyList.contains(1));
        assertFalse(firstList.contains(null));
    }

    @Test
    public void get() {
        assertEquals(new Integer(1), firstList.get(0));
        assertEquals(new Integer(3), firstList.get(2));
        assertEquals(new Integer(9), thirdList.get(2));
    }

    @Test
    public void isEmpty() {
        assertFalse(firstList.isEmpty());
        assertTrue(emptyList.isEmpty());
        emptyList.add(1);
        assertFalse(emptyList.isEmpty());
    }

    @Test
    public void size() {
        assertEquals(0, emptyList.size());
        assertNotEquals(4, firstList.size());
        assertTrue(emptyList.add(2));
        assertEquals(1, emptyList.size());
    }

    @Test
    public void set() {
        // should return the original data at changed index
        // firstList = {1, 2, 3}
        assertEquals(new Integer(1), firstList.set(0, 12));
        assertEquals(new Integer(12), firstList.get(0));
        assertEquals(new Integer(3), firstList.set(2, 4));
        assertEquals(new Integer(4), firstList.get(2));
        // secondList = {2, 4, 6}
        assertEquals(new Integer(4), secondList.set(1, 10));
        // {2, 10, 6}
        assertEquals(new Integer(10), secondList.get(1));
    }

    @Test
    public void remove() {
        // {1, 2, 3}
        assertEquals(new Integer(1), firstList.remove(0));
        // {2, 3}
        assertTrue(firstList.add(4));
        // {2, 3, 4}
        assertEquals(new Integer(4), firstList.get(2));
        // {2, 3, 4, 5}
        assertTrue(firstList.add(5));
        assertEquals(4, firstList.size());
        assertEquals(new Integer(3), firstList.get(1));
        firstList.remove(0);
        // {3, 4, 5}
        firstList.remove(2);
        // {3, 4}
        assertEquals(2, firstList.size());
        assertEquals(new Integer(4), firstList.get(1));
    }

    @Test
    public void toStringMethod() {
        String expected = "[(head) -> (tail)]";
        assertEquals(expected, emptyList.toString());
        String expected2 = "[(head) -> 1 -> 2 -> 3 -> (tail)]";
        assertEquals(expected2, firstList.toString());
        String expected3 = "[(head) -> 2 -> 4 -> 6 -> (tail)]";
        assertEquals(expected3, secondList.toString());
    }

    // EXTRA CREDIT PART TESTS
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSpliceThrowsIBE() {
        DoublyLinkedList<Integer> otherList = new DoublyLinkedList<Integer>();
        firstList.splice(-1, otherList);
    }

    @Test
    public void megaSplice() {
        // first list = {1,2,3}
        // second list = {2,4,6}
        // third list = {3, 6, 9}
        // insert at 0 index
        firstList.splice(0, secondList);
        String expected1 = "[(head) -> 2 -> 4 -> 6 -> 1 -> 2 -> 3 -> (tail)]";
        assertEquals(expected1, firstList.toString());
        assertEquals(new Integer(6), firstList.get(2));
        assertEquals(6, firstList.size());
        firstList.splice(0, thirdList);
        String expected2 = "[(head) -> 3 -> 6 -> 9 -> 2 -> 4 -> 6 -> 1 -> 2 -> 3 -> (tail)]";
        assertEquals(expected2, firstList.toString());
        assertEquals(new Integer(3), firstList.get(8));
    }

    @Test
    public void stringSplice() {
        stringList.splice(0, otherStringList);
        String expectedStr1 = "[(head) -> hee -> hoo -> haw -> this -> is -> stringy -> (tail)]";
        assertEquals(expectedStr1, stringList.toString());
        assertEquals("hee", stringList.get(0));
    }

    @Test
    public void spliceEnd() {
        firstList.splice(3, secondList);
        String exp = "[(head) -> 1 -> 2 -> 3 -> 2 -> 4 -> 6 -> (tail)]";
        assertEquals(exp, firstList.toString());
        assertEquals(new Integer(2), firstList.get(1));
        assertEquals(new Integer(2), firstList.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInSplice() {
        stringList.splice(4, otherStringList);
    }

    @Test
    public void testMatch() {
        DoublyLinkedList <String> heheList = new DoublyLinkedList<>();
        heheList.add("it");
        heheList.add("me");
        heheList.add("again");
        DoublyLinkedList<String> heehawList = new DoublyLinkedList<>();
        heehawList.add("it");
        heehawList.add("me");
        heehawList.add("again");
        heheList.splice(3, heehawList);
        DoublyLinkedList<String> subSeq = new DoublyLinkedList<>();
        subSeq.add("it");
        subSeq.add("me");
        subSeq.add("again");
        int[] indices = {0, 3};
        int[] matchIndices = heheList.match(subSeq);
        assertEquals(indices[1], matchIndices[1]);
        assertEquals(indices[0], matchIndices[0]);
        assertEquals(2, matchIndices.length);
        assertNotEquals(indices[1], matchIndices[0]);
    }
}
