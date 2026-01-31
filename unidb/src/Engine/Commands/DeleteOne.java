package Engine.Commands;

import Engine.Command;
import Engine.IndexManager;
import Index.Index;
import Storage.Collection;

public class DeleteOne {
    public void execute(Command command, Collection collection, IndexManager indexManager) {
        collection.deleteOne
                (Integer.parseInt(command.getArguments().getFirst()));

        indexManager.insertOne(command);
    }
}

