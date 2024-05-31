package dev.mahfuj.result.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record StudentDto(
        Long studentId,

        @NotEmpty(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,

        @NotEmpty(message = "Father's name is required")
        @Size(min = 2, max = 50, message = "Father's name must be between 2 and 50 characters")
        String fatherName,

        @NotEmpty(message = "Mother's name is required")
        @Size(min = 2, max = 50, message = "Mother's name must be between 2 and 50 characters")
        String motherName,

        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,

        @NotEmpty(message = "Image URL is required")
        String imageUrl
) {
}
