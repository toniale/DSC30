/*
 * NAME: Tonia Le
 * PID: A15662706
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DLLStackTest {

    DLLStack<Integer> intStack;
    DLLStack<Character> charStack1;
    DLLStack<Character> charStack2;

    @Before
    public void setUp() throws Exception {
        intStack = new DLLStack<Integer>();
        charStack1 = new DLLStack<Character>();
        charStack2 = new DLLStack<Character>();
    }

    @Test
    public void sizeAndPush() {
        assertEquals(0, charStack1.size());
        assertEquals(0, charStack2.size());
        charStack1.push('t');
        charStack1.push('o');
        charStack1.push('n');
        charStack1.push('i');
        charStack1.push('a');
        assertEquals(5, charStack1.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(intStack.isEmpty());
        assertTrue(charStack1.isEmpty());
        assertTrue(charStack2.isEmpty());
        intStack.push(5);
        assertFalse(intStack.isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPush() {
        intStack.push(null);
    }

    @Test
    public void popAndPeek() {
        charStack1.push('f');
        charStack1.push('m');
        charStack1.push('l');
        assertEquals(new Character('l'), charStack1.pop());
        assertEquals(new Character('m'), charStack1.pop());
        assertEquals(1, charStack1.size());
        assertEquals(null, charStack2.pop());
        assertEquals(new Character('f'), charStack1.peek());
        charStack1.pop();
        assertEquals(null, charStack1.peek());
    }
}
