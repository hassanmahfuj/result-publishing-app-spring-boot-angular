package dev.mahfuj.result.util;

import dev.mahfuj.result.dto.SubjectDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultCalculation {

    public String gradePointToLetterGrade(Double gradePoint) {
        if (gradePoint == null) return "";
        if (gradePoint < 1) {
            return "F";
        } else if (gradePoint < 2) {
            return "D";
        } else if (gradePoint < 3) {
            return "C";
        } else if (gradePoint < 3.5) {
            return "B";
        } else if (gradePoint < 4) {
            return "A-";
        } else if (gradePoint < 5) {
            return "A";
        } else {
            return "A+";
        }
    }

    public Double calculateGpa(List<SubjectDto> subjects) {
        if (subjects == null) return 0.0;
        if (subjects.stream().anyMatch(subject -> subject.gpa() < 1)) return 0.0;
        double totalGpa = subjects.stream().mapToDouble(SubjectDto::gpa).sum();
        return subjects.size() > 0 ? totalGpa / subjects.size() : 0.0;
    }

}
