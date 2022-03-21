/*
 * Name: Tonia Le
 * PID:  A15662706
 */

/**
 * Class MyPriorityQueue uses the dHeap class to implement the priority queue
 * @author Tonia Le
 * @since 02/22/21
 * @param <T> Generic type
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    private final static int dSize = 5;

    private dHeap<T> pQueue;

    /**
     * Constructor that creates a new priority queue
     * 
     * @param initialSize the given size
     */
    public MyPriorityQueue(int initialSize) {
        pQueue = new dHeap<>(dSize, initialSize, true);
    }

    /**
     * Inserts an element into the Priority Queue. The element received cannot be
     * null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer(T element) throws NullPointerException {
        if (element == null){
            throw new NullPointerException();
        }
        else {
            this.pQueue.add(element);
            return true;
        }
    }

    /**
     * Retrieves the head of this Priority Queue (largest element), or null if the
     * queue is empty.
     *
     * @return The head of the queue (largest element), or null if queue is empty.
     */
    public T poll() {
        if (this.pQueue.size() == 0){
            return null;
        }
        else {
            return this.pQueue.remove();
        }
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        this.pQueue.clear();
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if
     * this queue is empty.
     * 
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() {
        if (this.pQueue.size() == 0) {
            return null;
        }
        else {
            return this.pQueue.element();
        }
    }

    /**
     * Return true is the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.pQueue.size() == 0;
    }
}
