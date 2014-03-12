/*
 * Created on Nov 28, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package sorting2014;

import java.io.*;

/**
 * A series of methods for testing the various sorting
 * algorithms in this suite, and for reading in a set
 * of test data files.
 * <p/>
 * Ability to run single tests, tests on all data sets
 * for a given algorithm, or tests to run everything completely.
 * <p/>
 * For the best way to use this class, extra methods could
 * be implemented to take user input during runtime in order
 * to test particular algorithms. However, for my own testing I
 * have been changing the Strings in the Main method for each
 * test I wanted to run.
 *
 * @author rcs - Later revised by James Euesden (jee22).
 */
public class SortDemo {

    /**
     * How many times to run a test to 'warm up' the process.
     */
    private static final int WARMUP_ITERATIONS = 10;
    /**
     * How many times to actually run the test and record the
     * times for.
     */
    private static final int RUN_ITERATIONS = 100;
    /**
     * The directory of the test data.
     */
    private static String testDataDirectory = "SortingData/";
    /**
     * The file names of each test data.
     */
    private static String filenames[] = {
            "test1.dat", "test1a.dat", "test1b.dat",
            "test2.dat", "test2a.dat", "test2b.dat",
            "test3.dat", "test3a.dat", "test3b.dat",
            "test4.dat", "test4a.dat", "test4b.dat",
            "test5.dat", "test5a.dat", "test5b.dat",
            "test6.dat", "test6a.dat", "test6b.dat"
    };
    /**
     * The package containing the sorting algorithms.
     */
    private static String sortingPackage = "sorting2014.";
    /**
     * The names of each type of sort as String values.
     */
    private static String sortTypes[] = {"BubbleSort", "InsertionSort", "ShellSort", "RadixSort", "SelectionSort", "MergeSort", "QuickSort"};

    /**
     * The main testing method
     *
     * @param args - Command line arguments on application run time.
     */
    public static void main(String[] args) {
        /**
         * Create a new class of this.
         */
        SortDemo sd = new SortDemo();

        /**
         * Create a new data set,using the data in one of the testing files.
         */
        Comparable[] items = readData(testDataDirectory + filenames[10]);
        /**
         * Pass the data set and the name of the test you wish to run.
         */
        sd.testOne(sortingPackage + sortTypes[6], items);
        /**
         * Print the results of the sort.
         */
        sd.printSortedArray(items);

        /**
         * Conduct all tests in 'filesnames[]' on a particular algorithm.
         */
        sd.oneSortAllTests(sortTypes[6]);
    }

    /**
     * Runs a single array of items on a single algorithm once.
     *
     * @param type  - The type of algorithm.
     * @param items - The items to be sorted.
     * @return - Time taken to process in nanoseconds.
     */
    public long testOne(String type, Comparable[] items) {
        long start;
        long timeTaken = 0;
        /**
         * Make the algorithm.
         */
        Sorter s = SortFactory.getSorter(type);
        /**
         * Assuming the algorithm class exists and has been made (not null)
         */
        if (s != null) {
            /**
             * Record the start time, sort the items, then record the end
             * time and calculate the total elapsed time from this.
             */
            start = System.nanoTime();
            s.sort(items, 0);
            timeTaken = System.nanoTime() - start;
        } else {
            System.out.println("Failed loading the sorter, no sorting will happen.");
        }
        /**
         * Return the amount of time taken to process the list.
         */
        return timeTaken;
    }

    /**
     * Test all data sets with a single algorithm.
     *
     * @param type - Algorithm type
     * @return The results of the tests.
     */
    public String oneSortAllTests(String type) {
        /**
         * Build a String to keep track of all the results.
         */
        StringBuffer retLine = new StringBuffer();
        retLine.append(type + " ");
        /**
         * For every test file in filenames[]
         */
        for (int j = 0; j < filenames.length; j++) {
            retLine.append(filenames[j]);
            Comparable[] items;
            long elapsed = 0;
            long average = 999999;

            System.out.println(filenames[j]);

            /**
             * Run through n amount of warm up iterations.
             */
            for (int k = 0; k < WARMUP_ITERATIONS; k++) {
                /**
                 * Create a new set of items to be sure that
                 * we start with an unsorted list.
                 */
                items = this.readData(testDataDirectory + filenames[j]);
                /**
                 * Call to do a single test.
                 */
                this.testOne(sortingPackage + type, items);
            }

            /**
             * For n amount of times to run an actual recorded test.
             */
            for (int k = 0; k < RUN_ITERATIONS; k++) {
                /**
                 * Create a new set of items to be sure that
                 * we start with an unsorted list.
                 */
                items = this.readData(testDataDirectory + filenames[j]);
                /**
                 * Call to do a single test, and add the time onto the
                 * total time elapsed so far to get an time for how long
                 * it took to do all tests.
                 */
                elapsed += this.testOne(sortingPackage + type, items);
            }

            /**
             * Divide the time for all tests by how many tests were
             * run to get an average test runtime as our result.
             */
            average = elapsed / RUN_ITERATIONS;

            retLine.append(", ");
            retLine.append(average + " ");
            System.out.println(retLine.toString());
        }
        retLine.append("\n");

        return retLine.toString();
    }


    /**
     * Tests all test on all sorts for full iterations of the
     * specified test iterations.
     * <p/>
     * Absolute madness. Do not run this unless you have enough
     * processing power, time and memory to conduct something
     * ridiculous like a Bubble sort on 1,000,000 items!!
     *
     */
    public void testEverything() {

        /**
         * For all sorting algorithms.
         */
        for (int i = 0; i < sortTypes.length; i++) {
            this.oneSortAllTests(sortTypes[i]);
        }

    }

    /**
     * Read in the data to be used for testing from a given
     * filename.
     *
     * @param fileName - The file to load in.
     * @return - The unsorted list from the file.
     */
    public static Comparable[] readData(String fileName) {
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

    /**
     * Print out the state of a list.
     *
     * @param items
     */
    public void printSortedArray(Comparable[] items) {
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i]);
        }
    }
}
