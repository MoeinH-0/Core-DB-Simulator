package Benchmark;

import Index.Hash.HashIndex;
import Index.Tree.AVLTree;
import Models.Student;
import Storage.ArrayCollection;

public class Benchmark2Main {

        public static void main(String[] args) {
            ArrayCollection arrayCollection = new ArrayCollection();
            AVLTree<Integer,Student> avlTree = new AVLTree<>(Integer::compareTo);
            HashIndex<Integer, Student> hashIndex = new HashIndex<>(1000);

            for (int i = 0; i < 10000000; i++){
                Student s = new Student(i,"moein",i % 21);
                arrayCollection.insertOne(s);
                avlTree.insertOne(i,s);
                hashIndex.insertOne(i,s);
            }

            long start = System.currentTimeMillis();
            arrayCollection.findByID(80000000);
            long end = System.currentTimeMillis();
            System.out.println("ArrayCollection Search time (O(n)): " + (end - start));

            start = System.currentTimeMillis();
            avlTree.search(80000000);
            end = System.currentTimeMillis();
            System.out.println("AVLTree Search time (O(log(n))): " + (end - start));

            start = System.currentTimeMillis();
            hashIndex.search(80000000);
            end = System.currentTimeMillis();
            System.out.println("HashIndex Search time (O(1)): " + (end - start));
        }

}
