/*
 * Name: Tonia Le
 * PID:  A15662706
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Tonia Le
 * @since  02-13-21
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.key = key;
            // new empty linked list for the node
            this.dataList = new LinkedList<>();
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            if (this.dataList.contains(data)) {
                this.dataList.remove(data);
                return true;
            } else {
                return false;
            }
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return this.nelems;
    }

    /**
     * Insert a key into BST
     *
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {

        if (key == null) {
            throw new NullPointerException();
        }
        // null root
        if (this.root == null) {
            this.root = new BSTNode(null, null, key);
            this.nelems += 1;
        }
        else {
            BSTNode current = this.root;
            // run as long as current isn't null
            while(current != null){
                // equal means they're duplicates
                if (key.compareTo(current.getKey()) == 0){
                    return false;
                }
                //if key is smaller than root go to left
                if (key.compareTo(current.getKey()) < 0){
                    if(current.getLeft() == null){
                        current.setleft(new BSTNode(null, null, key));
                        this.nelems += 1;
                        current = null;
                    }
                    else {
                        current = current.left;
                    }
                }
                // if key is larger go to right of root
                else{
                    if(current.getRight() == null){
                        current.setright(new BSTNode(null, null, key));
                        this.nelems += 1;
                        current = null;
                    }
                    else {
                        current = current.right;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if(key == null){
            throw new NullPointerException();
        }
        BSTNode current = this.root;
        while (current != null){
            // found key
            if (key.compareTo(current.getKey()) == 0){
                return true;
            }
            // search
            if (key.compareTo(current.getKey()) < 0){
                current = current.left;
            }
            else if (key.compareTo(current.getKey()) > 0){
                current = current.right;
            }
        }
        return false;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        // throw exceptions
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode current = this.root;
        while (current != null) {
            if (key.compareTo(current.getKey()) == 0) {
                current.dataList.add(data);
                return;
            }
            if (key.compareTo(current.getKey()) < 0) {
                BSTNode newLeft = current.getLeft();
                current = newLeft;
            }
            // greater
            else {
                BSTNode newRight = current.getRight();
                current = newRight;
            }
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        // throw exceptions
        if (key == null){
            throw new NullPointerException();
        }
        if (!findKey(key)){
            throw new IllegalArgumentException();
        }
        BSTNode current = this.root;
        while(current != null){
            if (key.compareTo(current.key) == 0){
                return current.dataList;
            }
            if (key.compareTo(current.key) < 0){
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        return current.getDataList();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null){
            return -1;
        }
        int left = findHeightHelper(root.left);
        int right = findHeightHelper(root.right);
        // recursive call until end of bst
        return 1 + Math.max(left, right);
    }

    /* * * * * BST Iterator * * * * */

    /**
     * BSTree_Iterator implements Iterator class
     */
    public class BSTree_Iterator implements Iterator<T> {

        private Stack<BSTNode> stack;
        private BSTNode newNode;

        /**
         * Constructor for BSTree_Iterator that initializes the Stack with the leftPath of the root
         */
        public BSTree_Iterator() {
            stack = new Stack<BSTNode>();
            newNode = root;

            while (newNode != null) {
                // push into stack
                stack.push(newNode);
                // goes to left side
                newNode = newNode.left;
            }
        }

        /**
         * Checks if stack is empty or not
         * @return false if the stack is empty, true if not
         */
        public boolean hasNext() {
            if (stack.size() > 0) {
                return true;
            }
            return false;
        }

        /**
         *
         * @return next item in BST
         * @throws NoSuchElementException when there's no next element
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTNode current = stack.pop();
            T currKey = current.key;
            if (current.right != null) {
                // push right node to stack
                current = current.right;
                // push left node into stack
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }
            return currKey;
        }

    }

    /**
     *
     * @return returns the iterator
     */
    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        ArrayList<T> arr = new ArrayList<>();
        // for as long as there is a next
        while (iter1.hasNext()) {
            T firstElement = iter1.next();
            // same but for element dos
            while (iter2.hasNext()) {
                T secondElement = iter2.next();
                // if they're the same
                if (firstElement.compareTo(secondElement) == 0) {
                    arr.add(firstElement);
                }
            }
        }
        return arr;
    }

    public int levelCount(int level) {
        if (count(root, level) == 0) {
            return -1;
        }
        return count(root, level);
    }

    /**
     * helper function for counting levels
     * @param current is a node
     * @param m is the count
     * @return
     */
    private int count(BSTNode current, int m) {
        // BASE CASES for r
        if (current == null) {
            return 0;
        }
        // no puedo nada niveles
        if (m == 0) {
            return 1;
        }
        return count(current.getLeft(), m - 1) + count(current.getRight(), m - 1);
    }
}
