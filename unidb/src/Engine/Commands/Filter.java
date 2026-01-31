package Engine.Commands;

import Engine.Command;
import Engine.IndexManager;
import Models.Student;
import Presentation.ConsoleOutput;
import Storage.Collection;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    public void execute(Command command, Collection collection, IndexManager indexManager) {
        String field = command.getArguments().getFirst();
        String value = command.getArguments().getLast();
        List<Student> filteredStudents = new ArrayList<>();

        if (indexManager.isExist(command))
            filteredStudents = indexManager.filter(command);

        else
            for (Student student : collection.findAll()) {
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
                        if (Double.toString(student.getGpa()).equals(value)) {
                            filteredStudents.add(student);
                        }

                        break;
                }
            }

        ConsoleOutput.printStudents(filteredStudents);
    }


}

