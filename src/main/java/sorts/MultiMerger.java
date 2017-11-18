package sorts;

/**
 * This class is a multithreaded merge sort.
 * He implements SortI.
 */
public class MultiMerger extends Thread implements SortI {


    private Comparable[] unsorted, sorted;


    /**
     * Limit the number of threads.
     */
    private static final int MAX_THREADS = 4;

    public MultiMerger() {

    }

    public MultiMerger(Object[] unsorted) {
        Comparable[] elementTemp=new Comparable[unsorted.length];
        for (int i = 0; i < unsorted.length; i++) {
            elementTemp[i] = (Comparable)unsorted[i];
        }
        this.unsorted = elementTemp;

    }

    /**
     * Runs is partitioning the input array and start the recursive algorithm
     */
    public void run() {

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

                MultiMerger leftSort = new MultiMerger(left);

                MultiMerger rightSort = new MultiMerger(right);


                leftSort.start();

                rightSort.start();




                try {

                    leftSort.join();

                    rightSort.join();


                    sorted = SimpleMerger.merge(leftSort.getSorted(), rightSort.getSorted());

                } catch (Exception e) {


                }


            } else {

                SimpleMerger leftSort = new SimpleMerger(left);

                SimpleMerger rightSort = new SimpleMerger(right);


                leftSort.sort();

                rightSort.sort();


                sorted = SimpleMerger.merge(leftSort.getSorted(), rightSort.getSorted());

            }


        }

    }


    public Comparable[] getSorted() {

        return sorted;

    }

    /**
     * Prepares the input array of objects to sort and using a method "run" that sorts it.
     * @param elementData Array of objects
     * @param size  The length of the array
     * @return The sorted array
     */
    @Override
    public Object[] sort(Object[] elementData, int size) {
            Object[] elementTemp = new Object[size];
            System.arraycopy(elementData,0,elementTemp,0,size);
            MultiMerger ms=new MultiMerger(elementTemp);
            ms.run();
            elementData=ms.getSorted();
        return elementData;
    }
}