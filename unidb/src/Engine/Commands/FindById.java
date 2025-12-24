package Engine.Commands;

import Engine.Command;
import Presentation.ConsoleOutput;
import Storage.Collection;

public class FindById {
    public void execute(Command command, Collection collection) {
        ConsoleOutput.printStudent
                (collection.findByID
                        (Integer.parseInt(command.getArguments().getFirst())));
    }
}

