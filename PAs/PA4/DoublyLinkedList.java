/*
 * NAME: Tonia Le
 * PID: A15662706
 */

import java.util.AbstractList;

/**
 * Class that implements Double Linked List
 * @author Tonia Le
 * @since 01-30-21
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            this.next = nextNode;
            this.prev = prevNode;
            this.data = element;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }

        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            this.prev.setNext(this.getNext());
            this.next.setPrev(this.getPrev());
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.nelems = 0;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        // implementation of adding the new data
        if (element == null){
            throw new NullPointerException();
        }
        Node addNode = new Node(element);
        Node prevNode = this.tail.getPrev();
        addNode.setPrev(prevNode);
        this.tail.setPrev(addNode);
        prevNode.setNext(addNode);
        addNode.setNext(this.tail);
        this.nelems++ ;
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param index the index where we insert the node
     * @param element the data of node to insert
     * @throws NullPointerException if element is null
     * @throws IndexOutOfBoundsException if index not in valid range [0, size]
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        // implementation of adding the new data

        // index is out range exception
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        // element is null
        if (element == null) {
            throw new NullPointerException();
        }
        // new node given provided element (the one we want to insert)
        Node newNode = new Node(element);

        // index is the size of the list, adding to the end
        if (index == this.nelems) {
            // set the node prior to tail
            Node tailPrev = this.tail.getPrev();
            // next to the node before the tail is the new node
            tailPrev.setNext(newNode);
            // before the new node is the node that was originally before the tail
            newNode.setPrev(tailPrev);
            // next to the new node is the tail
            newNode.setNext(this.tail);
            // prev of tail is the new node
            this.tail.setPrev(newNode);
            // update number of elements
            this.nelems++;

        } else {
            // get the node(the one with the value) at a given index
            Node temp = getNth(index);
            // after getting the node at an index, define a new one as its previous
            Node tempPrev = temp.getPrev();
            // indexed node's previous is the new node
            temp.setPrev(newNode);
            // after new node is the indexed one (?)
            newNode.setNext(temp);
            // new node is after the temporary's previous
            tempPrev.setNext(newNode);
            // previous of new node is temp previous
            newNode.setPrev(tempPrev);
            // update # elements
            this.nelems++;
        }
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        // clearing everything in between the head & tail
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element element we're searching for
     * @return returns true if found, otherwise false
     */
    @Override
    public boolean contains(Object element) {
        T data = (T)element;
        if (element == null) {
            throw new NullPointerException();
        }
        Node newNode = this.head.getNext();
        // loop through the indices of the list
        for(int ind = 0; ind < this.nelems; ind++) {
            // if the tail is node, or the data element is null, we'll get false
            if (newNode == this.tail || newNode.getElement() == null) {
                return false;
            }
            // if the element(data val) of the new node is the same as data
            if (newNode.getElement().equals(data)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index the index of the element we want
     * @return the element stored at the given index on the list
     * @throws IndexOutOfBoundsException when the index is out of bound
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        // get the node at the index
        Node newNode = getNth(index);
        // get the value of the node
        return newNode.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index index that tells us where to get the node
     * @return return the node
     */
    private Node getNth(int index) {
        if (index == 0) {
            return this.head.getNext();
        }
        Node newNode = this.head.getNext();
        // go through the list and call next for as many index
        for (int ind = 0; ind < index; ind++) {
            newNode = newNode.getNext();
        }
        return newNode;
    }

    /**
     * Determine if the list empty
     *
     * @return true if list is empty, else false
     */
    @Override
    public boolean isEmpty() {
        if (this.nelems == 0){
            return true;
        }
        return false;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index the index of element to remove
     * @return return the data
     * @throws IndexOutOfBoundsException when index is not in range [0, size]
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        // throw exceptions
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        Node removeNode = getNth(index);
        T data = removeNode.getElement();
        // set prev and next
        removeNode.getNext().setPrev(removeNode.getPrev());
        removeNode.getPrev().setNext(removeNode.getNext());
        this.nelems-- ;
        return data;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index the index of element to modify
     * @param element the value to set the old element to
     * @return return the data before modification
     * @throws IndexOutOfBoundsException if the index is not valid
     * @throws NullPointerException if the element is null
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index > this.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        // get node
        Node newNode = getNth(index);
        // save old data
        T data = newNode.getElement();
        newNode.setElement(element);
        return data;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return size of list
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * @return string representation in the skeleton above
     */
    @Override
    public String toString() {
        String returnString = "[(head) -> ";
        // temporary node
        Node newNode = this.head.getNext();
        // loops through list
        for (int ind = 0; ind < nelems; ind++){
            // continue to add the element (string format) and a right arrow
            returnString += newNode.getElement().toString() + " -> ";
            newNode = newNode.getNext();
        }
        // the last piece
        returnString += "(tail)]";
        return returnString;
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Inserts another linked list of the same type into this one
     *
     * @param index the index to insert other list
     * @param otherList the linked list to insert into the given index position
     * @throws IndexOutOfBoundsException if index is not valid
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {
        // Determine if index is valid
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (otherList.isEmpty()) {
            return;
        }
        // Splicing implementation
        Node otherHead = otherList.head.getNext();
        Node otherTail = otherList.tail.getPrev();
        Node newNode = getNth(index);
        Node newNodePrev = newNode.getPrev();

        // set new prevs and next
        otherHead.setPrev(newNodePrev);
        otherTail.setNext(newNode);
        newNode.setPrev(otherTail);
        newNodePrev.setNext(otherHead);

        this.nelems += otherList.size();
    }

    /**
     * Determine the starting indices that match the subSequence
     *
     * @param subsequence the subsequence
     * @return the start indices of subsequence
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        // A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        //exceptions
        if ((this.size() <= 0 || (subsequence == null) || (subsequence.isEmpty()))){
            return new int[0];
        }
        // loop through num of elem - subseq size
        for (int ind = 0; ind <= (this.nelems - subsequence.size()); ind++) {
            // another loop to check if there's a match
            boolean match = true;
            for (int j = 0; j < subsequence.size(); j++) {
                // no match if the element doesnt equal sub's element at given index
                if (!this.get(ind + j).equals(subsequence.get(j))) {
                    match = false;
                    break;
                }
            }
            // when there's a match
            if (match) {
                indices.add(ind);
            }
        }

        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }
}
