package chapter5.hashtable;

import java.util.Random;

public class CuckooHashTable<T> {
    /** 最大的装填因子 **/
    private static final double MAX_LOAD = 0.4;
    /** 允许再散列的次数 **/
    private static final int ALLOWED_REHASHES = 1;
    /** 默认的hash表的大小 **/
    private static final int DEFAULT_TABLE_SIZE = 101;
    private int rehashes = 0;
    private Random r = new Random();
    private final HashFamily<? super T> hashFunctions;
    private final int numHashFunctions;
    private T[] array;
    /** 表的当前大小 **/
    private int currentSize;

    public CuckooHashTable(HashFamily<? super T> hf) {
        this(hf, DEFAULT_TABLE_SIZE);
    }

    public CuckooHashTable(HashFamily<? super T> hf, int size) {
        allocateArray(nextPrime(size));
        doClear();
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
    }


    public boolean remove(T t) {
        int pos = findPos(t);

        if (pos != -1) {
            array[pos] = null;
            currentSize--;
        }
        return pos != -1;
    }

    public boolean insert(T t) {
        if (contains(t)) {
            return false;
        }
        if (currentSize >= array.length * MAX_LOAD) {
            expand();
        }

        return insertHelper(t);
    }

    private void expand() {
        rehash((int) (array.length / MAX_LOAD));
    }

    private boolean insertHelper(T t) {
        final int COUNT_LIMIT = 100;

        while (true) {
            int lastPos = -1;
            int pos;

            for (int count = 0; count < COUNT_LIMIT; count++)  {

                for (int i = 0; i < numHashFunctions; i++) {
                    pos = myhash(t, i);

                    if (array[pos] == null) {
                        array[pos] = t;
                        currentSize++;
                        return true;
                    }
                }

                int i = 0;
                do {
                    pos = myhash(t, r.nextInt(numHashFunctions));
                } while (pos == lastPos && i++ < 5);

                T tmp = array[lastPos = pos];
                array[pos] = t;
                t = tmp;
            }

            if (++rehashes > ALLOWED_REHASHES) {
                expand();
                rehashes = 0;
            } else {
                rehash();
            }
        }
    }


    private void rehash() {
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void rehash(int newLength) {
        T[] oldArray = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;
        for (T str: oldArray) {
            if (str != null) {
                insert(str);
            }
        }
    }

    public void makeEmpty() {
        doClear();
    }

    public boolean contains(T t) {
        return findPos(t) != -1;
    }

    private int myhash(T t, int which) {
        int hashVal = hashFunctions.hash(t, which);

        hashVal %= array.length;
        if (hashVal < 0) {
            hashVal += array.length;
        }
        return hashVal;
    }

    private int findPos(T t) {
        for (int i = 0; i < numHashFunctions; i++) {
            int pos = myhash(t, i);
            if (array[pos] != null && array[pos].equals(t)) {
                return pos;
            }
        }
        return -1;
    }

    private void allocateArray(int arraySize) {
        array = (T[]) new Object[arraySize];
    }

    private void doClear() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    private static int nextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(i); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
