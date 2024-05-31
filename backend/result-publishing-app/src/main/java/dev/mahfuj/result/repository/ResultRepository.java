package dev.mahfuj.result.repository;

import dev.mahfuj.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {

    Optional<Result> findResultByExaminationAndYearAndRollNumber(String examination, Integer year, Long rollNumber);

}
