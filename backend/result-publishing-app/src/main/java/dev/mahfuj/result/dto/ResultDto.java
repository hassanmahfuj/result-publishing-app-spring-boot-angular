package dev.mahfuj.result.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ResultDto(
        Long studentId,
        Long resultId,

        @NotNull(message = "Roll Number cannot be null")
        Long rollNumber,

        @NotNull(message = "Year cannot be null")
        Integer year,

        @NotEmpty(message = "Group Name is required")
        @Size(min = 2, max = 50, message = "Group name must be between 2 and 50 characters")
        String groupName,

        @NotEmpty(message = "Examination is required")
        @Size(min = 2, max = 50, message = "Examination must be between 2 and 50 characters")
        String examination,

        @NotNull(message = "Subjects cannot be null")
        @NotEmpty(message = "Subjects cannot be empty")
        List<SubjectDto> subjects
) {
}
