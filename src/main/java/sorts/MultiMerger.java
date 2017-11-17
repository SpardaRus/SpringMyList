package sorts;

public class MultiMerger extends Thread implements SortI {


    private Comparable[] unsorted, sorted;


    // Ограничиваем максимальное количество запускаемых потоков

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


    public void run() {

        int middle;

        Comparable[] left, right;


        if (unsorted.length <= 1) {

            // Массив из 1 элемента точно отсортирован :)

            sorted = unsorted;

        } else {


            // Иначе делим массив на левую и правую части

            middle = unsorted.length / 2;


            left = new Comparable[middle];

            right = new Comparable[unsorted.length - middle];


            System.arraycopy(unsorted, 0, left, 0, middle);

            System.arraycopy(unsorted, middle, right, 0, unsorted.length - middle);


            // Пока не превысили максимальное количество потоков, запускаем рекурсивно новые потоки на 2-х

            // новых массивах

            if (activeCount() < MAX_THREADS) {

                MultiMerger leftSort = new MultiMerger(left);

                MultiMerger rightSort = new MultiMerger(right);


                leftSort.start();

                rightSort.start();


                // Лепим докучи, как только потоки дождутся друг друга

                try {

                    leftSort.join();

                    rightSort.join();


                    sorted = SimpleMerger.merge(leftSort.getSorted(), rightSort.getSorted());

                } catch (Exception e) {


                }


            } else {  // Тут уже новых потоков запускать нельзя - запускаем простой синглтредед алгоритм

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