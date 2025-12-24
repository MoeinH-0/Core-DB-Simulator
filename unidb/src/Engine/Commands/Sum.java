package Engine.Commands;

import Models.Student;
import Presentation.ConsoleOutput;
import Storage.Collection;

public class Sum {
    public void execute(Collection collection) {
        double sum = 0;

        for (Student s : collection.findAll())
            sum += s.getGpa();

        ConsoleOutput.printSum(sum);
    }
}

