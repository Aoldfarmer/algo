package chapter5.hashtable;

public class CuckooHashTable<T> {
    /** 最大的装填因子 **/
    private static final double MAX_LOAD = 0.4;
    /** 允许再散列的次数 **/
    private static final int ALLOWED_REHASHES = 1;
    /** 默认的hash表的大小 **/
    private static final int DEFAULT_TABLE_SIZE = 101;

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
