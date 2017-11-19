package sorts;

import test.Human;

import java.util.Comparator;

/**
 * This class is a singlehreaded merge sort.
 * He implements SortI.
 */
public class SimpleMerger implements SortI{



    private Comparable[] unsorted, sorted;
    private Object[] unsorted2, sorted2;

    public void setC(Comparator c) {
        this.c = c;
    }

    public static Comparator c;

    public SimpleMerger(){

    }

    public SimpleMerger( Object[] unsorted,Comparator c) {
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

    public void sort() {
        if(c==null) {
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


                // Внимание! Опа, рекурсия :)

                SimpleMerger leftSort = new SimpleMerger(left, c);

                SimpleMerger rightSort = new SimpleMerger(right,c);


                leftSort.sort();

                rightSort.sort();


                sorted = merge(leftSort.getSorted(), rightSort.getSorted());

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




                SimpleMerger leftSort = new SimpleMerger(left,c);

                SimpleMerger rightSort = new SimpleMerger(right,c);


                leftSort.sort();

                rightSort.sort();



                sorted2 = merge2(leftSort.getSorted2(), rightSort.getSorted2());

            }

        }
    }


    public Comparator getC() {
        return c;
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
    public static Object[] merge2( Object[] leftPart, Object[] rightPart ) {

        int cursorLeft = 0, cursorRight = 0, counter = 0;

        Object[] merged = new Object[leftPart.length + rightPart.length];



        while ( cursorLeft < leftPart.length && cursorRight < rightPart.length ) {

            if (c.compare(leftPart[cursorLeft],rightPart[cursorRight])<0) {
               // if(true){
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
    public Object[] getSorted2() {

        return sorted2;

    }
    /**
     * Prepares the input array of objects to sort and using a method "sort" that sorts it.
     * @param elementData Array of objects
     * @param size  The length of the array
     * @return The sorted array
     */

    @Override
    public Object[] sort(Object[] elementData, int size, Comparator comparator) {
        c=comparator;
        Object[] elementTemp = new Object[size];
        System.arraycopy(elementData,0,elementTemp,0,size);

        SimpleMerger ss=new SimpleMerger(elementTemp,c);

        ss.sort();

        if(comparator==null){
            elementData=ss.getSorted();
        }else{
            elementData=ss.getSorted2();
        }

        return elementData;
    }


}