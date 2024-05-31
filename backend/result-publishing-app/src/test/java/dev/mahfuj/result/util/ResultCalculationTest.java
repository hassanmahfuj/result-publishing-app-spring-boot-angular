package dev.mahfuj.result.util;

import dev.mahfuj.result.dto.SubjectDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ResultCalculationTest {

    private ResultCalculation resultCalculation;

    @Mock
    private SubjectDto subjectDto1, subjectDto2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resultCalculation = new ResultCalculation();
    }

    @Test
    void testGradePointToLetterGrade() {
        assertEquals("F", resultCalculation.gradePointToLetterGrade(0.99));
        assertEquals("D", resultCalculation.gradePointToLetterGrade(1.99));
        assertEquals("C", resultCalculation.gradePointToLetterGrade(2.99));
        assertEquals("B", resultCalculation.gradePointToLetterGrade(3.49));
        assertEquals("A-", resultCalculation.gradePointToLetterGrade(3.99));
        assertEquals("A", resultCalculation.gradePointToLetterGrade(4.99));
        assertEquals("A+", resultCalculation.gradePointToLetterGrade(5.00));
    }

    @Test
    void testCalculateGpa() {
        List<SubjectDto> subjects = new ArrayList<>();
        when(subjectDto1.gpa()).thenReturn(3.5);
        when(subjectDto2.gpa()).thenReturn(4.0);
        subjects.add(subjectDto1);
        subjects.add(subjectDto2);

        assertEquals(3.75, resultCalculation.calculateGpa(subjects));
    }

    @Test
    void testCalculateGpaWithNullSubjects() {
        assertEquals(0.0, resultCalculation.calculateGpa(null));
    }

    @Test
    void testCalculateGpaWithEmptySubjects() {
        assertEquals(0.0, resultCalculation.calculateGpa(new ArrayList<>()));
    }

    @Test
    void testCalculateGpaWithAnySubjectBelowGrade1() {
        List<SubjectDto> subjects = new ArrayList<>();
        when(subjectDto1.gpa()).thenReturn(0.5);
        when(subjectDto2.gpa()).thenReturn(3.0);
        subjects.add(subjectDto1);
        subjects.add(subjectDto2);

        assertEquals(0.0, resultCalculation.calculateGpa(subjects));
    }
}