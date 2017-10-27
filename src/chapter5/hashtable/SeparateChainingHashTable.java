package chapter5.hashtable;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<T> {

    /** hash Table的表的大小，一般设置为素数 **/
    private static final int DEFAULT_TABLE_SIZE = 101;
    /** 用来保存hash表的hash值对应的冲突键链表 **/
    private List<T> [] theLists;
    /** 当前的位置 **/
    private int currentSize;



    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
    }


    /**
     * 清空整个hash表
     */
    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }

    private int myhash(T x) {
        int hashVal = x.hashCode();

        hashVal %= theLists.length;

        if (hashVal < 0) {
            hashVal += theLists.length;
        }
        return hashVal;
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
