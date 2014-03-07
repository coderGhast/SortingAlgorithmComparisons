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
 * Based on adapted pseudo-code from:
 * http://bit.ly/1kCvXur
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
            quickSort(items, left, pivot -1);
            quickSort(items, pivot +1, right);
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
     * @param firstIndex - The left most index of the current section.
     * @param lastIndex - The right most index of the current section.
     * @return - The chosen pivot placement for partitioning the sides.
     */
    public int partition(Comparable[] items, int firstIndex, int lastIndex)
    {
        int i = firstIndex;
        int j = lastIndex + 1;

        Comparable pivot = items[firstIndex];

        while (i < j) {
            do{
                i++;
            } while (items[i].compareTo(pivot) < 0);
            do{
                j--;
            } while (items[j].compareTo(pivot) > 0);
            if(i < j) {
                UtilityMethods.swapElements(items, i, j);
            }
        }
        UtilityMethods.swapElements(items, firstIndex, j);
        return j;
    }

    private int partitionTwo(Comparable[] items, int first, int last){
        int pivot =1;
        /*
        pivotPos = middle of array a;
        swap a[pivotPos] with a[first]; // Move the pivot out of the way
        swapPos = first + 1;
        for each element in the array from swapPos to last do:
             // If the current element is smaller than pivot we
             // move it towards start of array
             if (a[currentElement] < a[first]):
             swap a[swapPos] with a[currentElement];
             increment swapPos by 1;
        // Now move the pivot back to its rightful place
        swap a[first] with a[swapPos-1];
        return swapPos-1; // Pivot position
         */
        return pivot;
    }
}