package Parser;

import Engine.Command;
import Engine.Enum.CollectionType;
import Engine.Enum.CommandType;
import Engine.ExecutionEngine;

import java.util.ArrayList;

public class QueryParser {
    public static void parseAndExecute(String input, ExecutionEngine engine) {

        String[] tokens = input.split("\\.(?!\\d)");
        if (tokens.length == 0) return;

        CommandType commandType;
        ArrayList<String> args = new ArrayList<>();
        CollectionType collectionType = null;
        String command = tokens[tokens.length - 1];

        int index;

        if (tokens.length > 2)
            collectionType = CollectionType.STUDENTS;

        switch (command.split("\\(")[0]) {
            case "insertOne":
                commandType = CommandType.INSERT_ONE;

                index = command.indexOf("id");
                args.add(command.substring(index + 4, command.indexOf(",")));

                index = command.indexOf("name");
                args.add(command.substring(index + 7, command.indexOf(",", index) - 1));

                index = command.indexOf("gpa");
                args.add(command.substring(index + 5, command.indexOf("}")));
                break;

            case "findByID":
                commandType = CommandType.FIND_BY_ID;

                args.add(command.substring(command.indexOf("(") + 1, command.length() - 1));
                break;

            case "findAll":
                commandType = CommandType.FIND_ALL;
                break;

            case "deleteOne":
                commandType = CommandType.DELETE_ONE;

                args.add(command.substring(command.indexOf("id") + 4, command.length() - 2));
                break;

            case "import":
                commandType = CommandType.IMPORT;

                args.add(command.substring(command.indexOf("\"") + 1, command.length() - 2) + ".csv");
                break;

            case "filter":
                commandType = CommandType.FILTER;

                index = command.indexOf("\"");
                args.add(command.substring(index + 1, command.indexOf(",") - 1));

                index = command.indexOf(",");
                args.add(command.substring(index + 3, command.length() - 2));
                break;

            case "count":
                commandType = CommandType.COUNT;
                break;

            case "sum":
                commandType = CommandType.SUM;

                args.add(command.substring(command.indexOf("(") + 2, command.length() - 2));
                break;

            case "average":
                commandType = CommandType.AVERAGE;

                args.add(command.substring(command.indexOf("(") + 2, command.length() - 2));
                break;

            case "beginTransaction":
                commandType = CommandType.BEGIN_TRANSACTION;
                break;

            case "rollback":
                commandType = CommandType.ROLLBACK;
                break;

            case "commit":
                commandType = CommandType.COMMIT;
                break;

            case "start":
                commandType = CommandType.START;
                break;

            case "execute":
                commandType = CommandType.EXECUTE;
                break;

            default:
                System.out.println("Unknown command.");
                return;
        }


        engine.executeCommand(new Command(commandType, args, collectionType));
    }
}
