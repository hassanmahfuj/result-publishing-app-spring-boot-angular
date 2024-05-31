package dev.mahfuj.result.repository;

import dev.mahfuj.result.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
