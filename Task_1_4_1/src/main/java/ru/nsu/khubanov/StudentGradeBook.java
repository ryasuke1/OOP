package ru.nsu.khubanov;

import java.util.*;

public class StudentGradeBook {
    protected Map<Integer, List<SubjectGrade>> gradesBySemester; // Оценки по семестрам
    private boolean isPaidForm; // Форма оплаты (платная или бюджетная)
    private boolean finalThesisExcellent; // Защита ВКР на "отлично"

    public StudentGradeBook(boolean isPaidForm) {
        this.gradesBySemester = new HashMap<>();
        this.isPaidForm = isPaidForm;
        this.finalThesisExcellent = false;
    }

    public void setPaidForm(boolean isPaidForm) {
        this.isPaidForm = isPaidForm;
    }

    public boolean isPaidForm() {
        return isPaidForm;
    }

    public void addGrade(int semester, String subjectName, String controlType, int grade) {
        gradesBySemester.putIfAbsent(semester, new ArrayList<>());
        gradesBySemester.get(semester).add(new SubjectGrade(subjectName, controlType, grade));
    }

    public void setFinalThesisGrade(boolean isExcellent) {
        this.finalThesisExcellent = isExcellent;
    }

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

    public boolean canTransferToBudgetForm() {
        if (!isPaidForm) return false;

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

    public boolean canReceiveRedDiploma() {
        int totalCount = 0, excellentCount = 0;

        for (List<SubjectGrade> grades : gradesBySemester.values()) {
            for (SubjectGrade grade : grades) {
                if (grade.isSatisfactory()) return false;
                totalCount++;
                if (grade.isExcellent()) excellentCount++;
            }
        }
        double excellentPercentage = (double) excellentCount / totalCount;
        return excellentPercentage >= 0.75 && finalThesisExcellent;
    }

    public boolean isEligibleForIncreasedScholarship(int currentSemester) {
        if (!gradesBySemester.containsKey(currentSemester)) return false;

        for (SubjectGrade grade : gradesBySemester.get(currentSemester)) {
            if (!grade.isExcellent()) return false;
        }
        return true;
    }
}
