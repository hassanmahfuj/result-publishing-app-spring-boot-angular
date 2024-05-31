package dev.mahfuj.result.controller;

import dev.mahfuj.result.dto.SuccessDetail;
import dev.mahfuj.result.exception.InternalServerErrorException;
import dev.mahfuj.result.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/file-upload")
    public ResponseEntity<SuccessDetail> fileUpload(@RequestParam("file") MultipartFile file) {
        String url = fileService.saveFile(file);
        var successDetail = new SuccessDetail("File successfully uploaded");
        successDetail.setData(url);
        return ResponseEntity.ok().body(successDetail);
    }

    @GetMapping(value = "/uploads/{fileName}", produces = MediaType.ALL_VALUE)
    public void fileDownload(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        try (InputStream is = fileService.getFile(fileName)) {
            response.setContentType(MediaType.ALL_VALUE);
            StreamUtils.copy(is, response.getOutputStream());
        } catch (IOException e) {
            throw new InternalServerErrorException("Something wrong with the server");
        }
    }
}
