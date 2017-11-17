package sorts;

public class Sorts implements SortI{


    public Object[] sort(Object[] elementData, int size) {

        if (size > 0) {
            Comparable[] elementTemp = new Comparable[size];
            for (int i = 0; i < size; i++) {
                elementTemp[i] = (Comparable) elementData[i];
            }

                for (int i = 0; i < size; i++) {
                    if (elementTemp[size-1].compareTo(elementTemp[i]) < 0) {
                        Comparable b = elementTemp[i];
                        elementTemp[i] = elementTemp[size-1];
                        elementTemp[size-1] = b;
                    }
                }

            elementData = elementTemp;
        }
        return elementData;
    }
}
