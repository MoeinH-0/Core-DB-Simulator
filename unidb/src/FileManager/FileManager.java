package FileManager;

import Models.Student;
import Storage.Collection;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final String filename;

    public FileManager(String filename) {
        this.filename = filename;
    }

    public void save(Collection collection) {
        List<Student> all = collection.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < all.size(); i++) {
            Student s = all.get(i);
            sb.append("{\"id\":").append(s.getId())
                    .append(",\"name\":\"").append(escapeJson(s.getName())).append('\"')
                    .append(",\"gpa\":").append(s.getGpa()).append("}");
            if (i < all.size() - 1) sb.append(',');
        }
        sb.append("]");

        try (FileWriter fw = new FileWriter(filename, false)) {
            fw.write(sb.toString());
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    public void load(Collection collection) {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) return;

        try {
            byte[] bytes = Files.readAllBytes(path);
            String content = new String(bytes, StandardCharsets.UTF_8).trim();
            if (content.isEmpty()) return;

            List<String> objects = extractJsonObjectsArray(content);
            for (String obj : objects) {
                int id = parseIntField(obj);
                String name = parseStringField(obj);
                double gpa = parseDoubleField(obj);

                try {
                    collection.insertOne(new Student(id, name, gpa));
                } catch (Exception _) {
                }
            }

        } catch (IOException e) {
            System.out.println("Failed to load data: " + e.getMessage());
        }
    }

    private List<String> extractJsonObjectsArray(String arrayContent) {
        List<String> objs = new ArrayList<>();
        String s = arrayContent.trim();
        if (s.startsWith("[")) s = s.substring(1);
        if (s.endsWith("]")) s = s.substring(0, s.length() - 1);

        int depth = 0;
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') {
                depth++;
                cur.append(c);
            } else if (c == '}') {
                depth--;
                cur.append(c);
                if (depth == 0) {
                    objs.add(cur.toString().trim());
                    cur.setLength(0);
                }
            } else {
                if (depth > 0) cur.append(c);
            }
        }
        return objs;
    }

    private int parseIntField(String obj) {
        String key = "\"" + "id" + "\"" + ":";
        int idx = obj.indexOf(key);
        if (idx == -1) return 0;
        int start = idx + key.length();
        int end = start;
        while (end < obj.length() && (Character.isDigit(obj.charAt(end)) || obj.charAt(end) == '-')) end++;
        return Integer.parseInt(obj.substring(start, end));
    }

    private double parseDoubleField(String obj) {
        String key = "\"" + "gpa" + "\"" + ":";
        int idx = obj.indexOf(key);
        if (idx == -1) return 0.0;
        int start = idx + key.length();
        int end = start;
        while (end < obj.length() && (Character.isDigit(obj.charAt(end)) || obj.charAt(end) == '.' || obj.charAt(end) == '-')) end++;
        return Double.parseDouble(obj.substring(start, end));
    }

    private String parseStringField(String obj) {
        String key = "\"" + "name" + "\"" + ":\"";
        int idx = obj.indexOf(key);
        if (idx == -1) return "";
        int start = idx + key.length();
        int end = obj.indexOf('"', start);
        if (end == -1) end = obj.length();
        return unescapeJson(obj.substring(start, end));
    }

    private String escapeJson(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private String unescapeJson(String s) {
        return s.replace("\\\"", "\"").replace("\\\\", "\\");
    }
}

