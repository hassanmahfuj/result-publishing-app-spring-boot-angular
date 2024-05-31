package dev.mahfuj.result.mapper;

import dev.mahfuj.result.dto.StudentDto;
import dev.mahfuj.result.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentDto(
                student.getStudentId(),
                student.getName(),
                student.getFatherName(),
                student.getMotherName(),
                student.getDateOfBirth(),
                student.getImageUrl()
        );
    }

    public Student toEntity(StudentDto studentDto) {
        if (studentDto == null) {
            return null;
        }
        var student = new Student();
        student.setStudentId(studentDto.studentId());
        student.setName(studentDto.name());
        student.setFatherName(studentDto.fatherName());
        student.setMotherName(studentDto.motherName());
        student.setDateOfBirth(studentDto.dateOfBirth());
        student.setImageUrl(studentDto.imageUrl());
        return student;
    }

}
