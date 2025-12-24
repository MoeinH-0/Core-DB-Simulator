package Storage;

import Models.Student;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    public List<Student> filter(String field, String value, List<Student> students) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            switch (field) {
                case "name":
                    if (student.getName().equals(value))
                        filteredStudents.add(student);

                    break;
                case "id":
                    if (Integer.toString(student.getId()).equals(value))
                        filteredStudents.add(student);

                    break;
                case "gpa":
                    if (Double.toString(student.getGpa()).equals(value))
                        filteredStudents.add(student);

                    break;
            }
        }
        return filteredStudents;
    }
}
