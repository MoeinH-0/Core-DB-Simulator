package Engine;

import Engine.Enum.CollectionType;
import Engine.Enum.CommandType;

import java.util.ArrayList;

public class Command {
    private final CommandType commandType;
    private final ArrayList<String> arguments;
    private final CollectionType collectionType;

    public Command(CommandType commandType, ArrayList<String> arguments, CollectionType collectionType) {
        this.commandType = commandType;
        this.arguments = arguments;
        this.collectionType = collectionType;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }
}
