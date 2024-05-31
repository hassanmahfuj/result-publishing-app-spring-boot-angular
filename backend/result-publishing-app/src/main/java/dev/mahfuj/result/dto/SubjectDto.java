package dev.mahfuj.result.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SubjectDto(
        Long subjectId,
        Long resultId,

        @NotNull(message = "Code cannot be null")
        Integer code,

        @NotEmpty(message = "Subject Name is required")
        @Size(min = 2, max = 50, message = "Mother's name must be between 2 and 50 characters")
        String name,

        String grade,

        @NotNull(message = "GPA cannot be null")
        Double gpa
) {
}
