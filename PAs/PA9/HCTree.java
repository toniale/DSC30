/*
 * Name: Tonia Le
 * PID: A15662706
 */

import java.io.*;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * The Huffman Coding Tree
 *
 * @since 03-10-21
 * @author Tonia Le
 */
public class HCTree {
    // alphabet size of extended ASCII
    private static final int NUM_CHARS = 256;
    // number of bits in a byte
    private static final int BYTE_BITS = 8;

    // the root of HCTree
    private HCNode root;
    // the leaves of HCTree that contain all the symbols
    private HCNode[] leaves = new HCNode[NUM_CHARS];

    /**
     * The Huffman Coding Node
     */
    protected static class HCNode implements Comparable<HCNode> {

        byte symbol; // the symbol contained in this HCNode
        int freq; // the frequency of this symbol
        HCNode c0, c1, parent; // c0 is the '0' child, c1 is the '1' child

        /**
         * Initialize a HCNode with given parameters
         *
         * @param symbol the symbol contained in this HCNode
         * @param freq   the frequency of this symbol
         */
        HCNode(byte symbol, int freq) {
            this.symbol = symbol;
            this.freq = freq;
        }

        /**
         * Getter for symbol
         *
         * @return the symbol contained in this HCNode
         */
        byte getSymbol() {
            return this.symbol;
        }

        /**
         * Setter for symbol
         *
         * @param symbol the given symbol
         */
        void setSymbol(byte symbol) {
            this.symbol = symbol;
        }

        /**
         * Getter for freq
         *
         * @return the frequency of this symbol
         */
        int getFreq() {
            return this.freq;
        }

        /**
         * Setter for freq
         *
         * @param freq the given frequency
         */
        void setFreq(int freq) {
            this.freq = freq;
        }

        /**
         * Getter for '0' child of this HCNode
         *
         * @return '0' child of this HCNode
         */
        HCNode getC0() {
            return c0;
        }

        /**
         * Setter for '0' child of this HCNode
         *
         * @param c0 the given '0' child HCNode
         */
        void setC0(HCNode c0) {
            this.c0 = c0;
        }

        /**
         * Getter for '1' child of this HCNode
         *
         * @return '1' child of this HCNode
         */
        HCNode getC1() {
            return c1;
        }

        /**
         * Setter for '1' child of this HCNode
         *
         * @param c1 the given '1' child HCNode
         */
        void setC1(HCNode c1) {
            this.c1 = c1;
        }

        /**
         * Getter for parent of this HCNode
         *
         * @return parent of this HCNode
         */
        HCNode getParent() {
            return parent;
        }

        /**
         * Setter for parent of this HCNode
         *
         * @param parent the given parent HCNode
         */
        void setParent(HCNode parent) {
            this.parent = parent;
        }

        /**
         * Check if the HCNode is leaf (has no children)
         *
         * @return if it's leaf, return true. Otherwise, return false.
         */
        boolean isLeaf() {
            if (this.getC0() == null && this.getC1() == null) {
                return true;
            }
            return false;
        }

        /**
         * String representation
         *
         * @return string representation
         */
        public String toString() {
            return "Symbol: " + this.symbol + "; Freq: " + this.freq;
        }

        /**
         * Compare two nodes
         *
         * @param o node to compare
         * @return int positive if this node is greater
         */
        public int compareTo(HCNode o){
        // COMPARING freq: higher priority to less frequency
            if (this.getFreq() > o.getFreq()) {
                return 1;
            } else if (this.getFreq() < o.getFreq()) {
                return -1;
            }
        // Comparing ASCI: HCNode with a symbol of smaller ASCII has higher priority
            if (this.getSymbol() > o.getSymbol()) {
                return 1;
            } else if (this.getSymbol() < o.getSymbol()) {
                return -1;
            }
        // otherwise the nodes are equal
            return 0;
        }
    }

    /**
     * Returns the root node
     *
     * @return root node
     */
    public HCNode getRoot() {
        return root;
    }

    /**
     * Sets the root node
     *
     * @param root node to set
     */
    public void setRoot(HCNode root) {
        this.root = root;
    }

    /**
     * Build the tree by creating leaf nodes and then adding them to the priority queue
     *
     * @param freq  frequency // size
     */
    public void buildTree(int[] freq) {
        // initialize a priority queue of HCNode
        PriorityQueue<HCNode> priQueue = new PriorityQueue<>();

        for (int i = 0; i < freq.length; i++) {
            // loop through frequency
            if (freq[i] > 0) {
                HCNode temporary = new HCNode((byte) i, freq[i]); // new node
                this.leaves[i] = temporary; // add nodes to the leaves
                priQueue.offer(temporary); // add node to priQueue
            }
        }
            // when the priority queue is not empty
            while (priQueue.size() > 1) {
                // removals
                HCNode left = priQueue.poll();
                HCNode right = priQueue.poll();
                // parent node
                HCNode parentNode = new HCNode(left.getSymbol(),
                                               left.getFreq() + right.getFreq());
                // setting parents
                left.setParent(parentNode);
                right.setParent(parentNode);
                //setting children
                parentNode.setC0(left);
                parentNode.setC1(right);
                //adding the parent back to priority queue
                priQueue.offer(parentNode);
            }
            setRoot(priQueue.peek());
        }

    /**
     * Find encoding bits for a given symbol and write to the given BitOutputStream
     *
     * @param symbol byte type
     * @param out    bit output stream
     * @throws IOException when stream occurs during read
     */
    public void encode(byte symbol, BitOutputStream out) throws IOException {
        // convert a byte c to its corresponding ascii (given)
        int ascii = symbol & 0xff;
        // the node from leaves
        HCNode target = leaves[ascii];

        Stack<Integer> bits = new Stack<>(); // creates a result stack to collect all the bits
        while (target != root) {
            //loops through the tree to get to the node
            HCNode parent = target.getParent();
            // add 0 if left side child (c0)
            if (parent.getC0() == target) {
                bits.push(0);
            }
            // add 1 if right side child (c1)
            if (parent.getC1() == target) {
                bits.push(1);
            }
            target = parent;
        }
        // write out bits after getting the encoding nodes
        // by going through bits array
        while (!bits.isEmpty()) {
            //writes out to out
            out.writeBit(bits.pop());
        }
    }

    /**
     * Decodes the bits form BitInputStream
     *
     * @param in bit input stream
     * @return returns a byte that represents the symbol that is encoded by a sequence of bits
     * from it
     * @throws IOException when we get an error in reading
     */
    public byte decode(BitInputStream in) throws IOException {
        HCNode current = this.root;
        // null root
        if (current == null) {
            return (byte) 0;
        }
        //down to the leaf
        while (!current.isLeaf() && current != null) {
            int bit = in.readBit();
            if (bit == 0) {
                current = current.getC0();
            }
            else if (bit == 1) {
                current = current.getC1();
            }
            else {
                break;
            }
        }
        // return symbol of leaf
        byte b = current.getSymbol();
        return b;
    }

    /**
     * pre-order traversing recursively to print out the structure of HCTree in bits
     *
     * @param node HCNode
     * @param out bit output stream
     * @throws IOException error writing
     */
    public void encodeHCTree(HCNode node, BitOutputStream out) throws IOException {
        // return if root is null
        if (node == null) {
            return;
        }
        // bit 1 to represent leaf node
        if (node.isLeaf()) {
            Byte symbol = node.getSymbol();
            //System.out.println(symbol);
            out.writeBit(1);
            out.writeByte(symbol);
        } else {
            // bit 0 to represent parent node
            out.writeBit(0);
        }
        // recursive call for 2 children
        if (node.getC0() != null) {
            encodeHCTree(node.getC0(), out);
        }
        if (node.getC1() != null) {
            encodeHCTree(node.getC1(), out);
            }
        }


    /**
     * Build original HCTree from the header that we "printed" in bits
     *
     * @param in the BitInputStream to read from
     * @return HCNode
     * @throws IOException error reading
     */
    public HCNode decodeHCTree(BitInputStream in) throws IOException {
        // store bit
        int bit = in.readBit();
        // read the byte followed if it's a leaf
        if (bit == 1) {
            byte symbol = in.readByte();
            //System.out.println(symbol);
            HCNode leaf = new HCNode(symbol, 1);
            int ascii = symbol & 0xff;
            leaves[ascii] = leaf;
            // return
            return leaf;
        }
        // if it is root, continue recursion
        else if (bit == 0) {
            HCNode leftNode = decodeHCTree(in);
            HCNode rightNode = decodeHCTree(in);
            // dummy symbol
            HCNode parent = new HCNode(leftNode.getSymbol(), 1);
            // connect nodes w/ parent
            // set the children's parent
            leftNode.setParent(parent);
            rightNode.setParent(parent);
            // set parents kids
            parent.setC0(leftNode);
            parent.setC1(rightNode);
            // return
            return parent;
        }
        return null;
    }
}
