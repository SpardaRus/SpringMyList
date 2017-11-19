package sorts;

import java.util.Comparator;

/**
 * This class is a multithreaded merge sort.
 * He implements SortI.
 */
public class MultiMerger extends Thread implements SortI {


    private Comparable[] unsorted, sorted;
    private Object[] unsorted2, sorted2;

    public static Comparator comp;

    public void setC(Comparator c) {
        this.comp = c;
    }



    public Comparator getC() {
        return comp;
    }

    public static Comparator c;

    /**
     * Limit the number of threads.
     */
    private static final int MAX_THREADS = 1;

    public MultiMerger() {

    }

    public MultiMerger(Object[] unsorted, Comparator c) {
        setC(c);

        if(c==null) {
            Comparable[] elementTemp = new Comparable[unsorted.length];
            for (int i = 0; i < unsorted.length; i++) {
                elementTemp[i] = (Comparable) unsorted[i];
            }


            this.unsorted = elementTemp;
        }else{

            this.unsorted2 = unsorted;

        }
    }

    /**
     * Runs is partitioning the input array and start the recursive algorithm
     */
    public void run() {
        if(comp ==null) {
            int middle;

            Comparable[] left, right;
            if (unsorted.length <= 1) {
                sorted = unsorted;
            } else {
                middle = unsorted.length / 2;
                left = new Comparable[middle];
                right = new Comparable[unsorted.length - middle];
                System.arraycopy(unsorted, 0, left, 0, middle);
                System.arraycopy(unsorted, middle, right, 0, unsorted.length - middle);
                if (activeCount() < MAX_THREADS) {
                    MultiMerger leftSort = new MultiMerger(left, comp);
                    MultiMerger rightSort = new MultiMerger(right, comp);
                    leftSort.start();
                    rightSort.start();
                    try {
                        leftSort.join();
                        rightSort.join();
                        sorted = SimpleMerger.merge(leftSort.getSorted(), rightSort.getSorted());
                    } catch (Exception e) {
                    }
                } else {
                    SimpleMerger leftSort = new SimpleMerger(left, comp);
                    SimpleMerger rightSort = new SimpleMerger(right, comp);
                    leftSort.sort();
                    rightSort.sort();
                    sorted = SimpleMerger.merge(leftSort.getSorted(), rightSort.getSorted());
                }
            }
        }else{
            int middle;

            Object[] left, right;
            if (unsorted2.length <= 1) {
                sorted2 = unsorted2;
            } else {
                middle = unsorted2.length / 2;
                left = new Object[middle];
                right = new Object[unsorted2.length - middle];
                System.arraycopy(unsorted2, 0, left, 0, middle);
                System.arraycopy(unsorted2, middle, right, 0, unsorted2.length - middle);

                if (activeCount() < MAX_THREADS) {
                    MultiMerger leftSort = new MultiMerger(left, comp);
                    MultiMerger rightSort = new MultiMerger(right, comp);
                    leftSort.start();
                    rightSort.start();
                    try {

                        leftSort.join();
                        rightSort.join();
                        sorted2 = SimpleMerger.merge2(leftSort.getSorted2(), rightSort.getSorted2());
                    } catch (Exception e) {
                    }
                } else {
                    SimpleMerger leftSort = new SimpleMerger(left, comp);
                    SimpleMerger rightSort = new SimpleMerger(right, comp);
                    leftSort.sort();
                    rightSort.sort();
                    sorted2 = SimpleMerger.merge2(leftSort.getSorted2(), rightSort.getSorted2());
                }
            }

        }
    }


    public Comparable[] getSorted() {

        return sorted;

    }
    public Object[] getSorted2() {

        return sorted2;

    }
    /**
     * Prepares the input array of objects to sort and using a method "run" that sorts it.
     * @param elementData Array of objects
     * @param size  The length of the array
     * @return The sorted array
     */

    public Object[] sort(Object[] elementData, int size, Comparator comparator) {
        comp =comparator;
        Object[] elementTemp = new Object[size];
        System.arraycopy(elementData,0,elementTemp,0,size);
        MultiMerger ms=new MultiMerger(elementTemp,comparator);
        ms.run();

        if(comparator==null){
            elementData=ms.getSorted();
        }else{
            elementData=ms.getSorted2();
        }

        return elementData;
    }

}