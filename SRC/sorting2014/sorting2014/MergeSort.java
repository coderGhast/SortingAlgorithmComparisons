package sorting2014;

import java.util.Arrays;

/**
 * Merge Sort takes a full list of unsorted
 * items and splits the list in half for two
 * arrays. This process is repeated on the
 * new arrays, and then on the resulting arrays
 * of those, until there are arrays containing
 * only one element.
 *
 * Once this has been done, adjacent arrays are
 * have their elements compared and sorted into
 * order, putting them into an array together.
 * This is repeated for all of the arrays.
 *
 * This process is then repeated for the sorted
 * arrays, comparing one sorted array with
 * another to create larger and larger sorted arrays
 * until there is only one array that is sorted!
 * GANDALF
 *
 * Pseudo-code for implementation from:
 * http://bit.ly/1jWFedX
 *
 * @author - James Euesden <jee22@aber.ac.uk>
 * @version - 1.0
 */
public class MergeSort  implements Sorter{

    private Comparable[] items;
    /**
     * The initial function called to initiate the Sort function.
     *
     * @param items  - Unsorted list of items to be sorted.
     * @param cutoff - When to stop the sort.
     */
    @Override
    public void sort(Comparable[] items, int cutoff) {
        items = mergeSort(items);
    }

    private Comparable[] mergeSort(Comparable[] items){
        Comparable[] leftArray;
        Comparable[] rightArray;
        if(items.length <= 1){
            return items;
        } else {
            int centre = items.length / 2;

            leftArray = Arrays.copyOfRange(items, 0, centre);
            rightArray = Arrays.copyOfRange(items, centre, items.length);

            leftArray = mergeSort(leftArray);
            rightArray = mergeSort(rightArray);
            items = merge(leftArray, rightArray);
        }
        System.out.println("=========");
        for(Comparable cu:items){
            System.out.println(cu);
        }
        return items;
    }

    private Comparable[] merge(Comparable[] left, Comparable[] right){
        Comparable[] items = new Comparable[left.length + right.length];
        int count = 0;
        while(left.length > 0 || right.length > 0){
            if(left.length > 0 && right.length > 0){
                if(left[0].compareTo(right[0]) < 0){
                    items[count] = left[0];
                    left = Arrays.copyOfRange(left, 1, left.length);
                } else {
                    items[count] = right[0];
                    right = Arrays.copyOfRange(right, 1, right.length);
                }
            } else if(left.length > 0){
                items[count] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else if(right.length > 0){
                items[count] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
            count++;
        }
        return items;
    }

    protected Comparable[] getSortedItems(){
        return items;
    }
}
