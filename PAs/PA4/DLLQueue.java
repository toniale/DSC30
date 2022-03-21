/*
 * NAME: Tonia Le
 * PID: A15662706
 */

/**
 * Class DLLQueue implements a queue
 *
 * @param <T> generic container
 * @author Tonia Le
 * @since 01-31-21
 */
public class DLLQueue<T> {

    private DoublyLinkedList<T> queue;

    /**
     * Constructor of DLLQueue
     */
    public DLLQueue() {
        this.queue = new DoublyLinkedList<>();
    }

    /**
     * Returns the size of the queue
     *
     * @return the size
     */
    public int size() {
        return this.queue.size();
    }

    /**
     *  Determine if there contains anything in the queue
     *
     * @return true if queue is empty, otherwise false
     */
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * Add the given data to queue
     *
     * @param data added data
     */
    public void enqueue(T data) {
        if (data == null){
            throw new IllegalArgumentException();
        }
        this.queue.add(data);
    }

    /**
     * Remove and return the top element from this queue
     *
     * @return top element of the queue
     */
    public T dequeue() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.queue.remove(0);
        }
    }

    /**
     * Peek and return the top element from this queue
     *
     * @return the top element of queue
     */
    public T peek() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.queue.get(0);
        }
    }
}
