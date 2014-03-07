package sorting2014;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Based on pseudo-code from:
 * http://en.wikipedia.org/wiki/Quicksort
 *
 * @author - James Euesden <jee22@aber.ac.uk>
 * @version - 1.0
 */
public class QuickSort implements Sorter {

    private Random pivotSelector = new Random();

    @Override
    public void sort(Comparable[] items, int cutoff) {
        items = quickSort(items);

        for(Comparable cu:items){
            System.out.println(cu);
        }
    }

    private Comparable[] quickSort(Comparable[] items) {
        if (items.length <= 1) {
            return items;
        }

        int centre = (pivotSelector.nextInt(items.length));
        Comparable pivotElement = items[centre];

        ArrayList<Comparable> less = new ArrayList<Comparable>();
        ArrayList<Comparable> greater = new ArrayList<Comparable>();

        for (int i = 0; i < items.length; i++) {
            if (items[i].compareTo(pivotElement) <= 0) {
                if (i != centre) {
                    less.add(items[i]);
                }
            } else {
                greater.add(items[i]);
            }
        }

        return concatenate(quickSort(less.toArray(new Comparable[less.size()])), pivotElement, quickSort(greater.toArray(new Comparable[greater.size()])));
    }

    private Comparable[] concatenate(Comparable[] less, Comparable pivotElement, Comparable[] greater) {
        ArrayList<Comparable> joinedItems = new ArrayList<Comparable>();

        for (int i = 0; i < less.length; i++) {
            joinedItems.add(less[i]);
        }

        joinedItems.add(pivotElement);

        for (int i = 0; i < greater.length; i++) {
            joinedItems.add(greater[i]);
        }

        return joinedItems.toArray(new Comparable[joinedItems.size()]);
    }
}