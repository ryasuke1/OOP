package ru.nsu.khubanov;

import java.util.*;

public class StudentGradeBook {
    private Map<Integer, List<SubjectGrade>> gradesBySemester; // Оценки по семестрам
    private boolean isPaidForm; // Форма оплаты (платное или бюджетное)

    public StudentGradeBook(boolean isPaidForm) {
        this.gradesBySemester = new HashMap<>();
        this.isPaidForm = isPaidForm;
    }

    // Добавление оценки
    public void addGrade(int semester, String subjectName, String controlType, int grade) {
        gradesBySemester.putIfAbsent(semester, new ArrayList<>());
        gradesBySemester.get(semester).add(new SubjectGrade(subjectName, controlType, grade));
    }


    // 1. Вычисление текущего среднего балла
    public double calculateAverageGrade() {
        int sum = 0, count = 0;
        for (List<SubjectGrade> grades : gradesBySemester.values()) {
            for (SubjectGrade grade : grades) {
                sum += grade.getGrade();
                count++;
            }
        }
        return count == 0 ? 0 : (double) sum / count;
    }

    // 2. Возможность перевода на бюджетную форму
    public boolean canTransferToBudgetForm() {
        int semesters = gradesBySemester.size();
        if (semesters < 2) return false;

        List<SubjectGrade> lastSemester = gradesBySemester.get(semesters);
        List<SubjectGrade> prevSemester = gradesBySemester.get(semesters - 1);

        return checkSemesterGrades(lastSemester) && checkSemesterGrades(prevSemester);
    }

    private boolean checkSemesterGrades(List<SubjectGrade> grades) {
        for (SubjectGrade grade : grades) {
            if (grade.isExamOrDiffZachet() && grade.isSatisfactory()) return false;
        }
        return true;
    }

    // 3. Возможность получения красного диплома
    public boolean canReceiveRedDiploma() {
        int totalCount = 0, excellentCount = 0;

        for (List<SubjectGrade> grades : gradesBySemester.values()) {
            for (SubjectGrade grade : grades) {
                if (grade.isSatisfactory()) return false; // Проверка на "удовлетворительно"
                totalCount++;
                if (grade.isExcellent()) excellentCount++;
            }
        }
        double excellentPercentage = (double) excellentCount / totalCount;
        return excellentPercentage >= 0.75 && finalThesisExcellent;
    }

    // 4. Возможность получения повышенной стипендии
    public boolean isEligibleForIncreasedScholarship(int currentSemester) {
        if (!gradesBySemester.containsKey(currentSemester)) return false;

        for (SubjectGrade grade : gradesBySemester.get(currentSemester)) {
            if (!grade.isExcellent()) return false;
        }
        return true;
    }

    // Вывод всей таблицы оценок
    public void printGradeTable() {
        System.out.println(String.format("%-10s %-20s %-15s %s", "Семестр", "Предмет", "Вид контроля", "Оценка"));
        System.out.println("-----------------------------------------------------------------");

        for (Map.Entry<Integer, List<SubjectGrade>> entry : gradesBySemester.entrySet()) {
            int semester = entry.getKey();
            for (SubjectGrade grade : entry.getValue()) {
                System.out.println(String.format("%-10d %s", semester, grade));
            }
        }
    }
}
