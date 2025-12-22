package Parser;

import Engine.ExecutionEngine;

public class QueryParser {
    public static void parseAndExecute(String input, ExecutionEngine engine) {

        String[] tokens = input.split("\\.");
        if (tokens.length == 0) return;

        String cmd = tokens[2];
        String [] args = new String [4];

        System.arraycopy(tokens, 0, args, 0, 3);

        int index = tokens[2].indexOf("(");

        args[3] = tokens[2].substring(index + 1, tokens[2].length() - 2);
        args[2] = args[2].substring(0, index - 1);

        engine.executeCommand(cmd, args);
    }
}
