package sorting2014;

/**
 * Insertion Sort takes an array
 * of unsorted items and compares
 * each item to the rest of the list.
 * The items that are found to be
 * smaller than the currently selected
 * item are moved to the position(s)
 * behind it. This continues until
 * there are no more items less
 * then the currently selected item.
 * <p/>
 * This process is repeated for all
 * items until each item has been checked
 * for all items less than itself.
 *
 * @author - Credit for this code goes to: http://bit.ly/1cqqtzY
 * @version - 1.0
 */
public class InsertionSort implements Sorter {
    /**
     * Call to begin the sort.
     *
     * @param items  - The unsorted array.
     * @param cutoff - A stop point.
     */
    @Override
    public void sort(Comparable[] items, int cutoff) {
        insertionSort(items);
    }

    /**
     * Conducts the Insertion Sort sorting algorithm.
     *
     * @param items - The unsorted array.
     */
    private void insertionSort(Comparable[] items) {
        /**
         * For every item in the list (except the first).
         */
        for (int i = 1; i < items.length; i++) {
            /**
             * Remember the initial 'swapping position' of our current item.
             */
            int swappingIndex = i;
            /**
             * Store the item for comparison and for putting back into the
             * list later.
             */
            Comparable currentItem = items[i];
            /**
             * While the current 'swapping position' is not at the start of the list,
             * and there are items of the list that are still greater than the previous
             * swapping position.
             */
            while ((swappingIndex > 0) && (items[swappingIndex - 1].compareTo(currentItem) > 0)) {
                /**
                 * Swap the item to be in the 'less than' the current selected number
                 * section.
                 */
                items[swappingIndex] = items[swappingIndex - 1];
                /**
                 * Go back to a previous place and check to see if this swapped number
                 * will be less than any other previous numbers to 'sift' it backwards.
                 */
                swappingIndex--;
            }
            /**
             * Remember to place our current item back into the list in it's rightful
             * place (so far).
             */
            items[swappingIndex] = currentItem;
        }
    }
}
