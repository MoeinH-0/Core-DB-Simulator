package Storage;

import Models.Student;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListCollection implements Collection {

    LinkedList<Student> students = new LinkedList<>();
    private final Filter filterFunction = new Filter();


    public void insertOne(Student student) {
        students.add(student);
    }

    public void deleteOne(int id) {
        students.remove(id);
    }

    public Student findByID(int id) {
        if (students.get(id) != null)
            return students.get(id);
        else
            return null;
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public List<Student> filter(String field, String value) {
        return filterFunction.filter(field, value,students);
    }
}
