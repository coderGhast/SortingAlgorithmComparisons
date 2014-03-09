/*
 * Created on Nov 28, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package sorting2014;

import java.io.*;

/**
 * @author rcs
 *         <p/>
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
@SuppressWarnings("unchecked")
public class SortDemo {

    private static final int WARMUP_ITERATIONS = 10;
    private static final int RUN_ITERATIONS = 100;

    public static void main(String[] args) {
        SortDemo sd = new SortDemo();
        sd.oneSortAllTests("RadixSort");
        //sd.testEverything();
    }

    public long testOne(String type, Comparable[] items) {
        long start;
        long timeTaken = 0;
        Sorter s = SortFactory.getSorter(type);
        if (s != null) {
            start = System.nanoTime();
            s.sort(items, 0);
            timeTaken = System.nanoTime() - start;
        } else {
            System.out.println("Failed loading the sorter, no sorting will happen.");
        }
        return timeTaken;
    }

    public String oneSortAllTests(String type){
        String filenames[] = {"test1.dat", "test3.dat",
                "test3a.dat", "test3b.dat",
                "test4.dat",
                "test4a.dat", "test4b.dat",
                "test5.dat",
                "test5a.dat"
                , "test5b.dat",
                "test6.dat" ,"test6a.dat", "test6b.dat"};

        StringBuffer retLine = new StringBuffer();
            retLine.append(type + " ");
            for (int j = 0; j < filenames.length; j++) {
                retLine.append(filenames[j]);
                Comparable[] items;
                long elapsed = 0;
                long average = 999999;

                System.out.println(filenames[j]);

                for (int k = 0; k < WARMUP_ITERATIONS; k++) {
                    items = this.readData("SortingData/" + filenames[j]);
                    this.testOne("sorting2014." + type, items);
                }

                for (int k = 0; k < RUN_ITERATIONS; k++) {
                    items = this.readData("SortingData/" + filenames[j]);
                    elapsed += this.testOne("sorting2014." + type, items);
                }

                average = elapsed / RUN_ITERATIONS;

                retLine.append(", ");
                retLine.append(average + " ");
                System.out.println(retLine.toString());
            }
            retLine.append("\n");

        return retLine.toString();
    }

    public String testEverything() {
        String filenames[] = {"test1.dat", "test3.dat",
                "test3a.dat", "test3b.dat",
                "test4.dat",
                "test4a.dat", "test4b.dat",
                "test5.dat",
                "test5a.dat"
                , "test5b.dat"/*,
                "test6.dat" ,"test6a.dat", "test6b.dat" */};
        String sortTypes[] = {"BubbleSort", "RadixSort", "SelectionSort", "MergeSort", "QuickSort"};

        StringBuffer retLine = new StringBuffer();

        for (int i = 0; i < sortTypes.length; i++) {
            retLine.append(sortTypes[i] + " ");
            for (int j = 0; j < filenames.length; j++) {
                retLine.append(filenames[j]);
                Comparable[] items;
                long elapsed = 0;
                long average = 999999;

                for (int k = 0; k < WARMUP_ITERATIONS; k++) {
                    items = this.readData("SortingData/" + filenames[j]);
                    this.testOne("sorting2014." + sortTypes[i], items);
                }

                for (int k = 0; k < RUN_ITERATIONS; k++) {
                    items = this.readData("SortingData/" + filenames[j]);
                    elapsed += this.testOne("sorting2014." + sortTypes[i], items);
                }

                average = elapsed / RUN_ITERATIONS;

                retLine.append(", ");
                retLine.append(average);
            }
            retLine.append("\n");
        }
        return retLine.toString();

    }

    public Comparable[] readData(String fileName) {
        Comparable[] items;
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + fileName);
            System.exit(0);
        }
        boolean eof = false;
        String inLine = null;
        int numLines = 0;
        while (!eof) {
            try {
                inLine = reader.readLine();
                if (inLine == null) {
                    eof = true;
                } else {
                    numLines++;
                }
            } catch (IOException e) {
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
        }

        items = new Comparable[numLines];

        try {
            reader = new BufferedReader(
                    new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + fileName);
            System.exit(0);
        }
        eof = false;
        inLine = null;
        numLines = 0;
        while (!eof) {
            try {
                inLine = reader.readLine();
                if (inLine == null) {
                    eof = true;
                } else {
                    items[numLines] = inLine;
                    numLines++;
                }
            } catch (IOException e) {
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
        }

        return items;
    }

    public void printSortedArray(Comparable[] items) {
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i]);
        }
    }
}
