/*
 * NAME: Tonia Le
 * PID:  A15662706
 */
import java.util.ArrayList;

/**
 * Sorts class.
 * @param <T> Generic type
 * @author Tonia Le
 * @since  02-07-21
 */
public class Sorts<T extends Comparable<? super T>> {

    private static final int MIDDLE_IDX = 2;
    private static final int dub = 2;

    /**
     * This method performs insertion sort on the input arraylist
     *
     * @param list  The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end   The final index of the subsection of Arraylist we want to sort
     */
    public void InsertionSort(ArrayList<T> list, int start, int end) {
        // loop through list
        for (int i = start + 1; i <= end; i++) {
            int j = i;
            // get val at indx j then compare to the val at prior position (j - 1)
            // why it work when i changed > 0 to < 0 lol
            while ((j > 0) && list.get(j).compareTo(list.get(j - 1)) < 0) {
                T current = list.get(j);
                // replace element at indx j with the other if when comparing found to be > 0
                list.set(j, list.get(j - 1));
                list.set(j - 1, current);
                // decrease
                j--;
            }
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param list  The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end   The final index of the subsection of Arraylist we want to sort
     */
    public void MergeSort(ArrayList<T> list, int start, int end) {

        if (start < end) {
            int mid = start + (end - start) / MIDDLE_IDX;
            MergeSort(list, start, mid);
            MergeSort(list, mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l   left-most index we want to merge
     * @param m   the middle index we want to merge
     * @param r   right-most index we want to merge
     */
    private void merge(ArrayList<T> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<T> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                mergedNums.add(arr.get(left));
                left++;
            } else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }

        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list  The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end   The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
        if (start >= end) {
            return;
        }
        int lowEndIndex = partition(list, start, end);
        // partition
        QuickSort(list, start, lowEndIndex);
        QuickSort(list, lowEndIndex + 1, end);
    }

    /**
     * partition helper function for QuickSort
     *
     * @param arr The arraylist we want to sort
     * @param l   left-most index we want to merge
     * @param h   right-most index we want to merge
     */
    private int partition(ArrayList<T> arr, int l, int h) {
        int middle = l + (h - l) / MIDDLE_IDX;
        T pivot = arr.get(middle);

        boolean finished = false;

        while (!finished) {
            while (arr.get(l).compareTo(pivot) < 0) {
                l += 1;
            }
            while (pivot.compareTo(arr.get(h)) < 0) {
                h -= 1;
            }

            if (l >= h) {
                finished = true;
            } else {
                T temp = arr.get(l);
                arr.set(l, arr.get(h));
                arr.set(h, temp);

                l += 1;
                h -= 1;
            }
        }
        return h;
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list   The arraylist we want to sort
     * @param start  The initial index on subsection of Arraylist we want to sort
     * @param end    The final index of the subsection of Arraylist we want to sort
     * @param cutoff the minimum length of an subsection of the arraylist
     *               such that we switch to Insertion Sort
     */
    public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
        if (start >= end) {
            return;
        }
        // array small enough is less than the cutoff
        if (end - start <= cutoff) {
            InsertionSort(list, start, end);
        }
        // perform recursive quicksort on input array
        else {
            //
            int x = partition(list, start, end);
            QuickSort(list, start, x);
            QuickSort(list, x + 1, end);
        }
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list  The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end   The final index of the subsection of Arraylist we want to sort
     * @param param The length of the initial splits that are sorted prior to merging
     */
    public void TimSort(ArrayList<T> list, int start, int end, int param) {
        // end - start = length
        if (param > end - start) {
            InsertionSort(list, start, end);
        }
        // sort part of the array using insertion sort
        for (int ind = start; ind < end; ind += param) {
            InsertionSort(list, ind, Math.min(ind + param, end));
        }
        // when the part of array equal to the size of parameter is sorted individually using
        // insertion sort
        // start merging from size of param until double the size of merged subset(?)
        for (int size = param; size < (end - start + 1); size *= dub) {
            // starting pt of left sub array to merge
            for (int left = start; (left < end); left = (left + (dub * size))) {
                // find end point of left sub array
                int right = Math.min((left + ((size * dub) - 1)), end);
                // mid + 1 is starting pt of right sub array
                int middle = left + (size - 1);
                if (middle > end) {
                    break;
                }
                // merge these sorted parts using mergesort
                merge(list, left, middle, right);
            }
        }
    }
}
