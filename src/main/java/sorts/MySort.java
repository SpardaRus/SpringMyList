package sorts;
/**
 * This class is a shell sort.
 * He implements SortI.
 */
public class MySort implements SortI{
    /**
     * Produced by sorting Shell the input array with a step of 3
     * @param array Array objects
     */
    public static void sort(Comparable[] array) {

        int h = 1;
        while (h * 3 < array.length)
            h = h * 3 + 1;

        while (h >= 1) {
            hSort(array, h);
            h = h / 3;
        }

    }

    /**Produce insertion sort with a stepProduce insertion sort with a step
     * @param array Array objects
     * @param h step
     */
    private static void hSort(Comparable[] array, int h) {
        int length = array.length;
        for (int i = h; i < length; i++) {
            for (int j = i; j >= h; j = j - h) {
                if (array[j].compareTo( array[j - h])<0)

                    swap(array, j, j - h);
                else
                    break;
            }
        }
    }

    /**
     * Swap the array elements
     * @param array Array objects
     * @param i Position item1
     * @param j Position item2
     */
    private static void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    /**
     * Prepares the input array of objects to sort and using a method "run" that sorts it.
     * @param elementData Array of objects
     * @param size  The length of the array
     * @return The sorted array
     */
    @Override
    public Object[] sort(Object[] elementData, int size) {

            Comparable[] elementTemp=new Comparable[size]; //Sorted MySort(Shell)
            for (int i = 0; i < size; i++) {
                elementTemp[i] = (Comparable)elementData[i];
            }
            sorts.MySort.sort(elementTemp);

        return elementTemp;
    }
}

