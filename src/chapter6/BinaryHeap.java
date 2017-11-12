package chapter6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author koou
 * @version 1.0
 * @since 2017-11-12 下午 19:26
 */
public class BinaryHeap<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private T[] array;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public BinaryHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for (T item: items) {
            array[i++] = item;
        }
        buildHeap();
    }

    public void insert(T t) {
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }

        int hole = ++currentSize;
        for (array[0] = t; t.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = t;
    }


    public T findMin() {
        return array[1];
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    public T deleteMin() {
        if(isEmpty()) {
            throw new RuntimeException("is Empty");
        }

        T minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }



    private void percolateDown(int hole) {
        int child;
        T tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize &&
                    array[child + 1].compareTo(array[child]) < 0) {
                child++;
            }
            if (array[child].compareTo(tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tmp;
    }

    private void enlargeArray(int newSize) {
        array = Arrays.copyOf(array, newSize);
    }


}
