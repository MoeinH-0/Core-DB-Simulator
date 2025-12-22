package Storage;
import Models.Student;
import java.util.List;

public interface Collection {
    void insertOne(Student student);
    void deleteOne(int id);
    Student findByID(int id);
    List<Student> findAll();
}
