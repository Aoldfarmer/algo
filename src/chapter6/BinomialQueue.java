package chapter6;

public class BinomialQueue<T extends Comparable<? super T>> {

    private int currentSize;
    private static final int DEFAULT_TREES = 1;
    private Node<T> [] theTrees;

    public BinomialQueue() {
        theTrees = new Node[DEFAULT_TREES];
    }

    public BinomialQueue(T element) {
        currentSize = 1;
        theTrees = new Node[1];
        theTrees[0] = new Node<T>(element);
    }


    public void merge(BinomialQueue<T> rhs) {
        if (this == rhs) {
            return;
        }

        currentSize += rhs.currentSize;

        if (currentSize > capacity()) {
            int maxLength = Math.max(theTrees.length, rhs.theTrees.length);
            expandTheTrees(maxLength + 1);
        }
        Node<T> carry = null;
        for (int i = 0, j = 1;j <= currentSize; i++, j *= 2) {
            Node<T> t1 = theTrees[i];
            Node<T> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch (whichCase) {
                case 0:
                case 1:
                    break;
                case 2:
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                case 3:
                    carry = combineTrees(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                case 4:
                    theTrees[i] = carry;
                    carry = null;
                    break;
                case 5:
                    carry = combineTrees(t1, carry);
                    theTrees[i] = null;
                    break;
                case 6:
                    carry = combineTrees(t2, carry);
                    theTrees[i] = null;
                    break;
                case 7:
                    theTrees[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.theTrees[i] = null;
                    break;
            }
        }

        for (int k = 0; k < rhs.theTrees.length; k++) {
            rhs.theTrees[k] = null;
        }
        rhs.currentSize = 0;
    }

    public  T deleMin() {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        int minIndex = findMinIndex();
        T minItem = theTrees[minIndex].element;

        Node<T> deletedTree = theTrees[minIndex].leftChild;

        BinomialQueue<T> deletedQueue = new BinomialQueue<T>();
        deletedQueue.expandTheTrees(minIndex + 1);

        deletedQueue.currentSize = (1 << minIndex) - 1;
        for (int j = minIndex - 1; j >= 0; j--) {
            deletedQueue.theTrees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[j].nextSibling = null;
        }

        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;

        merge(deletedQueue);
        return minItem;
    }

    public void insert(T element) {
        merge(new BinomialQueue<>(element));
    }

    public T findMin() {
        return theTrees[findMinIndex()].element;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        for (int k = 0; k < theTrees.length; k++) {
            theTrees[k] = null;
        }
        currentSize = 0;
    }

    private int capacity() {
        return (1 << theTrees.length) - 1;
    }

    private Node<T> combineTrees(Node<T> t1, Node<T> t2) {
        if (t1.element.compareTo(t2.element) > 0) {
            return combineTrees(t2, t1);
        }
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    private void expandTheTrees(int newNumTrees) {
        Node<T> [] newTrees = new Node[newNumTrees];
        for (int i = 0; i < Math.min(theTrees.length, newNumTrees); i++) {
            newTrees[i] = theTrees[i];
        }
        theTrees = newTrees;
    }
    private static class Node<T> {
        T element;
        Node<T> leftChild;
        Node<T> nextSibling;

        Node(T element) {
            this(element, null, null);
        }

        Node(T element, Node<T> lt, Node<T> nt) {
            this.element = element;
            this.leftChild = lt;
            this.nextSibling = nt;
        }
    }

    private int findMinIndex() {
        T min = theTrees[0].element;
        int index = 0;
        for (int i = 0; i < theTrees.length; i++) {
            if (theTrees[i].element.compareTo(min) < 0) {
                index = i;
            }
        }
        return index;
    }
}
