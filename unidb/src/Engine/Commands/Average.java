package Engine.Commands;

import Models.Student;
import Presentation.ConsoleOutput;
import Storage.Collection;

public class Average {
    public void execute (Collection collection) {
        double sum = 0;
        int count = collection.findAll().size();

        for (Student s : collection.findAll())
            sum += s.getGpa();

        ConsoleOutput.printAverage(sum / count);
    }
}

