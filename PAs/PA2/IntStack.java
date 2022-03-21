/*
    Name: Tonia Le
    PID:  A15662706
 */

import java.util.EmptyStackException;

/**
 * Class IntStack that implements a stack with stack methods (pushing, peeking, popping)
 * that takes in a capacity, load factor, and shrink factor. It also includes methods that returns
 * the size and capacity.
 *
 * @author Tonia Le
 * @since  01-15-20
 */
public class IntStack {

    /* instance variables, feel free to add more if you need */
    private int[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;
    private int top;
    private int currentCapacity;
    private int initialCapacity;
    private static final int CAP_MIN = 5;
    private static final double LOAD_MIN = .67;
    private static final double SHRINK_MAX = .33;
    private static final double DEFAULT_SHRINKF = .25;
    private static final double DEFAULT_LOADF = .75;
    private static final int RESIZE_FACTOR = 2;

    /**
     * Constructor IntStack that initializes a stack with the given capacity(integer),
     * load factor(double), and shrink factor(double).
     *
     * @param capacity is an integer representing the maximum number of elements that the current
     *                IntStack can contain
     * @param loadF is of double type that is a load factor used when expanding the stack
     * @param shrinkF is of double type that is the shrink factor when resizing
     */
    public IntStack(int capacity, double loadF, double shrinkF) {
        // exceptions for invalid ranges
        if (capacity < CAP_MIN) {
            throw new IllegalArgumentException();
        }
        if (loadF < LOAD_MIN || loadF > 1) {
            throw new IllegalArgumentException();
        }
        if (shrinkF == 0 || shrinkF > SHRINK_MAX || shrinkF < 0) {
            throw new IllegalArgumentException();
        }

        /* Initialize a stack with capacity, load, and shrink factor */
        this.data = new int[capacity];
        this.loadFactor = loadF;
        this.shrinkFactor = shrinkF;
        this.nElems = 0;
        this.top = -1;
        this.currentCapacity = capacity;
        this.initialCapacity = capacity;
    }

    /**
     * Constructor IntStack that initializes a stack with the given capacity(integer),
     * load factor(double), and has a default shrink factor of .25
     *
     * @param capacity is an integer representing the maximum number of elements that the current
     *                IntStack can contain
     * @param loadF is of double type that is a load factor used when expanding the stack
     */

    public IntStack(int capacity, double loadF) {
        this(capacity, loadF, DEFAULT_SHRINKF);
    }

    /**
     * Constructor IntStack that initializes a stack with the given capacity(integer), a default
     * load factor of size .75, and has a default shrink factor of .25
     *
     * @param capacity is an integer representing the maximum number of elements that the current
     *                IntStack can contain
     */
    public IntStack(int capacity) {
        this(capacity, DEFAULT_LOADF, DEFAULT_SHRINKF);
    }

    /**
     * Method isEmpty check if the stack is empty and returns a boolean accordingly
     *
     * @return returns True if the stack is empty and false when it's not empty
     */
    public boolean isEmpty() {
        /* check if the stack is empty */
        if (nElems == 0) {
            return true;
        }
        return false;
    }

    /**
     * Method clear clears all elements in the stack and resets the capacity to the initial capacity
     */
    public void clear() {
        /* Clears all elements in the stack and resets the capacity */
        this.data = new int[this.initialCapacity];
        this.nElems = 0;
        this.top = -1;
        this.currentCapacity = this.initialCapacity;
    }

    /**
     * Method size returns the number of elements in the stack
     *
     * @return returns the number of elements currently stored in the stacl
     */
    public int size() {
        return this.nElems;
    }

    /**
     * Method capacity returns the maximum number of elements the stack currently can store.
     *
     * @return returns current capacity
     */
    public int capacity() {
        return this.currentCapacity;
    }

    /**
    * Method peek looks at the top element of the stack (last in the array)
    *
    * @return returns the top element of the stack
    */
    public int peek() {
        // throw empty stack exception
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.data[this.top];
    }

    /**
    * Push method pushes an element to the stack, but doubles the capacity before doing so given
    * the condition is met
    *
    * @param element, an integer that is the element to be pushed to the stack
    */
    public void push(int element) {
        //resize by doubling
        if ((double) this.nElems / (this.currentCapacity) >= this.loadFactor) {
            this.currentCapacity = this.currentCapacity * this.RESIZE_FACTOR;
            // create a new array with the elements from original data array
            int[] newArray = new int[this.currentCapacity];
            for (int i = 0; i < this.nElems; i++) {
                newArray[i] = this.data[i];
            }
            this.data = newArray;
        }
        this.top++;
        this.nElems++;
        // push element
        this.data[this.top] = element;
    }

    /**
     * Pop method pops the top element of the stack and reduces the capacity of the stack by 1/2
     * after popping the element given the condition is met
     *
     * @return returns the element that has been popped
     */
    public int pop() {
        /* returns and removes the top element of the stack */
        // throw empty stack exception
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // shrink by 1/2
        if ((double) this.nElems / (this.currentCapacity) <= this.shrinkFactor) {
            this.currentCapacity = this.currentCapacity / this.RESIZE_FACTOR;
            // new array
            int[] newArray = new int[this.currentCapacity];
            for (int i = 0; i < this.nElems; i++) {
                newArray[i] = this.data[i];
            }
            this.data = newArray;
        }

        // if stack < initial capacity, resize to the initial capacity
        if (this.currentCapacity < this.initialCapacity) {
            this.currentCapacity = this.initialCapacity;
        }
        // return and remove top element
        int removedElement;
        removedElement = this.data[this.top];
        // -1 positioned (last) becomes zero
        this.data[this.top] = 0;
        this.top--;
        this.nElems--;
        return removedElement;
    }

    /**
     * multiPush method pushes an integer array of elements to the stack, where the elements in
     * the front of the array, elements, should be pushed to the stack first
     *
     * @param elements, an integer array of numbers to be pushed to the stack
     */
    public void multiPush(int[] elements) {
        //throw exception
        if (elements == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < elements.length; i++) {
            this.push(elements[i]);
        }
    }

    /**
     * multiPop method pops a given amount of elements from the stack
     *
     * @param amount, an integer that tells how many elements from the stack will be popped
     * @return returns all of the popped elements in an integer array
     */
    public int[] multiPop(int amount) {
        /* Pops the given amount of elements from the stack */
        // throw exception for negative amount
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        int[] poppedArray = new int[amount];
        // pops an amount of times
        for (int i = 0; i < amount; i++) {
            poppedArray[i] = this.pop();
        }
        return poppedArray;
    }
}