package dev.mahfuj.result.mapper;

import dev.mahfuj.result.dto.SubjectDto;
import dev.mahfuj.result.entity.Result;
import dev.mahfuj.result.entity.Subject;
import dev.mahfuj.result.util.ResultCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SubjectMapper {

    private final ResultCalculation resultCalculation;

    public SubjectDto toDto(Subject subject) {
        if (subject == null) {
            return null;
        }
        return new SubjectDto(
                subject.getSubjectId(),
                subject.getResult().getResultId(),
                subject.getCode(),
                subject.getName(),
                resultCalculation.gradePointToLetterGrade(subject.getGpa()),
                subject.getGpa()
        );
    }

    public Subject toEntity(SubjectDto subjectDto, Result result) {
        if (subjectDto == null) {
            return null;
        }
        var subject = new Subject();
        subject.setResult(result);

        subject.setSubjectId(subjectDto.subjectId());
        subject.setCode(subjectDto.code());
        subject.setName(subjectDto.name());
        subject.setGpa(subjectDto.gpa());
        return subject;
    }

}
