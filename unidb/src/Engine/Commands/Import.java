package Engine.Commands;

import Engine.Command;
import Models.Student;
import Storage.Collection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Import {
    public void execute(Command command, Collection collection) {
        String filename = command.getArguments().getFirst();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (firstLine) {
                    firstLine = false;
                    String[] headerParts = line.split(",");
                    if (headerParts.length >= 1 && !isInteger(headerParts[0].trim())) {
                        continue;
                    }
                }

                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double gpa = Double.parseDouble(parts[2].trim());

                    Student student = new Student(id, name, gpa);
                    collection.insertOne(student);
                } catch (NumberFormatException _) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
