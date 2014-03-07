package sortingUtilities;

/**
 * Holds a number of shared methods between different
 * types of Sorting algorithms that can be implemented.
 *
 * @author - James Euesden <jee22@aber.ac.uk>
 * @version - 1.0
 */
public class UtilityMethods {

    /**
     * Take two element indexes and swap the elements at those
     * indexes with each other.
     *
     * @param items - The array containing the items.
     * @param elementIndex_a - One element to swap
     * @param elementIndex_b - The other element to swap
     */
    public static void swapElements(Comparable[] items, int elementIndex_a, int elementIndex_b) {
        Comparable temp = items[elementIndex_a];
        items[elementIndex_a] = items[elementIndex_b];
        items[elementIndex_b] = temp;
    }

    /**
     * Pick a randomised point in the current section to be the centre pivot.
     * Credit for the code for getting a randomized integer from a range is from:
     * http://bit.ly/1fpR64x
     */
    public static int getPivot(int left, int right){
        return left + (int)(Math.random() * ((right - left) + 1));
    }
}
