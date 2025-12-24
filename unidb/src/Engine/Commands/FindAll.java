package Engine.Commands;

import Presentation.ConsoleOutput;
import Storage.Collection;

public class FindAll {
    public void execute(Collection collection) {
        ConsoleOutput.printStudents(collection.findAll());
    }
}

