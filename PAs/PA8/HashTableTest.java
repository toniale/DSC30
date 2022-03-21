/*
 * Name: Tonia Le
 * PID: A15662706
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {
    HashTable table;
    HashTable anotha;
    HashTable smolTab;
    HashTable checkpt;
    HashTable defaultTable;

    @org.junit.Before
    public void set(){
        table = new HashTable(14);
        anotha = new HashTable(6);
        checkpt = new HashTable(8);
        defaultTable = new HashTable();
    }

    @org.junit.Test
    public void defaultCapTest(){
        defaultTable.insert("jacob");
        assertEquals(15, defaultTable.capacity());
    }

    @org.junit.Test
    public void insert20(){
        defaultTable.insert("one");
        defaultTable.insert("two");
        defaultTable.insert("three");
        defaultTable.insert("four");
        defaultTable.insert("5");
        defaultTable.insert("6");
        defaultTable.insert("7");
        defaultTable.insert("8");
        defaultTable.insert("9");
        // 9/15 = .6
        assertEquals(15, defaultTable.capacity());
        defaultTable.insert("10");
        assertNotEquals(15, defaultTable.capacity());
        defaultTable.insert("11");
        defaultTable.insert("12");
        assertEquals(30, defaultTable.capacity());
        defaultTable.insert("13");
        defaultTable.insert("14");
        defaultTable.insert("15");
        defaultTable.insert("16");
        defaultTable.insert("17");
        defaultTable.insert("18");
        defaultTable.insert("19");
        defaultTable.insert("20");
        // delete 10
        defaultTable.delete("20");
        defaultTable.delete("19");
        defaultTable.delete("18");
        defaultTable.delete("17");
        defaultTable.delete("16");
        defaultTable.delete("15");
        defaultTable.delete("14");
        defaultTable.delete("13");
        defaultTable.delete("12");
        defaultTable.delete("11");

        assertEquals(10, defaultTable.size());

        // haven't deleted "one" yet
        assertTrue(defaultTable.delete("one"));
        assertFalse(defaultTable.delete("one"));
        // can't delete something that isn't there
        assertFalse(defaultTable.delete("12"));
        assertFalse(defaultTable.delete("11"));

        assertTrue(defaultTable.insert("18"));
        assertFalse(defaultTable.lookup("20"));
    }


    @org.junit.Test
    public void insertAndSize() {

        table.insert("xx");
        assertEquals(1,table.size());

        table.insert("yy");
        assertEquals(2,table.size());

        table.insert("zz");
        assertEquals(3,table.size());

        assertTrue(table.lookup("xx"));

        assertFalse(table.lookup("dd"));

        assertTrue(table.delete("zz"));
        assertFalse(table.lookup("zz"));

        assertEquals(2, table.size());

        System.out.println(table.getStatsLog());
    }

    @org.junit.Test
    public void delete() {
        assertTrue(table.insert("works"));
        assertTrue(table.insert("this"));
        assertTrue(table.delete("works"));
        assertTrue(table.insert("sucks"));
        assertTrue(table.insert("fr"));
        assertTrue(table.delete("fr"));
        assertTrue(table.insert("!"));
        assertEquals(3, table.size());
        assertTrue(table.lookup("!"));
        assertFalse(table.lookup("fr"));
        System.out.println(table.getStatsLog());
        // idrk
        String expected = "[null, this, null, null, null, !, null, null," +
                          " null, null, [BRIDGE], [BRIDGE], null, sucks]";
        assertEquals(expected, table.toString());
    }

    @org.junit.Test
    public void insertLookup() {
        assertFalse(table.lookup("1"));
        table.insert("1");
        assertTrue(table.lookup("1"));
        table.insert("2");
        table.insert("3");
        assertEquals(3, table.size());
        assertTrue(table.lookup("3"));
        assertEquals(14, table.capacity());
        System.out.println(table.getStatsLog());
        String expected = "[null, null, null, null, null, null," +
                          " null, 1, 2, 3, null, null, null, null]";
        assertEquals(expected, table.toString());
    }

    @org.junit.Test
    public void anothaTabTests() {
        assertEquals(6, anotha.capacity());
        anotha.insert("one");
        //System.out.println(anotha);
        anotha.insert("two");
        //System.out.println(anotha);
        anotha.insert("three");
        //System.out.println(anotha);
        anotha.insert("four");
        //System.out.println(anotha);
        anotha.insert("five");
        //System.out.println(anotha);
        assertEquals(12, anotha.capacity());
        anotha.insert("six");
        assertEquals(6, anotha.size());
        System.out.println(anotha.getStatsLog());
    }


    @org.junit.Test
    public void multipleRepeats(){
        assertTrue(table.insert("first"));
        assertTrue(table.insert("sec"));
        assertFalse(table.insert("first"));
        assertEquals(2, table.size());
        System.out.println(table.getStatsLog());
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void IAECap(){
        smolTab = new HashTable(2);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void insertNPE(){
        table.insert(null);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void deleteNPE(){
        table.delete(null);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void lookupNPE(){
        table.lookup(null);
    }

    @org.junit.Test
    public void multiCollisions() {
        anotha.insert("wee");
        anotha.insert("she");
        anotha.insert("bee");
        assertEquals(3, anotha.size());
        assertEquals(6, anotha.capacity());
        anotha.insert("woo");
        anotha.insert("poo");
        assertEquals(12, anotha.capacity());
        anotha.insert("shoo");
        anotha.insert("croo");
        anotha.insert("loo");
        assertEquals(8, anotha.size());
        assertEquals(24, anotha.capacity());
        System.out.println(anotha.getStatsLog());
        String expected = "[null, null, null, null, null, null, null, poo, shoo, croo, null, " +
                          "null, null, she, bee, loo, null, null, null, null, null, wee, null, woo]";
        assertEquals(expected, anotha.toString());
    }

    @org.junit.Test
    public void smolString() {
    anotha.insert("a");
    System.out.println(anotha.toString());
    anotha.insert("b");
    anotha.insert("c");
    anotha.insert("d");
    System.out.println(anotha.toString());
    String expected = "[null, a, b, c, d, null]";
    assertEquals(expected, anotha.toString());
    anotha.delete("d");
    String expected2 = "[null, a, b, c, [BRIDGE], null]";
    assertEquals(expected2, anotha.toString());
    anotha.insert("d");
    String expected3 = "[null, a, b, c, d, null]";
    assertEquals(expected3, anotha.toString());
    }
}