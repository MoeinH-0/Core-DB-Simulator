package Engine;

import Engine.Enum.CommandType;
import Storage.ArrayCollection;
import Storage.Collection;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ExecutionEngine {
    private final Collection currentCollection;
    private final Stack<Command> transactionStack = new Stack<>();
    private final Queue<Command> batchQueue = new LinkedList<>();

    private Boolean isTrsactionActive = false;
    private Boolean isBatchActive = false;

    public ExecutionEngine() {
        this.currentCollection = new ArrayCollection();
    }

    public void executeCommand(Command command) {
        if (command.getCommandType().equals(CommandType.INSERT_ONE)) {

        } else if (command.getCommandType().equals(CommandType.FIND_BY_ID)) {

        } else if (command.getCommandType().equals(CommandType.FIND_ALL)) {

        } else if (command.getCommandType().equals(CommandType.DELETE_ONE)) {

        } else if (command.getCommandType().equals(CommandType.IMPORT)) {

        } else if (command.getCommandType().equals(CommandType.FILTER)) {

        } else if (command.getCommandType().equals(CommandType.COUNT)) {

        } else if (command.getCommandType().equals(CommandType.SUM)) {

        } else if (command.getCommandType().equals(CommandType.AVERAGE)) {

        } else if (command.getCommandType().equals(CommandType.BEGIN_TRANSACTION)) {

        } else if (command.getCommandType().equals(CommandType.ROLLBACK)) {

        } else if (command.getCommandType().equals(CommandType.COMMIT)) {

        } else if (command.getCommandType().equals(CommandType.START)) {

        } else if (command.getCommandType().equals(CommandType.EXECUTE)) {

        }
    }
