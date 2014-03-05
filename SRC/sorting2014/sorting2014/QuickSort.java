package sorting2014;

import java.util.Random;

/**
 * Created by jee22 on 04/03/14.
 */
public class QuickSort implements Sorter {

    private Random pivotSelector;

    @Override
    public void sort(Comparable[] items, int cutoff) {
        quickSort(items);
    }


    private void quickSort(Comparable[] items){
        int pivot = pivotSelector.nextInt(items.length);
        for(Comparable current:items){
            if (current.compareTo(items[pivot]) > 0)
            {

            }
        }
    }
}
