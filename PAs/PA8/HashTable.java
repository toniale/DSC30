/*
 * Name: Tonia Le
 * PID: A15662706
 */

import java.util.Arrays;

/**
 * HashTable class that implements IHashTable
 *
 * @author Tonia Le
 * @since 02-24-21
 */
public class HashTable implements IHashTable {

    /* the bridge for lazy deletion */
    private static final String bridge = new String("[BRIDGE]".toCharArray());

    /* instance variables */
    private int size; // number of elements stored
    private String[] table; // data table
    private static final int DEFAULT_CAPACITY = 15;
    private static final int MIN_INITIAL_CAP = 5;
    private static final double MAX_LOAD_FACTOR = 0.55;
    private static final int LEFT_SHIFT = 5;
    private static final int RIGHT_SHIFT = 27;
    private static final int DOUBLE = 2;
    private double loadFactor = 0;
    private int collision = 0; // time collisions occur
    private static String statsLog = "";
    private int rehashed = 0; // times we rehashed table

    /**
     * Constructor for hash table
     */
    public HashTable() {
        this.size = 0;
        this.table = new String[DEFAULT_CAPACITY];
        this.collision = 0;
        this.statsLog = "";
        this.rehashed = 0;
    }

    /**
     * initialize a hash table with given total capacity
     *
     * @param capacity
     */
    public HashTable(int capacity) {
        if (capacity < MIN_INITIAL_CAP) {
            throw new IllegalArgumentException();
        }
        this.table = new String[capacity];
        this.size = 0;
        this.rehashed = 0;
        this.statsLog = "";
    }

    /**
     * Insert the string value into the hash table
     *
     * @param value value to insert
     * @return true if the value was inserted, false if the value was already
     * present
     * @throws NullPointerException if value is null
     */
    @Override
    public boolean insert(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        // value already present
        if (lookup(value)) {
            return false;
        }
        else {
            //rehash if load fact > max
            this.rehash();
            this.size++;
            int hashValue = hashString(value);
            // if cell == bridge, terminate while loop
            while (table[hashValue] != null && table[hashValue] != bridge) {
                this.collision++;
                hashValue++;
                hashValue = hashValue % this.table.length;
            }
            this.table[hashValue] = value;
        }
        return true;
    }

    /**
     * Delete the given value from the hash table
     *
     * @param value value to delete
     * @return true if the value was deleted, false if the value was not found
     */
    @Override
    public boolean delete(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (!lookup(value)) {
            return false;
        }
        else {
            // get location of value
            int tableIndex = hashString(value);
            // check to find element we're looking for
            while (!this.table[tableIndex].equals(value) && this.table[tableIndex] != null) {
                tableIndex ++;
                tableIndex = tableIndex % this.table.length;
                if (this.table[tableIndex] == bridge){
                    continue;
                }
            }
            // delete
            if (this.table[tableIndex].equals(value)) {
                this.table[tableIndex] = bridge;
                this.size--;
                return true;
            }
            else {
                return false;
            }
        }
    }

    /**
     * Check if the given value is present in the hash table
     *
     * @param value value to look up
     * @return true if the value was found, false if the value was not found
     */
    @Override
    public boolean lookup(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        // locate the value
        int tableIndex = hashString(value);
        // if value at the index is equal
        while (this.table[tableIndex] != null) {
            if (this.table[tableIndex].equals(value)) {
                return true;
            }
            tableIndex++;
            tableIndex = tableIndex % this.table.length;
            if (this.table[tableIndex] == bridge){
                continue;
            }
        }
        return false;
    }

    /**
     * @return returns number of elements in hash table
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return returns the total capacity of the table in the hashtable
     */
    @Override
    public int capacity() {
        return this.table.length;
    }

    /**
     * @return returns the statistics log of the hashtable
     */
    public String getStatsLog() {
        return statsLog;
    }

    /**
     * helper function to rehash, expands the array and rehashes values
     */
    private void rehash() {
        if (loadFac() > MAX_LOAD_FACTOR) {
            // increase amt of times we rehash
            this.rehashed++;

            statsLog += "Before rehash # " + (this.rehashed) +
                        ": load factor " + String.format("%1.2f", loadFac()) + ", "
                        + this.collision + " collision(s)." + "\n";

            // reset after rehashing
            this.collision = 0;

            String[] temporary = this.table;
            // new table w/ double the size
            String[] newTable = new String[this.table.length * DOUBLE];
            // empty table
            this.size = 0;

            this.table = newTable;
            // insert valid elements into temporary table
            for (int i = 0; i < temporary.length; i++) {
            if (temporary[i] == null || temporary[i] == bridge) {
                continue;
            }
                insert(temporary[i]);
            }
        }
    }

    /**
     * helper for calculating loadFactor
     * @return
     */
    private double loadFac() {
        return (double) this.size / (double) this.capacity();
    }

    /**
     * Simplified CRC hash function
     * @param value a string
     * @return returns the hash value of the given string
     */
    private int hashString(String value) {
        int hashVal = 0;
        for (int i = 0; i < value.length(); i++) {
            int leftShiftedValue = hashVal << LEFT_SHIFT;
            int rightShiftedValue = hashVal >>> RIGHT_SHIFT;
            int letter = value.charAt(i);
            hashVal = (leftShiftedValue | rightShiftedValue ^ letter);
        }
        return Math.abs(hashVal % table.length);
    }

    /**
     * Returns the string representation of the hash table.
     * This method internally uses the string representation of the table array.
     * DO NOT MODIFY. You can use it to test your code.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
