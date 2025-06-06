package ru.nsu.khubanov;

public class SubjectGrade {
    private String subjectName;    // Название предмета
    private String controlType;    // Вид контроля (экзамен, зачет и т.д.)
    private int grade;             // Оценка

    public SubjectGrade(String subjectName, String controlType, int grade) {
        this.subjectName = subjectName;
        this.controlType = controlType;
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public String getControlType() {
        return controlType;
    }

    public boolean isExamOrDiffZachet() {
        return controlType.equalsIgnoreCase("Экзамен") || controlType.equalsIgnoreCase("Дифференцированный зачет");
    }

    public boolean isExcellent() {
        return grade == 5;
    }

    public boolean isSatisfactory() {
        return grade == 3;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-15s %d", subjectName, controlType, grade);
    }
}
