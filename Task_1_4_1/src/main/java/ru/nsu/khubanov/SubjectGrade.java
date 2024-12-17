package ru.nsu.khubanov;

class SubjectGrade {
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


    @Override
    public String toString() {
        return String.format("%-20s %-15s %d", subjectName, controlType, grade);
    }
}