package Storage;

import Models.Student;
import java.util.ArrayList;
import java.util.List;

public class ArrayCollection implements Collection {
    private final ArrayList<Student> students = new ArrayList<>();

    public void insertOne(Student student) {
        students.add(student);
    }

    public void deleteOne(int id) {
        students.removeIf(s -> s.getId() == id);
    }

    public Student findByID(int id) {
        for (Student s : students)
            if (s.getId() == id)
                return s;

        return null;
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }
}
