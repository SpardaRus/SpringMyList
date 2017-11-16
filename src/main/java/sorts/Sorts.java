package sorts;

public class Sorts {


    public static Object[] sort(Object[] elementData, int size, int e) {

        if (size > 0) {
            Comparable[] elementTemp = new Comparable[size];
            for (int i = 0; i < size; i++) {
                elementTemp[i] = (Comparable) elementData[i];
            }
            if (e == size - 1) {
                for (int i = 0; i < size; i++) {
                    if (elementTemp[e].compareTo(elementTemp[i]) < 0) {
                        Comparable b = elementTemp[i];
                        elementTemp[i] = elementTemp[e];
                        elementTemp[e] = b;
                    }
                }
            } else {
                Comparable o = elementTemp[e];
                int count = size - 1;
                for (int i = 0; i < size; i++) {
                    if ((elementTemp[e].compareTo(elementTemp[i]) < 0)) {
                        count = i;
                        break;
                    }
                }
                if (e < count) {
                    for (int i = e; i < count-1; i++) {
                        elementTemp[i] = elementTemp[i + 1];

                    }
                    elementTemp[count-1] = o;

                } else {
                    for (int i = e; i > count; i--) {
                        elementTemp[i] = elementTemp[i - 1];

                    }
                    elementTemp[count] = o;
                }

            }

            elementData = elementTemp;
        }
        return elementData;
    }
}
