/*
    Name: Tonia Le
    PID:  A15662706
 */

/**
 * Class ImageEditor that uses the IntStack class to implement an image editor.
 * It takes in an image and includes painting operations, undo, and redo functions.
 *
 * @author Tonia Le
 * @since  01-19-20
 */
public class ImageEditor {

    /* static constants, feel free to add more if you need */
    private static final int MAX_PIXEL_VALUE = 255;
    private static final int STACKS_INIT_CAPACITY = 30;

    /* instance variables, feel free to add more if you need */
    private int[][] image;
    private IntStack undo;
    private IntStack redo;

    /**
     * Constructor ImageEditor that initializes the image with the given image,
     * and initializes two stacks for undo and redo with capacity of 30
     *
     * @param image, a 2-Dimension Array
     */
    ImageEditor(int[][] image) {
        // initialize image
        this.image = image;
        // width(#col) & height(#rows)
        if (this.image == null || this.image.length == 0) {
            throw new IllegalArgumentException();
        }
        int width = this.image[0].length;
        if (width == 0) {
            throw new IllegalArgumentException();
        }
        // exception for distorted: check if the width of each row is the same as first row's width
        for (int i = 0; i < this.image.length; i++) {
            if (this.image[0].length != this.image[i].length) {
                throw new IllegalArgumentException();
            }
        }
        // initialize new stacks
        this.undo = new IntStack(STACKS_INIT_CAPACITY);
        this.redo = new IntStack(STACKS_INIT_CAPACITY);
    }

    /**
     * getImage returns the 2D array of an image
     */
    int[][] getImage() {
        /* Returns the 2D array */
        return this.image;
    }

    /**
     * The method scale scales the color value by a given factor of the pixel and given position
     * (i,j)
     *
     * @param i:           an integer representing the row number
     * @param j:           an integer representing the column number
     * @param scaleFactor: a given factor(double type) to scale the color values by
     */
    public void scale(int i, int j, double scaleFactor) {
        /* scales the color values by a given factor */
        // height check
        if (i >= this.image.length || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        // width check
        if (j < 0 || j >= this.image[0].length) {
            throw new IndexOutOfBoundsException();
            }
        // scale factor exception
        if (scaleFactor < 0) {
            throw new IllegalArgumentException();
        }
        // multiply color values
        int color = (int) (this.image[i][j] * scaleFactor);

        // values can't exceed 255
        if (color > MAX_PIXEL_VALUE) {
            color = MAX_PIXEL_VALUE;
        }
        // undo
        this.undo.multiPush(new int[]{i, j, this.image[i][j]});
        // redo
        this.redo.clear();
        //set new color
        this.image[i][j] = color;
    }

    /**
     * The method assign assigns a given color value to the pixel at a given position (i,j)
     *
     * @param i:     an integer representing the row number
     * @param j:     an integer representing the column number
     * @param color: an integer representing the color value that should be assigned at position
     *               i and j
     */
    public void assign(int i, int j, int color) {
        /* Assigns a given color value to the pixel at the given position */
        // out of frame exceptions
        // height check
        if (i >= this.image.length || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        // width check
        if (j < 0 || j >= this.image[0].length) {
            throw new IndexOutOfBoundsException();
        }
        // color value can't exceed 255 or go below 0
        if (color < 0 || color > MAX_PIXEL_VALUE) {
            throw new IllegalArgumentException();
        }
        // undo
        this.undo.multiPush(new int[]{i, j, this.image[i][j]});
        // redo
        this.redo.clear();
        // set new color
        this.image[i][j] = color;
    }

    /**
     * The method delete sets the value of a pixel at position i, j to zero
     *
     * @param i: an integer representing the row number
     * @param j: an integer representing the column number
     */
    public void delete(int i, int j) {
        /* deletes pixels at given position (setting equal to zero)*/
        // height check
        if (i >= this.image.length || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        // width check
        for (int ind = 0; ind < this.image.length; ind++) {
            if (j < 0 || j >= this.image[ind].length) {
                throw new IndexOutOfBoundsException();
            }
        }
        // pushing to undo stack
        this.undo.multiPush(new int[]{i, j, this.image[i][j]});
        //empty the redo stack
        this.redo.clear();
        // set color values equal to zero
        this.image[i][j] = 0;
    }

    /**
     * The method undo updates the image by undoing the latest operation
     *
     * @return returns a boolean false if there's no operation to undo and true if there is
     */
    public boolean undo() {
        /* updates the image by undoing the latest operation */
        if (this.undo.isEmpty()) {
            return false;
        }
        else{
            int originalColor = this.undo.pop();
            int colNumber = this.undo.pop();
            int rowNumber = this.undo.pop();
            this.redo.multiPush(new int[] {rowNumber, colNumber, this.image[rowNumber][colNumber]});
            this.image[rowNumber][colNumber] = originalColor;
        }
        return true;
    }

    /**
     * The method redo updates the image by redoing the next operation
     *
     * @return returns a boolean false if there's no operation to redo and true if there is
     */
    public boolean redo() {
        /* updates the image by redoing the next operation */
        if (this.redo.isEmpty()) {
            return false;
        }
        else{
            int currentColor = this.redo.pop();
            int colNumber = this.redo.pop();
            int rowNumber = this.redo.pop();
            this.undo.multiPush(new int[] {rowNumber, colNumber, this.image[rowNumber][colNumber]});
            this.image[rowNumber][colNumber] = currentColor;
        }
        return true;
    }
}
