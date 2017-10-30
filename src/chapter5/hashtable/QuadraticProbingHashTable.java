package chapter5.hashtable;

public class QuadraticProbingHashTable<T> {

    /** 默认hash表的大小 **/
    private static final int DEFAULT_TABLE_SIZE = 11;
    /** 某个hash实体 **/
    private HashEntry<T>[] array;
    /** hash表当前的大小 **/
    private int currentSize;

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * 构造函数
     * @param size hash表大小
     */
    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty(); //?是否需要，allocateArray本身就是空表
    }

    /**
     * 清空hash表
     */
    public void makeEmpty() {
        currentSize = 0;
        for(int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    /**
     * 查询hash表中是否存在某个实体
     * @param t 实体
     * @return
     */
    public boolean contains(T t) {
        int currentPos = findPos(t);
        return isActive(currentPos);
    }

    /**
     * hash表中插入某个实体
     * @param t 实体
     */
    public void insert(T t) {
        int currentPos = findPos(t);
        if (isActive(currentPos)) {
            return;
        }

        array[currentPos] = new HashEntry<>(t, true);

        if (currentSize > array.length / 2) {
            //rehash
            rehash();
        }
    }

    /**
     * 删除某个hash(标识为删除)
     * @param t
     */
    public void remove(T t) {
        int currentPos = findPos(t);
        if (isActive(currentPos)) {
            array[currentPos].isActive = false;
        }
    }

    private static class HashEntry<T> {
        /** 放入hash表中的对象 **/
        public T element;
        /** 是否被标记为已删除：
         *  true -当前处于活动状态
         *  false -被删除
         **/
        public boolean isActive;

        public HashEntry(T t) {
            this(t, true);
        }

        public HashEntry(T t, boolean b) {
            this.element = t;
            this.isActive = b;
        }
    }


    private void rehash() {
        HashEntry<T> [] oldArray = array;

        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null && oldArray[i].isActive) {
                insert(oldArray[i].element);
            }
        }
    }

    /**
     * 查询实体在hash表中的当前位置
     * @param t 实体
     * @return hash表中的当前位置
     */
    private int findPos(T t) {
        int offset = 1;
        int currentPos = myhash(t);

        while (array[currentPos] != null &&
                !array[currentPos].element.equals(t)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length) {
                currentPos -= array.length;
            }
        }
        return currentPos;
    }

    /**
     * 查看当前位置的hash实体是否处于活动状态
     * @param currentPos 当前位置
     * @return true or false(被删除)
     */
    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }


    /**
     * 计算实体对应的hash表的hash值
     * @param t
     * @return
     */
    private int myhash(T t) {
        int hashVal = t.hashCode();

        hashVal %= array.length;

        if (hashVal < 0) {
            hashVal += array.length;
        }
        return hashVal;
    }

    /**
     * 初始化分配Hash数组
     * @param arraySize 数组大小
     */
    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
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
