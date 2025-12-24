package Benchmark;

import Storage.ArrayCollection;
import Storage.Collection;
import Storage.LinkedListCollection;
import Models.Student;

import java.util.Random;

public class BenchmarkMain {

    private static final int SIZE = 50000;

    public static void main(String[] args) {
        run(new ArrayCollection(), "ArrayCollection");
        run(new LinkedListCollection(), "LinkedListCollection");
    }

    private static void run(Collection collection, String name) {
        long start;
        long end;

        Random random = new Random();
        start = System.currentTimeMillis();
        for (int i = 1; i <= SIZE; i++) {
            collection.insertOne(new Student(i, "Name" + i, random.nextDouble(20)));
        }
        end = System.currentTimeMillis();
        print(name, "insert 50000", end - start);

        start = System.currentTimeMillis();
        for (int i = 1; i <= 500; i++) {
            collection.deleteOne(i);
        }
        end = System.currentTimeMillis();
        print(name, "delete first 500", end - start);

        start = System.currentTimeMillis();;
        for (int i = SIZE; i > SIZE - 500; i--) {
            collection.deleteOne(i);
        }
        end = System.currentTimeMillis();
        print(name, "delete last 500", end - start);


        start = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            int id = random.nextInt(SIZE) + 1;
            collection.findByID(id);
        }
        end = System.currentTimeMillis();
        print(name, "find random 500", end - start);

        System.out.println();
    }

    private static void print(String structure, String operation, long time) {
        System.out.println(structure + " | " + operation + " | " + time + " ms");
    }
}
