package sorts;

public class MySort implements SortI{
    public static void sort(Comparable[] array) {

        int h = 1;
        while (h * 3 < array.length)
            h = h * 3 + 1;

        while (h >= 1) {
            hSort(array, h);
            h = h / 3;
        }

    }

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
    private static void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

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

