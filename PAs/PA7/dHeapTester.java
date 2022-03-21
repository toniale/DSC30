/*
 * Name: Tonia Le
 * PID:  A15662706
 */

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class dHeapTester {

    dHeap<Integer> maxHeap1;
    dHeap<Integer> maxHeap2;
    dHeap<Integer> maxHeap3;
    dHeap<Integer> maxHeap4;
    dHeap<Integer> minHeap1;
    dHeap<Integer> minHeap2;
    dHeap<Integer> minHeap3;
    dHeap<Integer> minHeap4;


    @org.junit.Before
    public void setUp() {
        maxHeap1 = new dHeap<>();
        maxHeap2 = new dHeap<>(2, 10, true);
        maxHeap3 = new dHeap<Integer>(3, 10, true);
        maxHeap4 = new dHeap<Integer>(4, 10, true);
        minHeap1 = new dHeap<>();
        minHeap2 = new dHeap<Integer>(2, 10, false);
        minHeap3 = new dHeap<Integer>(3, 10, false);
        minHeap4 = new dHeap<Integer>(4, 10, false);
    }

    @org.junit.Test
    public void checkPointProblem1(){
        minHeap2.add(60);
        minHeap2.add(96);
        minHeap2.add(61);
        minHeap2.add(15);
        minHeap2.add(22);
        minHeap2.add(63);
        minHeap2.add(99);
        minHeap2.add(84);
        minHeap2.add(86);
        minHeap2.add(22);
        minHeap2.add(48);
        minHeap2.add(95);
        assertEquals(12, minHeap2.size());
    }

    @org.junit.Test
    public void checkPointProblem2(){
        maxHeap2.add(95);
        maxHeap2.add(89);
        maxHeap2.add(80);
        maxHeap2.add(65);
        maxHeap2.add(31);
        maxHeap2.add(32);
        maxHeap2.add(78);
        maxHeap2.add(28);
        maxHeap2.add(26);
        maxHeap2.add(5);
        maxHeap2.add(15);
        maxHeap2.add(14);
        assertEquals(12, maxHeap2.size());
        // first removal
        assertEquals(new Integer(95), maxHeap2.element());
        assertEquals(new Integer(95), maxHeap2.remove());
        assertEquals(11, maxHeap2.size());
        // second removal
        assertEquals(new Integer(89), maxHeap2.element());
        assertEquals(new Integer(89), maxHeap2.remove());
        assertEquals(10, maxHeap2.size());
        // third removal
        assertEquals(new Integer(80), maxHeap2.element());
        assertEquals(new Integer(80), maxHeap2.remove());
        assertEquals(9, maxHeap2.size());
        // fourth removal
        assertEquals(new Integer(78), maxHeap2.element());
        assertEquals(new Integer(78), maxHeap2.remove());
        assertEquals(8, maxHeap2.size());
        // fifth removal
        assertEquals(new Integer(65), maxHeap2.element());
        assertEquals(new Integer(65), maxHeap2.remove());
        assertEquals(7, maxHeap2.size());
    }

    @org.junit.Test
    public void d3checkPointProblem3(){
        maxHeap3.add(91);
        maxHeap3.add(89);
        maxHeap3.add(74);
        maxHeap3.add(81);
        maxHeap3.add(82);
        maxHeap3.add(61);
        maxHeap3.add(54);
        maxHeap3.add(72);
        maxHeap3.add(48);
        maxHeap3.add(29);
        maxHeap3.add(31);
        assertEquals(new Integer (91), maxHeap3.element());
        for (int i = 0; i < 5; i++) {
            maxHeap3.remove();
        }
        assertEquals(new Integer(72), maxHeap3.element());
    }

    @org.junit.Test
    public void d4checkPointProblem3(){
        maxHeap4.add(91);
        maxHeap4.add(89);
        maxHeap4.add(74);
        maxHeap4.add(81);
        maxHeap4.add(82);
        maxHeap4.add(61);
        maxHeap4.add(54);
        maxHeap4.add(72);
        maxHeap4.add(48);
        maxHeap4.add(29);
        maxHeap4.add(31);
        assertEquals(new Integer (91), maxHeap4.element());
        for (int i = 0; i < 5; i++) {
            maxHeap4.remove();
        }
        assertEquals(new Integer(72), maxHeap4.element());
    }

    @org.junit.Test
    public void checkPointProblem4(){
        minHeap3.add(11);
        minHeap3.add(23);
        minHeap3.add(19);
        minHeap3.add(42);
        minHeap3.add(31);
        minHeap3.add(48);
        minHeap3.add(58);
        minHeap3.add(55);
        minHeap3.add(30);
        minHeap3.add(26);
        minHeap3.add(45);
        assertEquals(11, minHeap3.size());
        // root before operations
        assertTrue(minHeap3.element() == 11);
        // remove minimum once
        assertEquals(new Integer(11), minHeap3.remove());
        assertEquals(new Integer(19), minHeap3.element());
        assertEquals(10, minHeap3.size());
        // remove minimum twice
        assertEquals(new Integer(19), minHeap3.remove());
        assertEquals(new Integer(23), minHeap3.remove());
        assertTrue(minHeap3.element() == 26);
        assertEquals(8, minHeap3.size());
        // insert 32
        minHeap3.add(32);
        assertEquals(9, minHeap3.size());
        assertTrue(minHeap3.element() == 26);
        // insert 18
        minHeap3.add(18);
        assertEquals(10, minHeap3.size());
        assertEquals(new Integer(18), minHeap3.element());
        // insert 15
        minHeap3.add(15);
        assertEquals(new Integer(15), minHeap3.element());
        // insert 12
        minHeap3.add(12);
        assertEquals(new Integer(12), minHeap3.element());
        assertEquals(12,minHeap3.size());
        // remove minimum 10 times
        for (int i = 0; i < 10; i++)
            minHeap3.remove();
        assertEquals(new Integer(55), minHeap3.element());
        assertEquals(2, minHeap3.size());
    }

    @org.junit.Test
    public void d3Max(){
        maxHeap3.add(42);
        maxHeap3.add(39);
        maxHeap3.add(41);
        maxHeap3.add(20);
        maxHeap3.add(15);
        maxHeap3.remove();
        assertEquals(new Integer(41),maxHeap3.element());
        assertEquals(new Integer(41), maxHeap3.remove());
        assertEquals(3, maxHeap3.size());
    }

    @org.junit.Test
    public void size() {
        maxHeap1.add(9);
        maxHeap1.add(5);
        assertEquals(2, maxHeap1.size());
        maxHeap2.add(1);
        assertEquals(1, maxHeap2.size());
        minHeap1.add(4);
        minHeap1.add(4);
        minHeap1.add(10);
        minHeap1.add(12);
        assertEquals(4, minHeap1.size());
    }

    @org.junit.Test
    public void addAndClear() {
        maxHeap1.add(12);
        maxHeap1.add(8);
        maxHeap1.add(4);
        maxHeap1.add(0);
        assertEquals(4, maxHeap1.size());
        maxHeap1.clear();
        assertEquals(0, maxHeap1.size());
        minHeap2.add(0);
        minHeap2.add(1);
        assertEquals(2, minHeap2.size());
        minHeap2.add(12);
        minHeap2.add(24);
        minHeap2.clear();
        assertEquals(0, minHeap2.size());
        minHeap2.add(48);
        minHeap2.add(96);
        assertEquals(2, minHeap2.size());
        minHeap2.clear();
        assertNotEquals(2, minHeap2.size());
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void addNPE(){
        maxHeap1.add(null);
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void removeNSEE(){
        maxHeap2.remove();
    }

    @org.junit.Test(expected = NoSuchElementException.class)
    public void elementNSEE() {
        maxHeap1.element();
    }
}
