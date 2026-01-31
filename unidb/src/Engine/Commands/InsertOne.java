package Engine.Commands;

import Engine.Command;
import Engine.IndexManager;
import Models.Student;
import Storage.Collection;

public class InsertOne {
    public void execute(Command command, Collection collection, IndexManager indexManager) {
        collection.insertOne
                (new Student
                        (Integer.parseInt(command.getArguments().getFirst()),
                                (command.getArguments().get(1)),
                                Double.parseDouble(command.getArguments().get(2))));


        indexManager.insertOne(command);
    }
}

