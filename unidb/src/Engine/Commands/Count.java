package Engine.Commands;

import Presentation.ConsoleOutput;
import Storage.Collection;

public class Count {
    public void execute(Collection collection) {
        ConsoleOutput.printCount(collection.findAll().size());
    }
}

