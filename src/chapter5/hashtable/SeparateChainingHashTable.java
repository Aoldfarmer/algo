package chapter5.hashtable;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<T> {

    /** hash Table的表的大小，一般设置为素数 **/
    private static final int DEFAULT_TABLE_SIZE = 101;
    /** 用来保存hash表的hash值对应的冲突键链表 **/
    private List<T> [] theLists;
    /** 当前hash表中元素的个数 **/
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
     * 插入实体
     * @param t t
     */
    public void insert(T t) {
        List<T> whichList = theLists[myhash(t)];
        if (!whichList.contains(t)) {
            whichList.add(t);

            if (++currentSize > theLists.length) {
                //hash表中的元素个数大于hash桶的个数，rehash操作
            }
        }
    }

    /**
     * 查询是否存在
     * @param t t
     * @return boolean
     */
    public boolean contains(T t) {
        List<T> whichList = theLists[myhash(t)];
        return  whichList.contains(t);
    }

    /**
     * 删除某个实体
     * @param t t
     */
    public void remove(T t) {
        List<T> whichList = theLists[myhash(t)];
        if (whichList.contains(t)) {
            whichList.remove(t);
            currentSize--;
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


    private void rehash() {

        List<T> [] oldLists = theLists;

        //选择扩展到2倍的素数大小
        theLists = new List[nextPrime(theLists.length)];

        for (int j = 0; j < theLists.length; j++) {
            theLists[j] = new LinkedList<>();
        }

        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++) {
            for (T t : oldLists[i]) {
                insert(t);
            }
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
