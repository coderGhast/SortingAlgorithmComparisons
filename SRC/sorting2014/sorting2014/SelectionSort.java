package sorting2014;

import sortingUtilities.UtilityMethods;

/**
 * Selection Sort traverses through a list of
 * items, starting from one end of the list and working
 * it's way to the other end, finding the smallest (or
 * largest, depending on implementation) element. After the run,
 * this element is swapped places with the element at the front
 * (or back, if largest) of the array. This element is then
 * ignored for the rest of the sort.
 * <p/>
 * The result is a single array, where part is an ever-increasing
 * list of sorted items, and an ever-decreasing list of unsorted
 * items.
 * <p/>
 * Pseudo-code help from:
 * http://bit.ly/1lyrd9e
 *
 * @author - James Euesden <jee22@aber.ac.uk>
 * @version - 1.0
 */
public class SelectionSort implements Sorter{
    /**
     * The index number of the current smallest item.
     */
    private int smallestIndex = 0;
    /**
     * 'counter' counts for the amount of elements that
     * have been iterated through as the algorithm processes
     * each element to find the smallest.
     */
    private int counter;

    /**
     * The initial function called to initiate the Sort function.
     *
     * @param items  - Unsorted list of items to be sorted.
     * @param cutoff - When to stop the sort.
     */
    @Override
    public void sort(Comparable[] items, int cutoff) {
        /**
         * Check that the list is not containing just one item.
         */
        if (items.length > 1) {
            selectionSort(items);
        }
    }

    private void selectionSort(Comparable[] items) {
        /**
         * Assuming a normal array, the first element
         * (and so the first 'count'), should be 0.
         */
        counter = 0;

        /**
         * Iterate through all elements.
         * - 1 as the array sensibly starts at 0, and so
         * ends (-1) than the full length of the array.
         */
        while (counter < items.length - 1) {

            /**
             * The smallest item begins with
             * the current element at the 'front' of the
             * list as a good starting point for comparisons.
             */
            smallestIndex = counter;

            /**
             * Start J at +1 the current 'frontIndex', based on
             * those sorted elements coming before the 'frontIndex'
             * in the array. This also avoids comparing an element
             * against itself.
             */
            for (int j = counter + 1; j < items.length; j++) {
                /**
                 * Check if an element is smaller than the current
                 * known smallest in the list. If yes, record it's index.
                 */
                if (items[j].compareTo(items[smallestIndex]) < 0) {
                    smallestIndex = j;
                }
            }

            /**
             * If the smallest element's index is not the same as
             * the index of the element currently at the 'front'
             * of the unsorted array, swap these elements.
             * This includes the current known smallest element
             * of the unsorted section as the current end element
             * of the sorted list.
             */
            if (smallestIndex != counter) {
                UtilityMethods.swapElements(items, counter, smallestIndex);
            }

            /**
             * Increment the counter, indicating one more item in the
             * sorted section of the array, and one less unsorted element.
             */
            counter++;
        }
    }
}
