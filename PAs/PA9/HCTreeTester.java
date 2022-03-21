/*
 * Name: Tonia Le
 * PID: A15662706
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HCTreeTester {

    private Object BitInputStream;

    /**
     * Tests encode() and decode().
     *
     * @param tree  HCTree to test
     * @param input the byte to reconstruct
     * @return whether the encode-decode can reconstruct the input byte
     * @throws IOException from stream
     */
    private static boolean testByte(HCTree tree, byte input) throws IOException {

        // build out-stream
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(byteOut);
        BitOutputStream bitOut = new BitOutputStream(dataOut);

        // encode byte
        tree.encode(input, bitOut);

        // send data from out-stream to in-stream
        bitOut.flush();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        DataInputStream dataIn = new DataInputStream(byteIn);
        BitInputStream bitIn = new BitInputStream(dataIn);

        // decode byte and compare with input
        boolean result = (input == tree.decode(bitIn));

        // close streams
        dataOut.close();
        byteOut.close();
        dataIn.close();
        byteIn.close();
        return result;
    }

    /**
     * Checks if `expected` and `actual` have the same structure,
     * regardless of the instance variables on the nodes.
     *
     * @param expected the root of the expected tree
     * @param actual   the root of the actual tree
     * @return whether they share the same structure
     */
    private static boolean sameTreeStructure(HCTree.HCNode expected, HCTree.HCNode actual) {
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        return sameTreeStructure(expected.c0, actual.c0)
               && sameTreeStructure(expected.c1, actual.c1);
    }

    /**
     * Tests encodeHCTree() and decodeHCTree().
     *
     * @param tree HCTree to test
     * @return whether the encode-decode can reconstruct the tree
     * @throws IOException from stream
     */
    private static boolean testTree(HCTree tree) throws IOException {
        // build out-stream
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(byteOut);
        BitOutputStream bitOut = new BitOutputStream(dataOut);

        // encode tree
        tree.encodeHCTree(tree.getRoot(), bitOut);

        // send data from out-stream to in-stream
        bitOut.flush();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        DataInputStream dataIn = new DataInputStream(byteIn);
        BitInputStream bitIn = new BitInputStream(dataIn);

        // decode tree and compare with input
        HCTree treeOut = new HCTree();
        treeOut.setRoot(treeOut.decodeHCTree(bitIn));
        boolean result = sameTreeStructure(tree.getRoot(), treeOut.getRoot());

        // close streams
        dataOut.close();
        byteOut.close();
        dataIn.close();
        byteIn.close();
        return result;
    }

    HCTree tree1 = new HCTree();
    HCTree tree2 = new HCTree();
    HCTree tree3 = new HCTree();

    @org.junit.Test
    public void test1() throws IOException {
        int[] freq = new int[256];
        freq['a'] = 17;
        freq['b'] = 8;
        freq['c'] = 7;
        freq['d'] = 14;
        freq['e'] = 9;
        freq['f'] = 1;
        freq['\n'] = 1;
        tree1.buildTree(freq);
        assertEquals(57, tree1.getRoot().getFreq());
        assertEquals(101, tree1.getRoot().getSymbol());
        assertEquals(23, tree1.getRoot().getC0().getFreq());
        assertEquals(101, tree1.getRoot().getC0().getSymbol());
        assertEquals(9, tree1.getRoot().getC0().getC0().getFreq());
        assertEquals(14, tree1.getRoot().getC0().getC1().getFreq());
        assertEquals(34, tree1.getRoot().getC1().getFreq());
        assertEquals(17, tree1.getRoot().getC1().getC0().getFreq());
        assertEquals(97, tree1.getRoot().getC1().getC0().getSymbol());
        assertTrue(tree1.getRoot().getC1().getC0().isLeaf());
        assertEquals(17, tree1.getRoot().getC1().getC1().getFreq());
        //assertEquals(98,tree1.getRoot().getC1().getC1().getSymbol());
        assertEquals(9, tree1.getRoot().getC1().getC1().getC1().getFreq());
        assertEquals(8, tree1.getRoot().getC1().getC1().getC0().getFreq());
        assertEquals(98, tree1.getRoot().getC1().getC1().getC0().getSymbol());
        assertEquals(2, tree1.getRoot().getC1().getC1().getC1().getC0().getFreq());
        assertEquals(1, tree1.getRoot().getC1().getC1().getC1().getC0().getC0().getFreq());
        assertEquals(10, tree1.getRoot().getC1().getC1().getC1().getC0().getC0()
                              .getSymbol());
        assertEquals(1, tree1.getRoot().getC1().getC1().getC1().getC0().getC1().getFreq());
        assertEquals(102, tree1.getRoot().getC1().getC1().getC1().getC0().getC1()
                               .getSymbol());
        assertEquals(7, tree1.getRoot().getC1().getC1().getC1().getC1().getFreq());
        assertEquals(99, tree1.getRoot().getC1().getC1().getC1().getC1().getSymbol());
        assertTrue(tree1.getRoot().getC1().getC1().getC1().getC1().isLeaf());


//        testByte(tree1, );
//        testTree(tree1);
    }

    @org.junit.Test
    public void test2() throws IOException {
        int[] freq = new int[256];
        freq['d'] = 6;
        freq['s'] = 6;
        freq['c'] = 6;
        freq['0'] = 6;
        freq['1'] = 1;
        freq['2'] = 1;
        freq['3'] = 1;
        freq['4'] = 2;
        freq['8'] = 1;
        freq['a'] = 1;
        freq['b'] = 1;
        freq['\n'] = 1;
        tree2.buildTree(freq);

        assertEquals(33, tree2.getRoot().getFreq());
        assertEquals(12, tree2.getRoot().getC0().getFreq());
        assertEquals(6, tree2.getRoot().getC0().getC0().getFreq());
        assertEquals(100, tree2.getRoot().getC0().getC0().getSymbol());
        assertEquals(6, tree2.getRoot().getC0().getC1().getFreq());
        assertEquals(115, tree2.getRoot().getC0().getC1().getSymbol());
        assertEquals(21, tree2.getRoot().getC1().getFreq());
        assertEquals(9, tree2.getRoot().getC1().getC0().getFreq());
        assertEquals(4, tree2.getRoot().getC1().getC0().getC0().getFreq());
        assertEquals(2, tree2.getRoot().getC1().getC0().getC0().getC0().getFreq());
        assertEquals(50, tree2.getRoot().getC1().getC0().getC0().getC0().getC0().getSymbol());
        assertEquals(51, tree2.getRoot().getC1().getC0().getC0().getC0().getC1().getSymbol());
        assertEquals(2, tree2.getRoot().getC1().getC0().getC0().getC1().getFreq());
        assertEquals(52, tree2.getRoot().getC1().getC0().getC0().getC1().getSymbol());
        assertEquals(5, tree2.getRoot().getC1().getC0().getC1().getFreq());
        assertEquals(2, tree2.getRoot().getC1().getC0().getC1().getC0().getFreq());
        assertEquals(56, tree2.getRoot().getC1().getC0().getC1().getC0().getC0().getSymbol());
        assertEquals(97, tree2.getRoot().getC1().getC0().getC1().getC0().getC1().getSymbol());
        assertEquals(3, tree2.getRoot().getC1().getC0().getC1().getC1().getFreq());
        assertEquals(98,
                     tree2.getRoot().getC1().getC0().getC1().getC1().getC0().getSymbol());
        assertEquals(2, tree2.getRoot().getC1().getC0().getC1().getC1().getC1().getFreq());
        assertEquals(10,
                     tree2.getRoot().getC1().getC0().getC1().getC1().getC1().getC0().getSymbol());
        assertEquals(49,
                     tree2.getRoot().getC1().getC0().getC1().getC1().getC1().getC1().getSymbol());
        assertEquals(12, tree2.getRoot().getC1().getC1().getFreq());
        // both c0 and c1 of 12 are 6
        assertEquals(99, tree2.getRoot().getC1().getC1().getC1().getSymbol());
        assertEquals(6, tree2.getRoot().getC1().getC1().getC0().getFreq());
        assertEquals(48, tree2.getRoot().getC1().getC1().getC0().getSymbol());
        assertEquals(6, tree2.getRoot().getC1().getC1().getC1().getFreq());
        // assertEquals(99, tree2.getRoot().getC1().getC1().getC0().getSymbol());
        testTree(tree2);
        byte a = (byte) 97;
        testByte(tree2, a);
    }

    // building tree from: http://homes.sice.indiana.edu/yye/lab/teaching/spring2014-C343/huffman
    // .php
    @org.junit.Test
    public void test3() throws IOException {
        int[] freq = new int[256];
        freq['Z'] = 2;
        freq['K'] = 7;
        freq['M'] = 24;
        freq['C'] = 32;
        freq['U'] = 37;
        freq['D'] = 42;
        freq['L'] = 42;
        freq['E'] = 120;
        freq['\n'] = 1;
        tree3.buildTree(freq);
        assertEquals(307, tree3.getRoot().getFreq());
        assertEquals(120, tree3.getRoot().getC0().getFreq());
        assertEquals(69, tree3.getRoot().getC0().getSymbol());
        assertEquals(187, tree3.getRoot().getC1().getFreq());
        assertEquals(79, tree3.getRoot().getC1().getC0().getFreq());
        assertEquals(37, tree3.getRoot().getC1().getC0().getC0().getFreq());
        assertEquals(42, tree3.getRoot().getC1().getC0().getC1().getFreq());
        assertEquals(68, tree3.getRoot().getC1().getC0().getC1().getSymbol());
        assertEquals(108, tree3.getRoot().getC1().getC1().getFreq());
        assertEquals(66, tree3.getRoot().getC1().getC1().getC1().getFreq());
        assertEquals(42, tree3.getRoot().getC1().getC1().getC0().getFreq());
        assertEquals(76, tree3.getRoot().getC1().getC1().getC0().getSymbol());
        assertEquals(32, tree3.getRoot().getC1().getC1().getC1().getC0().getFreq());
        assertEquals(34, tree3.getRoot().getC1().getC1().getC1().getC1().getFreq());
        assertEquals(24, tree3.getRoot().getC1().getC1().getC1().getC1().getC1().getFreq());
        assertEquals(77, tree3.getRoot().getC1().getC1().getC1().getC1().getC1().getSymbol());
        assertEquals(10, tree3.getRoot().getC1().getC1().getC1().getC1().getC0().getFreq());
        assertEquals(3, tree3.getRoot().getC1().getC1().getC1().getC1()
                             .getC0().getC0().getFreq());
        assertEquals(7, tree3.getRoot().getC1().getC1().getC1().getC1()
                             .getC0().getC1().getFreq());
        assertEquals(75, tree3.getRoot().getC1().getC1().getC1().getC1()
                              .getC0().getC1().getSymbol());
        assertEquals(1, tree3.getRoot().getC1().getC1().getC1().getC1()
                             .getC0().getC0().getC0().getFreq());
        assertEquals(10, tree3.getRoot().getC1().getC1().getC1().getC1()
                              .getC0().getC0().getC0().getSymbol());
        testTree(tree3);
        byte three = (byte) 'Z';
        HCTree.HCNode node3 = new HCTree.HCNode(three, 2);
        sameTreeStructure(node3, tree3.getRoot());
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void NPETest() {
        int[] freq = new int[256];
        freq['Z'] = 2;
        freq['K'] = 6;
        freq['M'] = 24;
        tree1.buildTree(freq);
        assertEquals(32, tree3.getRoot().getFreq());
    }

    @org.junit.Test
    public void test4() throws IOException {
        int[] freq = new int[256];
        freq[15] = 5;
        HCTree tree4 = new HCTree();
        tree4.buildTree(freq);
        byte four = (byte) 15;
        HCTree.HCNode node4 = new HCTree.HCNode(four, 30);
        testTree(tree4);
        sameTreeStructure(node4, tree4.getRoot());
        assertTrue(testByte(tree4, four));
        assertTrue(testTree(tree4));
    }

    @org.junit.Test
    public void test5() throws IOException {
        int[] freq = new int[256];
        freq[10] = 20;
        freq[12] = 40;
        freq[14] = 60;
        HCTree tree5 = new HCTree();
        tree5.buildTree(freq);
        byte b = (byte) 100;
        HCTree.HCNode node5 = new HCTree.HCNode(b, 103);
        sameTreeStructure(node5, tree5.getRoot());
        byte byte10 = (byte) 10;
        assertTrue(testByte(tree5, byte10));
        assertTrue(testTree(tree5));
        testTree(tree5);
    }
}

