/*
 * Name: Tonia Le
 * PID:  A15662706
 */

import java.util.*;

import static org.junit.Assert.*;

public class BSTreeTester {
    BSTree<Integer> testTree1;
    BSTree<Integer> testTree2;
    BSTree<Integer> emptyTree;
    BSTree<Integer> ecTree1;
    BSTree<Integer> ecTree2;
    BSTree<Integer> ecTree3;

    @org.junit.Before
    public void setUp() throws Exception {
        testTree1 = new BSTree<>();
        testTree2= new BSTree<>();
        emptyTree = new BSTree<>();
        testTree1.insert(3);
        testTree1.insert(7);
        testTree1.insert(2);
        testTree1.insert(4);
        testTree1.insert(1);
        testTree1.insert(5);

        ecTree1 = new BSTree<>();
        ecTree2= new BSTree<>();
        ecTree3 = new BSTree<>();
        ecTree2.insert(1);
        ecTree3.insert(30);
        ecTree2.insert(20);
        ecTree3.insert(20);

    }

    @org.junit.Test
    public void leftAndRight() {
        // root is 3
        // left sub tree --> 2 (root) --> 1 (left of 2)
        // right sub tree --> 7 (root) --> 4 left of 7, 5 is left of 4
        assertEquals(new Integer(7), testTree1.getRoot().getRight().key);
        assertEquals(new Integer(2), testTree1.getRoot().getLeft().key);
        assertNotEquals(new Integer(7), testTree1.getRoot().getLeft().key);
        assertEquals(new Integer(4), testTree1.getRoot().getRight().getLeft().key);
        assertEquals(new Integer(1), testTree1.getRoot().getLeft().getLeft().key);
        assertEquals(new Integer(5), testTree1.getRoot().getRight().getLeft().getRight().key);
    }

    @org.junit.Test
    public void getRootAndGetSize() {
        assertEquals(new Integer(3),testTree1.getRoot().key);
        assertEquals(6, testTree1.getSize());
        assertTrue(testTree1.insert(8));
        assertEquals(7, testTree1.getSize());
        assertTrue(testTree1.findKey(1));
        assertFalse(testTree1.findKey(9));
        testTree1.insertData(7, 70);
        LinkedList <Integer> expected = new LinkedList<>();
        expected.add(70);
        assertEquals(expected, testTree1.findDataList(7));
        assertEquals(3, testTree1.findHeight());
    }


    @org.junit.Test
    public void insertAndInsertData() {
        emptyTree = new BSTree<>();
        assertEquals(null, emptyTree.getRoot());
        assertEquals(0, emptyTree.getSize());
        emptyTree.insert(1);
        emptyTree.insert(2);
        emptyTree.insert(3);
        emptyTree.insertData(2, 20);
        assertEquals(3, emptyTree.getSize());
        assertTrue(emptyTree.findKey(3));
        emptyTree.insertData(1, 10);
        emptyTree.insertData(3, 30);
        LinkedList<Integer> expected = new LinkedList<>();
        expected.add(20);
        assertEquals(expected, emptyTree.findDataList(2));
        assertEquals(2, emptyTree.findHeight());
    }

    @org.junit.Test
    public void comboTest() {
        emptyTree = new BSTree<>();
        emptyTree.insert(2);
        assertEquals(new Integer(2), emptyTree.getRoot().key);
        emptyTree.insert(4);
        assertEquals(1, emptyTree.findHeight());
        assertEquals(null, emptyTree.getRoot().getLeft());
        emptyTree.insert(6);
        assertEquals(2, emptyTree.findHeight());
        emptyTree.insert(8);
        assertEquals(3, emptyTree.findHeight());
        assertTrue(emptyTree.findKey(2));
        assertFalse(emptyTree.findKey(10));
        emptyTree.insert(10);
        assertEquals(4, emptyTree.findHeight());
        assertTrue(emptyTree.findKey(10));
        emptyTree.insertData(2, 100);
        LinkedList<Integer> expected = new LinkedList<>();
        expected.add(100);
        assertEquals(expected, emptyTree.findDataList(2));
    }

    @org.junit.Test
    public void findDataList() {
        testTree2 = new BSTree<>();
        testTree2.insert(1);
        testTree2.insertData(1,10);
        testTree2.insert(2);
        testTree2.insertData(2, 20);
        LinkedList<Integer> expected = new LinkedList<>();
        expected.add(10);
        assertEquals(expected, testTree2.findDataList(1));
        LinkedList<Integer> expected2 = new LinkedList<>();
        expected2.add(20);
        assertEquals(expected2, testTree2.findDataList(2));
    }

    @org.junit.Test
    public void findHeight() {
        emptyTree = new BSTree<>();
        emptyTree.insert(0);
        assertEquals(new Integer(0), emptyTree.getRoot().key);
        assertEquals(0, emptyTree.findHeight());
        emptyTree.insert(1);
        assertEquals(1, emptyTree.findHeight());
        emptyTree.insert(2);
        assertEquals(2, emptyTree.findHeight());
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void insertNPE(){
        testTree1.insert(null);
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void findKeyNPE() {
        testTree1.findKey(null);
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void insertDataNPE() {
        testTree2.insertData(null, 2);
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void insertDataNPE2() {
        testTree2.insertData(2, null);
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void insertDataIAE() {
        testTree1.insertData(200, 30);
    }

    @org.junit.Test
    public void iterator() {

        Iterator<Integer> iter = testTree1.iterator();
        assertTrue(iter.hasNext());
        assertEquals(new Integer(1), iter.next());
        assertEquals(new Integer(2), iter.next());
        assertEquals(new Integer(3), iter.next());
        assertEquals(new Integer(4), iter.next());
        assertTrue(iter.hasNext());
        assertEquals(new Integer(5), iter.next());
        assertEquals(new Integer(7), iter.next());
        assertFalse(iter.hasNext());
    }

    @org.junit.Test
    public void extraIter(){
        Iterator <Integer> iter = testTree1.iterator();
        assertNotEquals(new Integer(0), iter.next());
    }

    @org.junit.Test
    public void emptyTreeIterator() {
        Iterator<Integer> iter = emptyTree.iterator();
        assertFalse(iter.hasNext());
    }

    @org.junit.Test (expected = NoSuchElementException.class)
    public void nseException() {
        Iterator<Integer> iter = emptyTree.iterator();
        iter.next();
    }

    @org.junit.Test
    public void extraCreditTest() {
        ecTree1.insert(20);
        ecTree2.insert(1);
        ecTree2.insert(10);
        ecTree3.insert(2);
        ecTree3.insert(3);
        ecTree3.insert(20);
        ecTree1.insertData(20,1);
        ecTree2.insertData(10,2);
        ecTree3.insertData(30,3);
        ecTree3.insertData(20, 1);
        Iterator<Integer> iter1 = ecTree1.iterator();
        Iterator<Integer> iter2 = ecTree2.iterator();
        Iterator<Integer> iter3 = ecTree3.iterator();
        ArrayList int1 = new ArrayList(Arrays.asList(20));
        ArrayList int2 = new ArrayList(Arrays.asList());
        ArrayList int3 = new ArrayList(Arrays.asList());
        assertEquals(int1,ecTree2.intersection(iter1,iter2));
        assertEquals(int2,ecTree1.intersection(iter3,iter2));
        assertEquals(int3, ecTree2.intersection(iter1,iter3));
        assertEquals(1,ecTree2.levelCount(1));
        assertEquals(1,ecTree3.levelCount(0));
        assertEquals(-1,ecTree3.levelCount(100000));
    }
}
