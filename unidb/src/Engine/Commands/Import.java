package Engine.Commands;

import Engine.Command;
import Models.Student;
import Storage.Collection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Import {
    public void execute(Command command, Collection collection) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(command.getArguments().getFirst()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        String line;

        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] parts = line.split(",");

            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            double gpa = Double.parseDouble(parts[2].trim());

            Student student = new Student(id, name, gpa);
            collection.insertOne(student);
        }

        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

