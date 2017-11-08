package chapter4;

public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T t) {
        return contains(t, root);
    }


    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("is empty");
        }

        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("is empty");
        }

        return findMax(root).element;
    }

    public void insert(T t) {
        root = insert(t, root);
    }

    public void remove(T t) {
        root = remove(t, root);
    }







    //-------------- private method---------------


    private boolean contains(T t, BinaryNode<T> root) {
        if (root == null) {
            return false;
        }

        int compareResult = t.compareTo(root.element);

        if (compareResult < 0) {
            return contains(t, root.left);
        } else if (compareResult > 0) {
            return contains(t, root.right);
        } else {
            return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }
        return findMax(t.right);
    }

    private BinaryNode<T> insert(T t, BinaryNode<T> root) {
        if (t == null) {
            return new BinaryNode(t, null, null);
        }

        int compareResult = t.compareTo(root.element);

        if (compareResult < 0) {
            root.left = insert(t, root.left);
        } else if (compareResult > 0) {
            root.right = insert(t, root.right);
        } else {
            //do nothing
        }
        return root;
    }

    private BinaryNode<T> remove(T t, BinaryNode<T> root) {
        if (root == null) {
            return null;
        }

        int compareResult = t.compareTo(root.element);

        if (compareResult < 0) {
            root.left = remove(t, root.left);
        } else if (compareResult > 0) {
            root.right = remove(t, root.right);
        } else if (root.left != null && root.right != null) {
            root.element = findMin(root.right).element;
            root.right = remove(root.element, root.right);
        } else {
            root = (root.left != null) ? root.left : root.right;
        }
        return root;
    }

    //-------------inner class----------------

    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        public BinaryNode(T element) {
            this(element, null, null);
        }

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }


}
