package Index.Hash;

import Index.Index;
import Models.Student;

import java.util.ArrayList;

public class InvertedIndex implements Index<String, Student> {
    private final HashIndex<String, Student> map;

    public InvertedIndex() {
        map = new HashIndex<>(100);
    }

    @Override
    public void insertOne(String s, Student student) {
        String[] keys = s.split(" ");
        for (String key : keys)
            map.insertOne(key, student);
    }

    @Override
    public void deleteOne(String s, Student student) {
        String[] keys = s.split(" ");
        for (String key : keys)
            map.deleteOne(key, student);
    }

    @Override
    public ArrayList<Student> search(String s) {
        return map.search(s);
    }
}
