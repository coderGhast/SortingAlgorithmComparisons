package sorting2014;

/**
 * Shell Sort is an improved Insertion Sort,
 * that compares between gaps in the data set.
 *
 * @author - Full credit for this sorting algorithm belongs to http://bit.ly/OdGRsu
 */
public class ShellSort implements Sorter {

    /**
     * To begin the sorting process.
     * @param items - The unsorted array.
     * @param cutoff - When to stop.
     */
    @Override
    public void sort(Comparable[] items, int cutoff) {
        if(items.length > 1){
            shellSort(items);
        }
    }

    /**
     * Conduct shell sort in the unsorted array.
     * @param items - The unsorted array.
     */
    private void shellSort(Comparable[] items) {
        for (int gap = items.length / 2; gap > 0;
             gap = gap == 2 ? 1 : (int) (gap / 2.2))
            for (int i = gap; i < items.length; i++) {
                Comparable temp = items[i];
                int j;

                for (j = i; j >= gap && temp.compareTo(items[j - gap]) < 0; j -= gap) {
                    items[j] = items[j - gap];
                }
                items[j] = temp;
            }
    }
}
