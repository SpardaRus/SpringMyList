package sorts;
/**
 * This class is a singlehreaded merge sort.
 * He implements SortI.
 */
public class SimpleMerger implements SortI{



    private Comparable[] unsorted, sorted;

    public SimpleMerger(){

    }

    public SimpleMerger( Object[] unsorted ) {

        Comparable[] elementTemp=new Comparable[unsorted.length];
        for (int i = 0; i < unsorted.length; i++) {
            elementTemp[i] = (Comparable)unsorted[i];
        }
        this.unsorted = elementTemp;

    }



    /**
     * Runs is partitioning the input array and start the recursive algorithm
     */

    public void sort() {

        int middle;

        Comparable[] left, right;



        if ( unsorted.length <= 1 ) {

            sorted = unsorted;

        } else {

            middle = unsorted.length / 2;



            left = new Comparable[middle];

            right = new Comparable[unsorted.length - middle];



            System.arraycopy( unsorted, 0, left, 0, middle );

            System.arraycopy( unsorted, middle, right, 0, unsorted.length - middle );



            // Внимание! Опа, рекурсия :)

            SimpleMerger leftSort = new SimpleMerger( left );

            SimpleMerger rightSort = new SimpleMerger( right );



            leftSort.sort();

            rightSort.sort();



            sorted = merge( leftSort.getSorted(), rightSort.getSorted() );

        }

    }



    /**
     * Combines two sorted array
     * Used in multi-threaded sorting
     * @see sorts.MultiMerger
     * @param leftPart Left part array
     * @param rightPart Right part array
     * @return The sorted array
     */

    public static Comparable[] merge( Comparable[] leftPart, Comparable[] rightPart ) {

        int cursorLeft = 0, cursorRight = 0, counter = 0;

        Comparable[] merged = new Comparable[leftPart.length + rightPart.length];



        while ( cursorLeft < leftPart.length && cursorRight < rightPart.length ) {

            if ( leftPart[cursorLeft].compareTo(rightPart[cursorRight])<0) {

                merged[counter] = leftPart[cursorLeft];

                cursorLeft++;

            } else {

                merged[counter] = rightPart[cursorRight];

                cursorRight++;

            }

            counter++;

        }



        if ( cursorLeft < leftPart.length ) {

            System.arraycopy( leftPart, cursorLeft, merged, counter, merged.length - counter );

        }

        if ( cursorRight < rightPart.length ) {

            System.arraycopy( rightPart, cursorRight, merged, counter, merged.length - counter );

        }



        return merged;

    }



    public Comparable[] getSorted() {

        return sorted;

    }
    /**
     * Prepares the input array of objects to sort and using a method "sort" that sorts it.
     * @param elementData Array of objects
     * @param size  The length of the array
     * @return The sorted array
     */
    @Override
    public Object[] sort(Object[] elementData, int size) {
        Object[] elementTemp = new Object[size];
        System.arraycopy(elementData,0,elementTemp,0,size);
        SimpleMerger ss=new SimpleMerger(elementTemp);
        ss.sort();
        elementData=ss.getSorted();
        return elementData;
    }
}