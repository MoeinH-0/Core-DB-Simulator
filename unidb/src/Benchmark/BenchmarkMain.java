package Benchmark;

import Index.Tree.AVLTree;
import Index.Tree.BST;

public class BenchmarkMain {

    public static void main(String[] args) {
        BST<Integer,String> bst = new BST<>(Integer::compareTo);
        AVLTree<Integer,String> avlTree = new AVLTree<>(Integer::compareTo);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
            bst.insertOne(i,"bst");
        bst.search(50000);
        long end = System.currentTimeMillis();
        System.out.println("BST Search time: " + (end - start));


        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
            avlTree.insertOne(i,"bst");
        avlTree.search(50000);
        end = System.currentTimeMillis();
        System.out.println("AVLTree Search time: " + (end - start));
    }
}
