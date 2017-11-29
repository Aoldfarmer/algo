package chapter6;

/**
 * @author koou
 * @version 1.0
 * @since 2017-11-29 下午 21:11
 */
public class LeftistHeap<T extends Comparable<? super T>> {

    private Node<T> root;


    public void merge(LeftistHeap<T> rhs) {
        if (this == rhs) {
            return;
        }
        root = merge(root, rhs.root);
        rhs.root = null;
    }

    public void insert(T t) {
        root = merge(new Node<T>(t), root);
    }

    public T findMin() {
        return root.element;
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        T minItem = root.element;
        root = merge(root.left, root.right);

        return minItem;
    }

    public LeftistHeap() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;
        int npl;

        Node(T element) {
            this(element, null, null);
        }

        Node(T element, Node<T> lt, Node<T> rt) {
            this.element = element;
            this.left = lt;
            this.right = rt;
            npl = 0;
        }
    }


    private Node<T> merge(Node<T> h1, Node<T> h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        if (h1.element.compareTo(h2.element) < 0) {
            return merge1(h1, h2);
        } else {
            return merge1(h2, h1);
        }
    }

    private Node<T> merge1(Node<T> h1, Node<T> h2) {
        if (h1.left == null) {
            h1.left = h2;
        } else {
            h1.right = merge(h1.right, h2);
            if (h1.left.npl < h1.right.npl) {
                swapChildren(h1);
            }
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    private void swapChildren(Node<T> t) {
        Node<T> temp = t.left;
        t.left = t.right;
        root.right = temp;
    }
}
