package Index.Tree;


import Index.Index;

import java.util.ArrayList;
import java.util.Comparator;

public class BST<K, V> implements Index<K, V> {

    private Node<K, V> root;
    private final Comparator<K> comparator;

    public BST(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public void insertOne(K k, V v) {
        if (root == null) {
            root = new Node<>(k, v, null);
            return;
        }

        Node<K, V> current = root;
        Node<K, V> parent = null;

        while (current != null) {
            parent = current;
            int cmp = comparator.compare(k, current.k);
            if (cmp == 0) {
                current.v = v;
                return;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        Node<K, V> newNode = new Node<K, V>(k, v, parent);
        if (comparator.compare(k, parent.k) > 0)
            parent.right = newNode;
        else
            parent.left = newNode;
    }

    @Override
    public void deleteOne(K k, V v) {
        Node<K, V> node = findNode(k);
        if (node == null || !v.equals(node.v))
            return;

        if (node.left == null)
            transplant(node, node.right);
        else if (node.right == null)
            transplant(node, node.left);
        else {
            Node<K, V> successor = minimum(node.right);
            if (successor.parent != node) {
                transplant(successor, successor.right);
                successor.right = node.right;
                successor.right.parent = successor;
            }
            transplant(node, successor);
            successor.left = node.left;
            successor.left.parent = successor;
        }
    }

    @Override
    public ArrayList<V> search(K k) {
        Node<K, V> node = findNode(k);
        if (node == null)
            return null;

        ArrayList<V> result = new ArrayList<>();
        result.add(node.v);
        return result;
    }

    private Node<K, V> findNode(K k) {
        Node<K, V> current = root;
        while (current != null) {
            int cmp = comparator.compare(k, current.k);
            if (cmp == 0)
                return current;
            else if (cmp > 0)
                current = current.right;
            else
                current = current.left;
        }
        return null;
    }

    private void transplant(Node<K, V> u, Node<K, V> v) {
        if (u.parent == null)
            root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;

        if (v != null)
            v.parent = u.parent;
    }

    private Node<K, V> minimum(Node<K, V> node) {
        while (node.left != null)
            node = node.left;
        return node;
    }
}

class Node<K, V> {
    Node<K, V> parent;
    Node<K, V> left;
    Node<K, V> right;
    K k;
    V v;

    public Node(K k, V v, Node<K, V> parent) {
        this.parent = parent;
        this.k = k;
        this.v = v;
    }
}
