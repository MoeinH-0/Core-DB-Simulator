package Presentation;

import Models.Student;

import java.util.List;


public class ConsoleOutput {

    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String GRAY = "\u001B[90m";
    private static final String YELLOW = "\u001B[33m";

    public static void printStudents(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println(YELLOW + "No records found." + RESET);
            return;
        }

        System.out.println(BLUE + String.format("%-5s %-15s %5s", "ID", "Name", "GPA") + RESET);
        System.out.println(GRAY + "──────────────────────────" + RESET);

        for (Student student : students) {
            System.out.printf(
                    "%-5d %-15s %s%5.2f%s%n",
                    student.getId(),
                    student.getName(),
                    GREEN,
                    student.getGpa(),
                    RESET
            );
        }
    }

    public static void printCount(int count) {
        System.out.println(BLUE + "Total records: " + RESET + count);
    }

    public static void printSum(double sum, String fieldName) {
        System.out.println(GREEN + "Sum of " + fieldName + ": " + String.format("%.2f", sum) + RESET);
    }

    public static void printAverage(double average, String fieldName) {
        System.out.println(GREEN + "Average " + fieldName + ": " + String.format("%.2f", average) + RESET);
    }
}
