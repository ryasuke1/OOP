package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class StudentGradeBookTest {

    @Test
    public void testCalculateAverageGrade() {
        StudentGradeBook student = new StudentGradeBook(true);

        student.addGrade(1, "Математика", "Экзамен", 5);
        student.addGrade(1, "Физика", "Экзамен", 4);
        student.addGrade(2, "Химия", "Дифференцированный зачет", 5);

        assertEquals(4.67, student.calculateAverageGrade(), 0.01);
    }

    @Test
    public void testCanTransferToBudgetForm() {
        StudentGradeBook student = new StudentGradeBook(true);

        student.addGrade(1, "Математика", "Экзамен", 5);
        student.addGrade(1, "Физика", "Экзамен", 4);
        student.addGrade(2, "Химия", "Экзамен", 5);

        assertTrue(student.canTransferToBudgetForm());

        student.addGrade(3, "Биология", "Экзамен", 3);

        assertFalse(student.canTransferToBudgetForm());
    }

    @Test
    public void testCannotTransferIfAlreadyOnBudget() {
        StudentGradeBook student = new StudentGradeBook(false); // Уже на бюджете

        student.addGrade(1, "Математика", "Экзамен", 5);
        student.addGrade(1, "Физика", "Экзамен", 4);

        assertFalse(student.canTransferToBudgetForm());
    }

    @Test
    public void testRedDiplomaRequiresThesisGrade() {
        StudentGradeBook student = new StudentGradeBook(true);

        student.addGrade(1, "Математика", "Экзамен", 5);
        student.addGrade(1, "Физика", "Экзамен", 5);
        student.addGrade(2, "Химия", "Экзамен", 5);

        student.setFinalThesisGrade(false); // ВКР не на "отлично"

        assertFalse(student.canReceiveRedDiploma());

        student.setFinalThesisGrade(true); // ВКР на "отлично"

        assertTrue(student.canReceiveRedDiploma());
    }

    @Test
    public void testIsEligibleForIncreasedScholarship() {
        StudentGradeBook student = new StudentGradeBook(true);

        student.addGrade(1, "Математика", "Экзамен", 5);
        student.addGrade(1, "Физика", "Экзамен", 5);

        assertTrue(student.isEligibleForIncreasedScholarship(1));

        student.addGrade(1, "Химия", "Экзамен", 4);

        assertFalse(student.isEligibleForIncreasedScholarship(1));
    }

    @Test
    public void testNoGradesShouldReturnZeroAverage() {
        StudentGradeBook student = new StudentGradeBook(true);
        assertEquals(0.0, student.calculateAverageGrade());
    }

    @Test
    public void testScholarshipWithoutGrades() {
        StudentGradeBook student = new StudentGradeBook(true);
        assertFalse(student.isEligibleForIncreasedScholarship(1));
    }

    @Test
    public void testTransferWithoutGrades() {
        StudentGradeBook student = new StudentGradeBook(true);
        assertFalse(student.canTransferToBudgetForm());
    }

    @Test
    public void testSummaryOutput() {
        StudentGradeBook student = new StudentGradeBook(true);

        student.addGrade(1, "Математика", "Экзамен", 5);
        student.addGrade(1, "Физика", "Экзамен", 5);
        student.addGrade(2, "Химия", "Экзамен", 4);
        student.setFinalThesisGrade(true);

        assertFalse(student.isEligibleForIncreasedScholarship(2));
    }
}
