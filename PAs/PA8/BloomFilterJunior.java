/*
 * NAME: Tonia Le
 * PID: A15662706
 */

/**
 * Class BloomFilterJunior
 *
 * @author Tonia Le
 * @since 03-05-21
 */
public class BloomFilterJunior {

    /* Constants */
    private static final int MIN_INIT_CAPACITY = 50;
    private static final int BASE256_LEFT_SHIFT = 8;
    private static final int HORNERS_BASE = 27;
    private static final int LEFT_SHIFT = 5;
    private static final int RIGHT_SHIFT = 27;

    /* Instance variables */
    private boolean[] table;

    /**
     * Constructor of BloomFilterJunior that initializes a boolean array (table) w/ given capacity
     * @param capacity
     */
    public BloomFilterJunior(int capacity) {
        if (capacity < MIN_INIT_CAPACITY) {
            throw new IllegalArgumentException();
        }
        // initialize a new boolean array with given capacity
        this.table = new boolean[MIN_INIT_CAPACITY];
    }

    /**
     * Insert an element value in the table
     * @param value given as a string
     */
    public void insert(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int pos1 = hashBase256(value);
        int pos2 = hashCRC(value);
        int pos3 = hashHorners(value);
    }

    /**
     * Looks up if a value is a member of the BloomFilterJunior
     * @param value
     * @return true if present, else false
     */
    public boolean lookup(String value){
        if (value == null) {
            throw new NullPointerException();
        }
        int pos1 = hashBase256(value);
        int pos2 = hashCRC(value);
        int pos3 = hashHorners(value);
        // locate the value
        int tableIndex = hashHorners(value);
        // if value at the index is equal
        if (this.table[tableIndex] == true) {
                return true;
            }
        return false;
    }

    /**
     * Base-256 hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashBase256(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = ((hash << BASE256_LEFT_SHIFT) + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }

    /**
     * Simplified CRC hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashCRC(String value) {
        int hashVal = 0;
        for (int i = 0; i < value.length(); i++) {
            int leftShiftedValue = hashVal << LEFT_SHIFT;
            int rightShiftedValue = hashVal >>> RIGHT_SHIFT;
            int letter = value.charAt(i);
            hashVal = (leftShiftedValue | rightShiftedValue ^ letter);
        }
        return hashVal % table.length;
    }

    /**
     * Horner's hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashHorners(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = (hash * HORNERS_BASE + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }
}
