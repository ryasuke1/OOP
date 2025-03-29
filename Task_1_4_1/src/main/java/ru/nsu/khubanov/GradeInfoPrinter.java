package ru.nsu.khubanov;

import java.util.*;

public class GradeInfoPrinter {
    public static void collectUserInput(StudentGradeBook student, Scanner scanner) {
        System.out.print("Вы обучаетесь на платной форме? (да/нет): ");
        boolean isPaidForm = scanner.nextLine().equalsIgnoreCase("да");
        student.setPaidForm(isPaidForm);

        System.out.print("Вы получили 'отлично' за ВКР? (да/нет): ");
        boolean finalThesisExcellent = scanner.nextLine().equalsIgnoreCase("да");
        student.setFinalThesisGrade(finalThesisExcellent);

        System.out.println("Добавьте данные по предметам:");
        while (true) {
            System.out.print("Введите семестр (или 0 для завершения): ");
            int semester = scanner.nextInt();
            scanner.nextLine();

            if (semester == 0) break;

            System.out.print("Введите название предмета: ");
            String subjectName = scanner.nextLine();

            System.out.print("Введите вид контроля (Экзамен/Зачет/Дифференцированный зачет): ");
            String controlType = scanner.nextLine();

            System.out.print("Введите оценку (5, 4, 3): ");
            int grade = scanner.nextInt();
            scanner.nextLine();

            student.addGrade(semester, subjectName, controlType, grade);
        }
    }

    public static void printGradeTable(Map<Integer, List<SubjectGrade>> gradesBySemester) {
        System.out.println(String.format("%-10s %-20s %-15s %s", "Семестр", "Предмет", "Вид контроля", "Оценка"));
        System.out.println("-----------------------------------------------------------------");

        for (Map.Entry<Integer, List<SubjectGrade>> entry : gradesBySemester.entrySet()) {
            int semester = entry.getKey();
            for (SubjectGrade grade : entry.getValue()) {
                System.out.println(String.format("%-10d %s", semester, grade));
            }
        }
    }

    public static void printSummary(StudentGradeBook student) {
        System.out.printf("\nСредний балл: %.2f\n", student.calculateAverageGrade());

        if (student.isPaidForm()) {
            System.out.println("Перевод на бюджет: " + (student.canTransferToBudgetForm() ? "возможен" : "невозможен"));
        }

        System.out.println("Красный диплом: " + (student.canReceiveRedDiploma() ? "возможен" : "невозможен"));

        // Проверка возможности получения повышенной стипендии
        for (int semester : student.gradesBySemester.keySet()) {
            boolean eligible = student.isEligibleForIncreasedScholarship(semester);
            System.out.printf("Повышенная стипендия в семестре %d: %s\n", semester, eligible ? "возможна" : "невозможна");
        }
    }

}
