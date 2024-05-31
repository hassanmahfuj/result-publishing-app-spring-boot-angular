package dev.mahfuj.result.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MarksheetDto {

    private String examination;
    private Integer year;
    private Long rollNumber;
    private String groupName;
    private Double gpa;
    private String grade;
    private String result;
    private String name;
    private String fatherName;
    private String motherName;
    private LocalDate datOfBirth;
    private List<SubjectDto> subjects;

}
