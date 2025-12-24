package Engine;

import Engine.Commands.*;
import Engine.Enum.CommandType;
import Models.Student;
import Storage.ArrayCollection;
import Storage.Collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ExecutionEngine {
    private final Collection currentCollection;
    private final Stack<Command> transactionStack = new Stack<>();
    private final Queue<Command> batchQueue = new LinkedList<>();

    private Boolean isTransactionActive = false;
    private Boolean isBatchActive = false;

    public ExecutionEngine() {
        this.currentCollection = new ArrayCollection();
    }

    public void executeCommand(Command command) {
        if (isBatchActive) {
            batchQueue.add(command);
            return;
        }

        if (command.getCommandType().equals(CommandType.INSERT_ONE)) {
            InsertOne insertOne = new InsertOne();
            insertOne.execute(command, currentCollection);

            if (isTransactionActive) {
                ArrayList<String> args = new ArrayList<>();
                args.add(command.getArguments().getFirst());
                transactionStack.push(new Command
                        (CommandType.DELETE_ONE, args, command.getCollectionType()));
            }

        } else if (command.getCommandType().equals(CommandType.FIND_BY_ID)) {
            FindById findById = new FindById();
            findById.execute(command, currentCollection);


        } else if (command.getCommandType().equals(CommandType.FIND_ALL)) {
            FindAll findAll = new FindAll();
            findAll.execute(command, currentCollection);


        } else if (command.getCommandType().equals(CommandType.DELETE_ONE)) {
            DeleteOne deleteOne = new DeleteOne();
            deleteOne.execute(command, currentCollection);

            if (isTransactionActive) {
                Student student =
                        currentCollection.findByID
                                (Integer.parseInt(command.getArguments().getFirst()));
                ArrayList<String> args = new ArrayList<>();
                args.add(String.valueOf(student.getId()));
                args.add(student.getName());
                args.add(String.valueOf(student.getGpa()));

                transactionStack.push(new Command
                        (CommandType.INSERT_ONE, args, command.getCollectionType()));
            }


        } else if (command.getCommandType().equals(CommandType.IMPORT)) {
            Import importCmd = new Import();
            importCmd.execute(command, currentCollection);


        } else if (command.getCommandType().equals(CommandType.FILTER)) {
            Filter filter = new Filter();
            filter.execute(command, currentCollection);


        } else if (command.getCommandType().equals(CommandType.COUNT)) {
            Count count = new Count();
            count.execute(currentCollection);


        } else if (command.getCommandType().equals(CommandType.SUM)) {
            Sum sum = new Sum();
            sum.execute(currentCollection);


        } else if (command.getCommandType().equals(CommandType.AVERAGE)) {
            Average average = new Average();
            average.execute(currentCollection);


        } else if (command.getCommandType().equals(CommandType.BEGIN_TRANSACTION)) {
            isTransactionActive = true;


        } else if (command.getCommandType().equals(CommandType.ROLLBACK)) {
            while (!transactionStack.empty())
                executeCommand(transactionStack.pop());

            isTransactionActive = false;


        } else if (command.getCommandType().equals(CommandType.COMMIT)) {
            transactionStack.clear();
            isTransactionActive = false;


        } else if (command.getCommandType().equals(CommandType.START)) {
            isBatchActive = true;


        } else if (command.getCommandType().equals(CommandType.EXECUTE)) {
            while (!transactionStack.empty())
                executeCommand(batchQueue.poll());

            isBatchActive = false;
        }
    }
}
