/*
 * NAME: Tonia Le
 * PID: A15662706
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DLLQueueTest {
    DLLQueue<Integer> intQueue;
    DLLQueue<Character> charQueue1;
    DLLQueue<Character> charQueue2;

    @Before
    public void setUp() throws Exception {
        charQueue1 = new DLLQueue<Character>();
        charQueue2 = new DLLQueue<Character>();
        intQueue = new DLLQueue<Integer>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnqueue() {
        charQueue1.enqueue(null);
    }

    @Test
    public void enqueueAndSize() {
        assertEquals(0, charQueue1.size());
        charQueue1.enqueue('f');
        assertEquals(1, charQueue1.size());
        charQueue1.enqueue('m');
        assertEquals(2, charQueue1.size());
        charQueue1.enqueue('l');
        assertEquals(3, charQueue1.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(charQueue1.isEmpty());
        charQueue1.enqueue('h');
        assertFalse(charQueue1.isEmpty());
        charQueue1.dequeue();
        assertTrue(charQueue1.isEmpty());
        assertTrue(charQueue2.isEmpty());
    }

    @Test
    public void dequeueTest() {
        charQueue1.enqueue('t');
        charQueue1.enqueue('o');
        charQueue1.enqueue('e');
        assertEquals(new Character('t'), charQueue1.dequeue());
        assertEquals(2, charQueue1.size());
        assertEquals(new Character('o'), charQueue1.dequeue());
        assertEquals(new Character('e'), charQueue1.dequeue());
        assertEquals(null, intQueue.dequeue());
    }

    @Test
    public void peek() {
        intQueue.enqueue(2);
        intQueue.enqueue(4);
        assertEquals(new Integer(2), intQueue.peek());
        intQueue.dequeue();
        assertEquals(new Integer(4), intQueue.peek());
        assertEquals(null, charQueue2.peek());
    }
}
