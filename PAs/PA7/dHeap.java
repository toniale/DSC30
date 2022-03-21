/*
 * Name: Tonia Le
 * PID:  A15662706
 */
import java.util.NoSuchElementException;
import java.util.*;

/**
 * Class dHeap implements the dHeap interface
 * @author Tonia Le
 * @since 2/22/21
 * @param <T> Generic type
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min

    private static final int DOUBLE = 2;
    private static final int DEFAULT_CAP = 6;

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this(DOUBLE, DEFAULT_CAP, true);
        this.nelems = 0;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this(DOUBLE, heapSize, true);
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        // exception
        if (d < 1){
            throw new IllegalArgumentException();
        }
        // new heap
        heap = (T[]) new Comparable[heapSize];
        // updates
        this.d = d;
        this.isMaxHeap = isMaxHeap;
        nelems = 0;
    }

    /**
     * Returns the number of elements stored in the heap
     * @return number of elements in heap
     */
    @Override
    public int size() {
       return this.nelems;
    }

    /**
     *  Adds given data to the heap
     * @param data is the data to add to the heap
     * @throws NullPointerException when heap is empty
     */
    @Override
    public void add(T data) throws NullPointerException {
        // data can't be null
        if (data == null) {
            throw new NullPointerException();
        }
        if (this.heap.length == this.size()) {
            this.resize();
        }
        // add the data
        this.heap[nelems] = data;
        bubbleUp(nelems);
        nelems++;
    }

    /**
     * Returns and removes the root element from the heap
     * @return root element
     * @throws NoSuchElementException when heap is empty
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (nelems == 0) {
            throw new NoSuchElementException();
        }
        // swap
        swap(0, nelems- 1);
        //this.heap[0] = this.heap[nelems - 1];
        T result = this.heap[nelems - 1];
        nelems --;
        trickleDown(0);
        return result;
    }

    /**
     * Clears all elements in the heap
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.nelems = 0;
    }

    /**
     * Method that returns the root element of heap
     * @return returns the root element of heap
     * @throws NoSuchElementException if the heap is empty
     */
    public T element() throws NoSuchElementException {
        // throw exception for empty heap
        if (nelems == 0) {
            throw new NoSuchElementException();
        } else {
            // return root
            return this.heap[0];
        }
    }

    /**
     * trickleDown is a method that trickles down the tree to get the node in correct position
     * @param index is of integer type that is where the value is
     */
    private void trickleDown(int index) {
        // base cases:
        // val trickling down has no children > itself
        // value we're trickling down is at a lead node
        // children of the node @ index = d * index + 1
        int childIndex = this.d * index + 1;
        T value = this.heap[index]; // store root
        // runs while node has at least one child
        while (childIndex < this.size()) {
            // find max among the node and all of its children
            T maxVal = value;
            int maxIndex = -1;
            for (int ind = 0; ind < this.d && ind + childIndex < this.size(); ind++) {
                if (compareTrickle(ind + childIndex, maxVal)){
                    // shift child up
                    maxVal = this.heap[ind + childIndex];
                    maxIndex = ind + childIndex;
                }
            }
            if (maxVal.compareTo(value) == 0) {
                return;
            } else if (!(maxVal.compareTo(value) == 0)) {
                swap(index, maxIndex);
                index = maxIndex;
                childIndex = d * index + 1;
            }
        }
    }

    /**
     * helper function to check if max/ min heap for trickle down
     * @param child
     * @param max
     * @return
     */
    private boolean compareTrickle(int child, T max) {

        if (isMaxHeap) {
            if (this.heap[child].compareTo(max) > 0 ) {
                return true;
            } else {
                return false;
            }
        } else {
            if (this.heap[child].compareTo(max) < 0 ) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * helper method: bubbleUp
     * @param index
     */
    private void bubbleUp(int index) {
        // cant bubble at root (index 0)
        while (index > 0) {
            int parentIndex = parent(index);
            // cur index > parent
            if (compareBubble(index, parentIndex)) {
                return;
            }
                swap(index, parentIndex);
                index = parentIndex;
            }
        }

    /**
     * helper function to compare child and parent for bubble up
     * @param child
     * @param parentIndex
     * @return
     */
    private boolean compareBubble(int child, int parentIndex){
        // max heheaps
        if (this.isMaxHeap) {
            return this.heap[child].compareTo(this.heap[parentIndex]) <= 0;
        }
        // min heaps
        else {
            return this.heap[child].compareTo(this.heap[parentIndex]) >= 0;
        }
    }

    /**
     * helper function that doubles the array before adding if it is full
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] heapCopy = (T[]) new Comparable[this.heap.length * DOUBLE];
        System.arraycopy(this.heap, 0, heapCopy, 0, nelems);
        this.heap = heapCopy;
    }

    /**
     *  helper method that finds the index of the parent
     * @param index
     * @return
     */
    private int parent(int index) {
        if(index - this.d <= 0) {
            return 0;
        }
        else {
            return (index - 1) / this.d;
        }
    }

    /**
     * helper function to swap elements at index 1 and 2
     * @param index1
     * @param index2
     */
    private void swap(int index1, int index2){
        //stores index
        T swap = this.heap[index1];
        this.heap[index1] = this.heap[index2];
        this.heap[index2] = swap;
    }
}
