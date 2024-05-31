package dev.mahfuj.result.controller;

import dev.mahfuj.result.dto.ResultDto;
import dev.mahfuj.result.dto.SuccessDetail;
import dev.mahfuj.result.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/result")
@CrossOrigin(origins = "http://localhost:4200")
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<SuccessDetail> saveStudent(@Valid @RequestBody ResultDto resultDto) {
        resultService.saveResult(resultDto);
        var successDetail = new SuccessDetail("Result successfully saved");
        return ResponseEntity.status(HttpStatus.OK).body(successDetail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDetail> getResult(@PathVariable Long id) {
        var successDetail = new SuccessDetail("Result found");
        successDetail.setData(resultService.getResult(id));
        return ResponseEntity.status(HttpStatus.OK).body(successDetail);
    }

    @GetMapping
    public ResponseEntity<SuccessDetail> getResults() {
        var successDetail = new SuccessDetail("Result found");
        successDetail.setData(resultService.getResults());
        return ResponseEntity.status(HttpStatus.OK).body(successDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDetail> deleteResult(@PathVariable Long id) {
        var successDetail = new SuccessDetail("success");
        resultService.deleteResult(id);
        return ResponseEntity.status(HttpStatus.OK).body(successDetail);
    }

    @GetMapping("/marksheet")
    public ResponseEntity<SuccessDetail> getResult(@RequestParam String examination,
                                                   @RequestParam int year,
                                                   @RequestParam Long rollNumber) {
        var successDetail = new SuccessDetail("Marksheet found");
        successDetail.setData(resultService.getMarksheet(examination, year, rollNumber));
        return ResponseEntity.status(HttpStatus.OK).body(successDetail);
    }

}
