/*
 * NAME: Tonia Le
 * PID: A15662706
 */

/**
 * Class DLLStack
 *
 * @param <T> generic container
 * @author Tonia Le
 * @since 01-31-21
 */
public class DLLStack<T> {

    private DoublyLinkedList<T> stack;

    /**
     * Constructor of DLLStack
     */
    public DLLStack() {
        this.stack = new DoublyLinkedList<>();
    }

    /**
     * Return the size of the stack (# of elements in the stack)
     *
     * @return the size
     */
    public int size() {
        return this.stack.size();
    }

    /**
     * Determine if the stack empty
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    /**
     * Push data to the stack
     *
     * @param data
     */
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        this.stack.add(data);
    }

    /**
     * Remove and return the top element from the stack
     *
     * @return the removed element (top of stack)
     */
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            return this.stack.remove(this.size() - 1);
        }
    }

    /**
     * Peek and return the top element from the stack
     *
     * @return the top element
     */
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            return this.stack.get(this.size() - 1);
        }
    }
}
