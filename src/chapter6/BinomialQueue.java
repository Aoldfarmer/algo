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
}
