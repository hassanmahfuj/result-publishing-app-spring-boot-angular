package dev.mahfuj.result.controller;

import dev.mahfuj.result.dto.StudentDto;
import dev.mahfuj.result.dto.SuccessDetail;
import dev.mahfuj.result.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<SuccessDetail> saveStudent(@Valid @RequestBody StudentDto studentDto) {
        studentService.saveStudent(studentDto);
        var successDetail = new SuccessDetail("Student successfully saved");
        return ResponseEntity.status(HttpStatus.OK).body(successDetail);
    }

    @PutMapping
    public ResponseEntity<SuccessDetail> updateStudent(@Valid @RequestBody StudentDto studentDto) {
        studentService.updateStudent(studentDto);
        var successDetail = new SuccessDetail("Student successfully updated");
        return ResponseEntity.status(HttpStatus.OK).body(successDetail);
    }

    @GetMapping
    public ResponseEntity<SuccessDetail> getStudents() {
        var successDetail = new SuccessDetail("success");
        successDetail.setData(studentService.getStudents());
        return ResponseEntity.status(HttpStatus.OK).body(successDetail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDetail> getStudent(@PathVariable Long id) {
        var successDetail = new SuccessDetail("success");
        successDetail.setData(studentService.getStudent(id));
        return ResponseEntity.status(HttpStatus.OK).body(successDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDetail> deleteStudent(@PathVariable Long id) {
        var successDetail = new SuccessDetail("Student successfully deleted");
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK).body(successDetail);
    }

}
