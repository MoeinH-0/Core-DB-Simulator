package Engine;

import Index.Hash.HashIndex;
import Index.Hash.InvertedIndex;
import Index.Index;
import Index.Tree.AVLTree;
import Index.Tree.BST;
import Models.Student;
import Storage.Collection;

import java.util.*;

public class IndexManager {
    Collection collection;
    HashMap<String, Index> indexes;
    Set<String> keys;

    IndexManager(Collection collection) {
        this.collection = collection;
        indexes = new HashMap<>();
        keys = new HashSet<>();
    }

    public void insertOne(Command command) {
        Student s =
                (new Student
                        (Integer.parseInt(command.getArguments().getFirst()),
                                (command.getArguments().get(1)),
                                Double.parseDouble(command.getArguments().get(2))));

        for (String k : keys) {
            if (indexes.containsKey(k)) {
                Index index = indexes.get(k);
                switch (k) {
                    case "gpa":
                        index.insertOne(s.getGpa(), s);
                        break;
                    case "id":
                        index.insertOne(s.getId(), s);
                        break;
                    case "name":
                        index.insertOne(s.getName(), s);
                        break;
                }
            }
        }
    }

    public void deleteOne(Command command, Student s) {
        for (String k : keys) {
            if (indexes.containsKey(k))
                indexes.get(k).
                        deleteOne(command.getArguments().getFirst(), s);

        }
    }

    public boolean isExist(Command command) {
        return keys.contains(command.getArguments().getFirst());
    }

    public ArrayList<Student> filter(Command command) {
        String field = command.getArguments().getFirst();
        String value = command.getArguments().getLast();

        Index index = indexes.get(command.getArguments().getFirst());
        switch (field) {
            case "name":
                return index.search(value);

            case "id":
                return index.search(Integer.valueOf(value));

            case "gpa":
                return index.search(Double.parseDouble(value));
        }

        return null;
    }

    public void createIndex(Command command) {
        String field = command.getArguments().getFirst();
        String value = command.getArguments().getLast();
        keys.add(field);

        switch (value) {
            case "bst":
                AddBST(field);
                break;
            case "avl":
                AddAVL(field);
                break;
            case "hash":
                AddHash(field);
                break;
            case "inverted":
                AddInverted(field);
                break;
        }

        Index index = indexes.get(field);
        for (Student s : collection.findAll())
            switch (field) {
                case "gpa":
                    index.insertOne(s.getGpa(), s);
                    break;
                case "id":
                    index.insertOne(s.getId(), s);
                    break;
                case "name":
                    index.insertOne(s.getName(), s);
                    break;
            }
    }

    private void AddBST(String field) {
        switch (field) {
            case "gpa":
                indexes.put(field, new BST<Double, Student>(Double::compare));
                break;
            case "id":
                indexes.put(field, new BST<Integer, Student>(Integer::compare));
                break;
            case "name":
                indexes.put(field, new BST<String, Student>(String::compareTo));
                break;
        }
    }

    private void AddAVL(String field) {
        switch (field) {
            case "gpa":
                indexes.put(field, new AVLTree<Double, Student>(Double::compare));
                break;
            case "id":
                indexes.put(field, new AVLTree<Integer, Student>(Integer::compare));
                break;
            case "name":
                indexes.put(field, new AVLTree<String, Student>(String::compareTo));
                break;
        }
    }

    private void AddHash(String field) {
        switch (field) {
            case "gpa":
                indexes.put(field, new HashIndex<Double, Student>(100));
                break;
            case "id":
                indexes.put(field, new HashIndex<Integer, Student>(100));
                break;
            case "name":
                indexes.put(field, new HashIndex<String, Student>(100));
                break;
        }
    }

    private void AddInverted(String field) {
        indexes.put(field, new InvertedIndex());
    }

}
