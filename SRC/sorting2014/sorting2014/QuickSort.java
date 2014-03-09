package sorting2014;

import sortingUtilities.UtilityMethods;

/**
 * QuickSort is a divide-and-conquer based
 * sorting algorithm. The algorithm takes a
 * set of unsorted items, 'partitions' them
 * by selecting a 'pivot' (an element from
 * the list), and then partitioning the remaining
 * elements to different sides of the list,
 * based on whether they are greater than or
 * less than the pivot.
 *
 * Those less than and greater than sections
 * are then themselves put through the
 * same QuickSort partitioning process.
 *
 * This is repeated until there are partitions
 * the size of a single element. When this is
 * the result, the list is sorted, as each element
 * has been put either to the greater or less than
 * side of pivots throughout the process, gradually
 * putting each element into the correct index.
 *
 * quickSort based on pseudo-code from:
 * http://bit.ly/1kCvXur
 * partitioning based on pseudo-code from Richard Shipman's
 * (rcs@aber.ac.uk) slides from lectures at
 * Aberystwyth University.
 *
 * @author - James Euesden <jee22@aber.ac.uk>
 * @version - 1.0
 */
public class QuickSort implements Sorter {

    /**
     * Calls to quickSort to begin the sorting algorithm.
     * @param items - The unsorted list.
     * @param cutoff - When the sort should stop.
     */
    @Override
    public void sort(Comparable[] items, int cutoff) {
        quickSort(items, 0, items.length - 1);
    }

    /**
     * A recursive function that first checks that there
     * is more than one element in the partition (through
     * checking that the left of the partition is not the
     * same as or greater than the right). The method
     * then partitions the left and the right side of the
     * list, and returns the 'pivot', separating the less
     * than and greater than sides of the list.
     *
     * This pivot is then used with the right most and left
     * most indexes in the current partition to conduct
     * another quickSort on those individual partitions,
     * gradually sorting the list through dividing and
     * conquering.
     * @param items - The unsorted array.
     * @param left - The left most index of the section.
     * @param right - The right most index of the section.
     */
    private void quickSort(Comparable[] items, int left, int right) {
        if(left < right){
            /**
             * Split the list at the pivot through partitioning it,
             * and return the pivot to separate out the less than
             * and greater than sides.
             */
            int pivot = partition(items, left, right);
            if(pivot -1 > left){
                quickSort(items, left, pivot-1);
            }
            if(pivot + 1 < right){
                quickSort(items, pivot +1, right);
            }
        }
    }

    /**
     * Partitioning a section is taking the left-most and
     * right-most indexes of the section, the first and last
     * indexes of the section, respectively, and using the
     * firstIndex's element as a pivot, put those items that are less
     * than it to the left side of the section, and those
     * that are greater than it to the right side of the
     * section.
     *
     * @param items - The unsorted array.
     * @param first - The first index of the current section to be sorted.
     * @param last - The last index of the current section to be sorted.
     * @return - The current pivot position, where the partition between lists is.
     */
    private int partition(Comparable[] items, int first, int last){
        /**
         * Get a random pivot for the current section.
         */
        int pivot = (int) Math.floor(UtilityMethods.getPivot(first, last));

        /**
         * Swap the pivot item with the first element to move it out
         * of the way during sorting. Assign a variable to hold this
         * for quick comparison.
         */
        UtilityMethods.swapElements(items, pivot, first);
        Comparable pivotElement = items[first];

        /**
         * The index to begin the swapping of the elements is the next
         * index after the pivot, currently at first.
         */
        int swapPosition = first + 1;

        /**
         * For each element within the current section, we iterate through
         * the section, starting from one after the pivot (the swap position)
         */
        for(int currentElement=swapPosition; currentElement <= last; currentElement++){

            /**
             * If the currently being checked element is smaller than the pivot,
             * we swap the current element with the 'swap position'. This results
             * in gathering all of the numbers less than the pivot element to
             * one side of the array.
             *
             * The index that is then to be swapped is incremented. This means
             * that any elements before the swap position will be sorted as 'less'
             * than the pivot. We don't need to move any elements greater than
             * the pivot.
             */
            if(items[currentElement].compareTo(pivotElement) < 0){
                UtilityMethods.swapElements(items, swapPosition, currentElement);
                swapPosition++;
            }

        }

        /**
         * After all elements have been swapped around, we switch the first element
         * (the pivot element), with the last sorted 'less than' element in swap
         * position -1. The works, as it doesn't matter what element is in what
         * position, as long as there are greater and less than sections. By
         * doing this swap, we keep the elements less than the pivot to the left
         * of the pivot, and put the pivot in the 'correct' sorted place in the list.
         */
        UtilityMethods.swapElements(items, first ,swapPosition-1);

        /**
         * We return the swapPosition -1, which is the final index of the pivot element.
         */
        return swapPosition -1;
    }
}