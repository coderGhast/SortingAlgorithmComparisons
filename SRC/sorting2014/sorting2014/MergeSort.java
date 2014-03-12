package sorting2014;

/**
 * Merge Sort takes a full list of unsorted
 * items and splits the list in half for two
 * arrays. This process is repeated on the
 * new arrays, and then on the resulting arrays
 * of those, until there are arrays containing
 * only one element.
 * <p/>
 * Once this has been done, adjacent arrays are
 * have their elements compared and sorted into
 * order, putting them into an array together.
 * This is repeated for all of the arrays.
 * <p/>
 * This process is then repeated for the sorted
 * arrays, comparing one sorted array with
 * another to create larger and larger sorted arrays
 * until there is only one array that is sorted!
 * GANDALF
 * <p/>
 * Adapted pseudo-code for implementation from:
 * http://bit.ly/1gXs7Gz
 * and based on some Richard Shipman's example 'mergeSort'
 * code during University lectures at Aberystwyth
 * University.
 *
 * @author - James Euesden <jee22@aber.ac.uk>
 * @version - 1.0
 */
public class MergeSort implements Sorter {

    /**
     * The initial function called to initiate the Sort function.
     * Creates a temporary array for holding the sorted elements
     * of the current sorted sections of the unsorted array.
     *
     * @param items  - Unsorted list of items to be sorted.
     * @param cutoff - When to stop the sort.
     */
    @Override
    public void sort(Comparable[] items, int cutoff) {
        /**
         * Check that there are more than just 1 item
         * in the array.
         */
        if (items.length > 1) {
            /**
             * Create a new temporary array that will hold
             * the elements while the items array is sorted,
             * so that no items are lost in the process.
             */
            Comparable[] tempArray = new Comparable[items.length];
            /**
             * Call to merge sort, using the unsorted items array,
             * the temporary array for the sorted results, '0'
             * for the initial start of the 'left' section, and
             * the end index of the array (length - 1) as the
             * 'right' side of the array.
             */
            mergeSort(items, tempArray, 0, items.length - 1);
        }
    }

    /**
     * mergeSort calls recursively to itself, dividing
     * the items array into half sections until the result is
     * each element in their own defined 'section'. This
     * is signified when the 'left' is the same as 'right'
     * or higher, indicating that the section has only one
     * element (or no element) in it.
     * <p/>
     * With each split section of the array, the function to
     * 'merge' the two is called. Since the mergeSort is called
     * recursively, this results in each of the smaller sections
     * of a single element being compared and 'merged' into
     * sorted order first.
     *
     * @param items - The unsorted array.
     * @param temp  - An array temporarily holding the elements while they are sorted.
     * @param left  - the index considered left most of the current section.
     * @param right - the index considered the right most of the current section.
     */
    private static void mergeSort(Comparable[] items, Comparable[] temp, int left, int right) {
        /**
         * If the left of the section is still less than the right of the section,
         * we still have elements to be sorted (at least one). Otherwise do nothing,
         * leading to the end of this recursive step.
         */
        if (left < right) {
            /**
             * Find the centre point to split the current array section from
             * the unsorted items.
             */
            int centre = (int) Math.floor(left + right) / 2;

            /**
             * Take the elements between the 'left' of the current section and the
             * centre and call for them to be mergeSorted (split again and then merged).
             */
            mergeSort(items, temp, left, centre);
            /**
             * As above, but for all elements above the centre, up to the end of the
             * right of the current section.
             */
            mergeSort(items, temp, centre + 1, right);
            /**
             * Call to 'merge' the two sides of this current section, from the left
             * side of the section, to the right. This will sort them into the
             * correct location within 'items'.
             */
            merge(items, temp, left, centre, right);
        }
    }

    /**
     * Takes two sections of the array and merges them by sorting the elements
     * from the temporary array, holding the unsorted elements, and putting
     * them in-order in items.
     *
     * @param items      - The unsorted array.
     * @param temp       - The holder for the elements while they are sorted in items.
     * @param firstIndex - The left-most element of the current section.
     * @param centre     - The centre element of the current section.
     * @param lastIndex  - The right-most element of the current section.
     */
    private static void merge(Comparable[] items, Comparable[] temp, int firstIndex, int centre, int lastIndex) {

        /**
         * Copy the elements of 'items' into 'temp', so that
         * no items are lost while they are re-copied, sorted,
         * into 'items' from their original places.
         */
        for (int i = firstIndex; i <= lastIndex; i++) {
            temp[i] = items[i];
        }

        /**
         * As the current section is sorted, the method needs
         * to be aware of which item from the 'left' section
         * and which item from the 'right' section are currently
         * being sorted and compared.
         *
         * In order to do this, these two variables are defined, based
         * on the starting point of the left section (the firstIndex of
         * the left section) and the starting point of the right
         * section (the element to the right of the centre index).
         *
         * These values are incremented each time the element from the
         * array is put into the items list as 'sorted'.
         */
        int currentLeftIndex = firstIndex;
        int currentRightIndex = centre + 1;

        /**
         * 'i' is used as a counter to increment the current point
         * where an element will be added in 'items' from 'temp'.
         */
        int i = firstIndex;

        /**
         * Loop through all elements in the current section of the
         * array until either the left or right section has no more
         * elements.
         */
        while (currentLeftIndex <= centre && currentRightIndex <= lastIndex) {
            /**
             * Compare the current left element to the current right element.
             * If the left is smaller, add it to the items array, sorted. Then
             * increment the left section index to point to the next left element
             * to be compared, and increment the counter for the next element to
             * be filled in the 'items' array.
             */
            if (temp[currentLeftIndex].compareTo(temp[currentRightIndex]) < 0) {
                items[i] = temp[currentLeftIndex];
                currentLeftIndex++;
                i++;
            }
            /**
             * Else, the right element must be smaller than or equal to the element
             * on the left, so add the current right element next, then increment
             * the index numbers.
             */
            else {
                items[i] = temp[currentRightIndex];
                currentRightIndex++;
                i++;
            }
        }
        /**
         * While there are still elements in the left section (but not right),
         * add those remaining elements to the sorted array. This is safe to do
         * as those remaining elements should be sorted in that section already.
         */
        while (currentLeftIndex <= centre) {
            items[i] = temp[currentLeftIndex];
            currentLeftIndex++;
            i++;
        }
        /**
         * Same as for if the right section still has elements while the left
         * does not.
         */
        while (currentRightIndex <= lastIndex) {
            items[i] = temp[currentRightIndex];
            currentRightIndex++;
            i++;
        }
    }
}