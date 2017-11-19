package sorts;


import java.util.Comparator;

/**
 * For various sorts
 */
public interface SortI{
    Object[] sort(Object[] elementData, int size, Comparator comparator);
}
