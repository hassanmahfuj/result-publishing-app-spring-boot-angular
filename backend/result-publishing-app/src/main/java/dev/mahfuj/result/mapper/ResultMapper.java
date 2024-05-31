package dev.mahfuj.result.mapper;

import dev.mahfuj.result.dto.ResultDto;
import dev.mahfuj.result.entity.Result;
import dev.mahfuj.result.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ResultMapper {

    private final SubjectMapper subjectMapper;

    public ResultDto toDto(Result result) {
        if (result == null) {
            return null;
        }
        return new ResultDto(
                result.getStudent().getStudentId(),
                result.getResultId(),
                result.getRollNumber(),
                result.getYear(),
                result.getGroupName(),
                result.getExamination(),
                result.getSubjects().stream()
                        .map(subjectMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    public Result toEntity(ResultDto resultDto) {
        if (resultDto == null) {
            return null;
        }
        var result = new Result();
        var student = new Student();
        student.setStudentId(resultDto.studentId());

        result.setStudent(student);
        result.setResultId(resultDto.resultId());
        result.setRollNumber(resultDto.rollNumber());
        result.setYear(resultDto.year());
        result.setGroupName(resultDto.groupName());
        result.setExamination(resultDto.examination());
        result.setSubjects(resultDto.subjects().stream()
                .map(subjectDto -> subjectMapper.toEntity(subjectDto, result))
                .collect(Collectors.toList()));

        return result;
    }

}
