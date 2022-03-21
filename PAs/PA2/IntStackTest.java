import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class IntStackTest {
    IntStack testStack;


    @org.junit.Before
    public void setUp() throws Exception {
        testStack = new IntStack(10);
    }


    // Test Constructor Exceptions
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void IntStack() {
        testStack = new IntStack(2);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void loadFTest() {
        testStack = new IntStack(6, .66);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void shrinkFTest() {
        testStack = new IntStack(10, .80, -.35);
    }

    // Constructor Tests
    @org.junit.Test
    public void constructorTest(){
        assertNotNull(new IntStack(30));
        assertNotNull(new IntStack(30, .85));
        assertNotNull(new IntStack(30, .85, .16));
    }

    // isEmpty Test
    @org.junit.Test
    public void emptyStack() {
        assertTrue(testStack.isEmpty());
        testStack.push(2);
        assertFalse(testStack.isEmpty());
        testStack.pop();
        assertTrue(testStack.isEmpty());
    }

    // clear() tests
    @org.junit.Test
    public void clearTests() {
        testStack.multiPush(new int[]{1, 2, 3, 4, 5});
        testStack.clear();
        assertTrue(testStack.isEmpty());
        testStack.multiPush(new int[]{2, 4, 6, 8, 10});
        assertEquals(5, testStack.size());
        testStack.clear();
        assertEquals(0, testStack.size());
        testStack.multiPush(new int[]{9, 6, 3});
        testStack.clear();
        assertTrue(testStack.size() == 0);
    }

    // capacity and size tests
    @org.junit.Test
    public void capacityAndSizeTests() {
        IntStack newStack = new IntStack(7, .70, .20);
        newStack.multiPush(new int[]{18, 20, 1, 24, 8});
        assertFalse(newStack.capacity() == 11);
        assertEquals(7, newStack.capacity());
        assertTrue(newStack.size() == 5);
        newStack.clear();
        assertTrue(newStack.isEmpty());
        assertEquals(7, newStack.capacity());
        newStack.multiPush(new int[]{10, 2, 1, 5});
        assertEquals(4, newStack.size());
        newStack.pop();
        assertTrue(newStack.size() == 3);
    }

    // multiPush and multiPop tests
    @org.junit.Test
    public void multiPushPop() {
        testStack.push(10);
        testStack.push(2);
        testStack.push(2);
        testStack.multiPush(new int[]{3, 10, 4});
        assertArrayEquals(new int[]{4, 10, 3}, testStack.multiPop(3));
        testStack.multiPop(1);
        assertArrayEquals(new int[]{2}, testStack.multiPop(1));
        testStack.multiPush(new int[]{13, 22});
        assertArrayEquals(new int[]{2, 13, 22}, new int[]{2, 13, 22});
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void multiPushError() {
        testStack.multiPush(null);
    }


    @org.junit.Test
    public void pushPeekThenPop() {
        int[] newArray = new int[]{1};
        testStack.push(1);
        assertTrue(newArray[0] == testStack.peek());
        assertEquals(1, testStack.pop());
    }

    // Pop Tests
    @org.junit.Test
    public void morePopTests() {
        testStack.push(40);
        testStack.push(19);
        testStack.push(310);
        assertEquals(310, testStack.pop());
        assertEquals(19, testStack.pop());
        assertEquals(40, testStack.pop());
    }

    @org.junit.Test
    public void peekTests() {
        int[] newArray = new int[]{11, 4, 1};
        testStack.multiPush(new int[]{3, 33, 12, 5, 8, 11});
        assertTrue(newArray[0] == testStack.peek());
        int[] anotherArray = new int[]{2, 2, 5, 5};
        testStack.multiPop(2);
        assertTrue(anotherArray[2] == testStack.peek());
        assertTrue(anotherArray[3] == testStack.peek());
    }

    @org.junit.Test
    public void multiPop() {
        testStack.push(2);
        testStack.push(1);
        assertArrayEquals(new int[]{1, 2}, testStack.multiPop(2));
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void multiPopError() {
        testStack.multiPush(new int[]{12, 1, 9, 2});
        testStack.multiPop(-2);
    }

//    @org.junit.Test
//    public void push() {
//        assertEquals(0, testStack.push(0));
//    }

    @org.junit.Test
    public void resizeCap() {
        IntStack lol = new IntStack(6, .67);
        assertTrue(lol.capacity() == 6);
        lol.push(3);
        lol.push(2);
        lol.push(1);
        lol.push(22);
        lol.push(30);
        lol.push(23);
        assertTrue(lol.capacity() == 12);

    }

}