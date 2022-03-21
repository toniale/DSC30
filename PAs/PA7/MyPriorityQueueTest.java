/*
 * Name: Tonia Le
 * PID:  A15662706
 */
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyPriorityQueueTest {
    MyPriorityQueue<Integer> maxHeap;

    @Before
    public void setUp() throws Exception {
        maxHeap = new MyPriorityQueue<>(10);
        maxHeap.offer(55);
        maxHeap.offer(32);
        maxHeap.offer(48);
        maxHeap.offer(22);
        maxHeap.offer(54);
        maxHeap.offer(30);
    }

    @Test (expected = NullPointerException.class)
    public void offerNPE() {
        maxHeap.offer(null);
    }

    @Test
    public void poll() {
        assertEquals(new Integer(55), maxHeap.poll());
        assertEquals(new Integer(54), maxHeap.peek());
    }

    @Test
    public void clear() {
        maxHeap.clear();
        assertEquals(null, maxHeap.poll());
    }

    @Test
    public void peek() {
        assertEquals(new Integer(55), maxHeap.peek());
        maxHeap.clear();
        maxHeap.offer(100);
        assertEquals(new Integer(100), maxHeap.peek());
        maxHeap.offer(200);
        assertEquals(new Integer(200), maxHeap.peek());
    }

    @Test
    public void isEmpty() {
        assertFalse(maxHeap.isEmpty());
        maxHeap.clear();
        assertTrue(maxHeap.isEmpty());
    }
}
