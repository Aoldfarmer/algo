package chapter4;

/**
 * @author koou
 * @version 1.0
 * @since 2017-11-08 下午 20:35
 */
public class AvlTree<T extends Comparable<? super T>> {

    private static final int ALLOWED_IMBALANCE = 1;
    private AvlNode<T> root;

    public AvlTree() {
        root = null;
    }



    //----------------private method------------

    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    private AvlNode<T> insert(T t, AvlNode<T> root) {
        if (t == null) {
            return new AvlNode<T>(t, null, null);
        }

        int compareResult = t.compareTo(root.element);

        if (compareResult < 0) {
            root.left = insert(t, root.left);
        } else if (compareResult > 0) {
            root.right = insert(t, root.right);
        } else {
            //do nothing
        }

        return balance(root);
    }


    private AvlNode<T> remove(T t, AvlNode<T> root) {
        if (t == null) {
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
        return balance(root);
    }

    private AvlNode<T> findMin(AvlNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    private AvlNode<T> balance(AvlNode<T> root) {
        if (root == null) {
            return null;
        }

        if (height(root.left) - height(root.right) > ALLOWED_IMBALANCE) {
            if (height(root.left.left) >= height(root.left.right)) {
                root = rotateWithLeftChild(root);
            } else {
                root = doubleWithLeftChild(root);
            }
        } else if (height(root.right) - height(root.left) > ALLOWED_IMBALANCE) {
            if (height(root.right.right) >= height(root.right.left)) {
                root = rotateWithRightChild(root);
            } else {
                root = doubleWithRightChild(root);
            }
        } else {
            //do nothing
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        return root;
    }


    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
        AvlNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(k1.height, height(k2.right)) + 1;
        return k2;
    }


    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }


    private AvlNode<T> doubleWithRightChild(AvlNode<T> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }


    //----------------inner class---------------
    private static class AvlNode<T> {
        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;

        AvlNode(T element) {
            this(element, null, null);
        }

        AvlNode(T element, AvlNode<T> lt, AvlNode<T> rt) {
            this.element = element;
            this.left = lt;
            this.right = rt;
            height = 0;
        }
    }

}
