package ru.nsu.khubanov;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StudentGradeBook student = new StudentGradeBook(true);
        // Добавляем оценки
        student.addGrade(1, "Математика", "Экзамен", 5);
        student.addGrade(1, "Физика", "Дифференцированный зачет", 4);
        student.addGrade(2, "Информатика", "Экзамен", 5);
        student.addGrade(2, "Химия", "Экзамен", 3);
        student.addGrade(3, "Физкультура", "Зачет", 5);

        // Установка оценки за ВКР
        student.setFinalThesisGrade(true);

        // Вывод результатов
        student.printGradeTable();
        System.out.printf("\nСредний балл: %.2f\n", student.calculateAverageGrade());
        System.out.println("Перевод на бюджет: " + student.canTransferToBudgetForm());
        System.out.println("Красный диплом: " + student.canReceiveRedDiploma());
        System.out.println("Повышенная стипендия в 3 семестре: " + student.isEligibleForIncreasedScholarship(3));
    }
}