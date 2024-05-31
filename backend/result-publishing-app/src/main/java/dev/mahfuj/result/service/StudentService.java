package dev.mahfuj.result.service;

import dev.mahfuj.result.dto.StudentDto;
import dev.mahfuj.result.entity.Student;
import dev.mahfuj.result.exception.NotFoundException;
import dev.mahfuj.result.mapper.StudentMapper;
import dev.mahfuj.result.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final FileService fileService;

    public void saveStudent(StudentDto studentDto) {
        studentRepository.save(studentMapper.toEntity(studentDto));
    }

    public void updateStudent(StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.studentId())
                .orElseThrow(() -> new NotFoundException("Student not found to update"));
        student.setName(studentDto.name());
        student.setFatherName(studentDto.fatherName());
        student.setMotherName(studentDto.motherName());
        student.setDateOfBirth(studentDto.dateOfBirth());
        student.setImageUrl(studentDto.imageUrl());
        studentRepository.save(student);
    }

    public List<StudentDto> getStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    public StudentDto getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return studentMapper.toDto(student.orElseThrow(() -> new NotFoundException("Student not found")));
    }

    public void deleteStudent(Long id) {
        studentRepository.findById(id).ifPresent(student -> {
            fileService.deleteFile(student.getImageUrl());
        });
        studentRepository.deleteById(id);
    }

}
