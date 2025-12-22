package Storage;

import Models.Student;
import java.util.ArrayList;
import java.util.List;

public class LinkedListCollection implements Collection {
    private class Node {
        Student data;
        Node next;
        Node prev;
    }
    // TODO: Manage head/tail

    public void insertOne(Student student) {
        // TODO: Implement
    }
    public void deleteOne(int id) {
        // TODO: Implement
    }
    public Student findByID(int id) {
        // TODO: Implement
        return null;
    }
    public List<Student> findAll() {
        return new ArrayList<>();
    }
}
