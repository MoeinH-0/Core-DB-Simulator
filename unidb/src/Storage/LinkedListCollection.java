package Storage;

import Models.Student;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListCollection implements Collection {

    private final LinkedList<Student> students = new LinkedList<>();


    public void insertOne(Student student) {
        students.add(student);
    }

    public void deleteOne(int id) {
        students.remove(id);
    }

    public Student findByID(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }
}
