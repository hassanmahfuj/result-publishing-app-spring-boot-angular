package dev.mahfuj.result.service;

import dev.mahfuj.result.dto.MarksheetDto;
import dev.mahfuj.result.dto.ResultDto;
import dev.mahfuj.result.entity.Result;
import dev.mahfuj.result.exception.NotFoundException;
import dev.mahfuj.result.mapper.ResultMapper;
import dev.mahfuj.result.mapper.SubjectMapper;
import dev.mahfuj.result.repository.ResultRepository;
import dev.mahfuj.result.util.ResultCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final ResultMapper resultMapper;
    private final SubjectMapper subjectMapper;
    private final ResultCalculation resultCalculation;

    public void saveResult(ResultDto resultDto) {
        var result = resultMapper.toEntity(resultDto);
        resultRepository.save(result);
    }

    public List<ResultDto> getResults() {
        return resultRepository.findAll().stream().map(resultMapper::toDto).collect(Collectors.toList());
    }

    public ResultDto getResult(Long id) {
        Optional<Result> student = resultRepository.findById(id);
        return resultMapper.toDto(student.orElseThrow(() -> new NotFoundException("Result not found")));
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    public MarksheetDto getMarksheet(String examination, int year, Long rollNumber) {
        Result result = resultRepository.findResultByExaminationAndYearAndRollNumber(examination, year, rollNumber)
                .orElseThrow(() -> new NotFoundException("Marksheet not found"));

        var marksheetDto = new MarksheetDto();
        marksheetDto.setExamination(result.getExamination());
        marksheetDto.setYear(result.getYear());
        marksheetDto.setRollNumber(result.getRollNumber());
        marksheetDto.setGroupName(result.getGroupName());
        marksheetDto.setName(result.getStudent().getName());
        marksheetDto.setFatherName(result.getStudent().getFatherName());
        marksheetDto.setMotherName(result.getStudent().getMotherName());
        marksheetDto.setDatOfBirth(result.getStudent().getDateOfBirth());

        marksheetDto.setSubjects(result.getSubjects().stream().map(subjectMapper::toDto).collect(Collectors.toList()));
        marksheetDto.setGpa(resultCalculation.calculateGpa(marksheetDto.getSubjects()));
        marksheetDto.setGrade(resultCalculation.gradePointToLetterGrade(marksheetDto.getGpa()));
        marksheetDto.setResult(marksheetDto.getGrade().equals("F") ? "FAILED" : "PASSED");
        return marksheetDto;
    }
}
