package list;

import java.util.*;
import java.util.function.*;

public class MyList<E> extends AbstractList<E> implements List<E>, Cloneable {


    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};


    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};


    transient Object[] elementData;

    private int size;

    public MyList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }


    public MyList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {

            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {

            this.elementData = EMPTY_ELEMENTDATA;
        }
    }



    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,
                newCapacity(minCapacity));
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private int newCapacity(int minCapacity) {
          int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }


    public Object clone() {
        try {
            MyList<?> v = (MyList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }


    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }



    public <T> T[] toArray(T[] a) {
        if (a.length < size)

            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }




    E elementData(int index) {
        return (E) elementData[index];
    }

    static <E> E elementAt(Object[] es, int index) {
        return (E) es[index];
    }


    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }


    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        remove(index);
        add(element);

        return (E)elementData;
    }


    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }


    private void sorts(){
        try{
//            Comparable[] elementTemp=new Comparable[size()]; //Sorted MultiMerger
//            for (int i = 0; i < size(); i++) {
//                elementTemp[i] = (Comparable)elementData[i];
//            }
//            MultiMerger ms=new MultiMerger(elementTemp);
//            ms.run();
//            elementData=ms.getSorted();
           elementData=sorts.Sorts.sort(elementData,size()); //Sorted One
//            Comparable[] elementTemp=new Comparable[size()]; //Sorted MySort(Shell)
//            for (int i = 0; i < size(); i++) {
//                elementTemp[i] = (Comparable)elementData[i];
//            }
//            sorts.MySort.sort(elementTemp);
//            elementData=elementTemp;

        }catch(Exception e){
             System.out.println("Class '"+elementData(0).getClass().getName()+"' must implements Comparable. Object '"+elementData(0).getClass().getName()+"' inserted in the order");
        }
    }

    public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        sorts();
        return true;
    }


    public void add(int index, E element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = element;
        size = s + 1;
        sorts();
    }


    public E remove(int index) {
        Objects.checkIndex(index, size);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null;

        return oldValue;
    }


    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }


    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null;
    }


    public void clear() {
        modCount++;
        final Object[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }


    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0)
            return false;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = grow(s + numNew);
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        sorts();
        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        modCount++;
        int numNew = a.length;
        if (numNew == 0)
            return false;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = grow(s + numNew);

        int numMoved = s - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index,
                    elementData, index + numNew,
                    numMoved);
        System.arraycopy(a, 0, elementData, index, numNew);
        size = s + numNew;
        sorts();
        return true;
    }


    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }


    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }


    private static String outOfBoundsMsg(int fromIndex, int toIndex) {
        return "From Index: " + fromIndex + " > To Index: " + toIndex;
    }


    public Iterator<E> iterator() {
        return new Itr();
    }


    private class Itr implements Iterator<E> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;


        Itr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = MyList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                MyList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            final int size = MyList.this.size;
            int i = cursor;
            if (i < size) {
                final Object[] es = elementData;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                for (; i < size && modCount == expectedModCount; i++)
                    action.accept(elementAt(es, i));
                // update once at end to reduce heap write traffic
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}

