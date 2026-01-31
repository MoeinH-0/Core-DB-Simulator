package Index.Tree;

import Index.Index;

import java.util.ArrayList;
import java.util.Comparator;

public class AVLTree<K, V> implements Index<K, V> {

    private AVLNode<K, V> root;
    private final Comparator<K> comparator;

    public AVLTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void insertOne(K k, V v) {
        AVLNode<K, V> current = root;
        AVLNode<K, V> parent = null;

        if (root == null) {
            root = new AVLNode<>(k, v, null);
            return;
        }

        while (current != null) {
            parent = current;
            int cmp = comparator.compare(k, current.k);
            if (cmp == 0) {
                current.v = v;
                return;
            }
            if (cmp > 0)
                current = current.right;
            else
                current = current.left;
        }

        current = new AVLNode<K, V>(k, v, parent);
        if (comparator.compare(k, parent.k) > 0)
            parent.right = current;
        else
            parent.left = current;

        updateHeight(current);

        AVLNode<K, V> z = getUnBalanceNode(current.parent);
        if (z == null)
            return;

        AVLNode<K, V> y = getHeight(z.left) > getHeight(z.right) ? z.left : z.right;
        AVLNode<K, V> x = getHeight(y.left) >= getHeight(y.right) ? y.left : y.right;
        reStructure(x);
    }

    @Override
    public void deleteOne(K k, V v) {
        AVLNode<K, V> node = findNode(k);
        if (node == null || !v.equals(node.v))
            return;

        AVLNode<K, V> start;

        if (node.left != null && node.right != null) {
            AVLNode<K, V> tmp = node.right;
            while (tmp.left != null)
                tmp = tmp.left;

            node.k = tmp.k;
            node.v = tmp.v;
            node = tmp;
        }

        AVLNode<K, V> child = (node.left != null) ? node.left : node.right;
        start = node.parent;

        if (child != null)
            child.parent = node.parent;

        if (node.parent == null) {
            root = child;
        } else if (node == node.parent.left) {
            node.parent.left = child;
        } else {
            node.parent.right = child;
        }

        while (start != null) {
            updateHeight(start);

            AVLNode<K, V> z = getUnBalanceNode(start);
            if (z != null) {
                AVLNode<K, V> y = getHeight(z.left) > getHeight(z.right) ? z.left : z.right;
                AVLNode<K, V> x;
                if (getHeight(y.left) == getHeight(y.right))
                    x = (y == z.left) ? y.left : y.right;
                else
                    x = getHeight(y.left) > getHeight(y.right) ? y.left : y.right;

                reStructure(x);
            }
            start = start.parent;
        }
    }

    @Override
    public ArrayList<V> search(K k) {
        AVLNode<K, V> node = findNode(k);
        if (node == null)
            return null;

        ArrayList<V> result = new ArrayList<>();
        result.add(node.v);
        return result;
    }

    private AVLNode<K, V> findNode(K k) {
        AVLNode<K, V> current = root;
        while (current != null) {
            int cmp = comparator.compare(k, current.k);
            if (cmp == 0)
                return current;
            if (cmp > 0)
                current = current.right;
            else
                current = current.left;
        }
        return null;
    }

    private void reStructure(AVLNode<K, V> x) {
        AVLNode<K, V> y = x.parent;
        AVLNode<K, V> z = y.parent;
        if ((y == z.left) == (x == y.left)) {
            rotate(y);
        } else {
            rotate(x);
            rotate(x);
        }
    }

    private void rotate(AVLNode<K, V> x) {
        AVLNode<K, V> y = x.parent;
        AVLNode<K, V> z = y.parent;

        if (z == null) {
            root = x;
            x.parent = null;
        } else
            reLink(z, x, y == z.left);

        if (x == y.left) {
            reLink(y, x.right, true);
            reLink(x, y, false);
        } else {
            reLink(y, x.left, false);
            reLink(x, y, true);
        }

        updateHeight(y);
        updateHeight(x);
    }

    private void reLink(AVLNode<K, V> parent, AVLNode<K, V> child, boolean isLeft) {
        if (child != null)
            child.parent = parent;
        if (isLeft)
            parent.left = child;
        else
            parent.right = child;
    }

    private int getHeight(AVLNode<K, V> node) {
        return node == null ? -1 : node.height;
    }

    private void updateHeight(AVLNode<K, V> node) {
        while (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            node = node.parent;
        }
    }

    private AVLNode<K, V> getUnBalanceNode(AVLNode<K, V> node) {
        while (node != null) {
            if (Math.abs(getHeight(node.left) - getHeight(node.right)) > 1)
                return node;
            node = node.parent;
        }
        return null;
    }
}

class AVLNode<K, V> {
    AVLNode<K, V> parent;
    AVLNode<K, V> left;
    AVLNode<K, V> right;
    K k;
    V v;
    int height;

    AVLNode(K k, V v, AVLNode<K, V> parent) {
        this.k = k;
        this.v = v;
        this.parent = parent;
        this.height = 0;
    }
}
