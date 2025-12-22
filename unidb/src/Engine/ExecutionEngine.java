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
        if (command.getCommandType().equals(CommandType.INSERT_ONE)){
            // TODO: Parse JSON-like args
            System.out.println("Executing insertOne...");
        }
        // TODO: Handle others
    }
}
