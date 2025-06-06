package ru.nsu.khubanov;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentGradeBook student = new StudentGradeBook(true);

        GradeInfoPrinter.collectUserInput(student, scanner);

        GradeInfoPrinter.printGradeTable(student.gradesBySemester);
        GradeInfoPrinter.printSummary(student);
    }
}
