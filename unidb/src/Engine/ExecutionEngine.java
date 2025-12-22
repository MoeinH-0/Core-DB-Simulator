package Engine;

import Storage.ArrayCollection;
import Storage.Collection;

public class ExecutionEngine {
    private Collection currentCollection;
    // TODO: Add Stack<Command> for Transactions
    // TODO: Add Queue<Command> for Batch

    public ExecutionEngine() {
        this.currentCollection = new ArrayCollection();
    }

    public void executeCommand(String commandType, String[] args) {
        if (commandType.equals("insertOne")) {
            // TODO: Parse JSON-like args
            System.out.println("Executing insertOne...");
        }
        // TODO: Handle others
    }
}
