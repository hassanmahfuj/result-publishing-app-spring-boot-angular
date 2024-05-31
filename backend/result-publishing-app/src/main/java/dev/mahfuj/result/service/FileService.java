package dev.mahfuj.result.service;

import dev.mahfuj.result.exception.InternalServerErrorException;
import dev.mahfuj.result.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {

    public static final String UPLOAD_PATH = "uploads";

    public String saveFile(MultipartFile file) {
        createUploadDirectoryIfNotExists();
        InputStream inputStream;
        try {
            String fileName = file.getOriginalFilename() != null ? file.getOriginalFilename() : "student-image.jpg";
            String name = fileName.substring(0, fileName.lastIndexOf("."));
            String extension = fileName.substring(fileName.lastIndexOf("."));
            String filePath = UPLOAD_PATH.concat("/").concat(fileName);
            inputStream = file.getInputStream();
            int count = 1;
            while (true) {
                try {
                    Files.copy(inputStream, Paths.get(filePath));
                    break;
                } catch (FileAlreadyExistsException e) {
                    filePath = UPLOAD_PATH.concat("/")
                            .concat(name).concat("(")
                            .concat(String.valueOf(count++))
                            .concat(")")
                            .concat(extension);
                }
            }
            try {
                inputStream.close();
            } catch (IOException ignored) {
            }
            return "/".concat(filePath);
        } catch (IOException e) {
            throw new InternalServerErrorException("Something wrong with server");
        }
    }

    public InputStream getFile(String filename) {
        String filePath = UPLOAD_PATH.concat("/").concat(filename);
        try {
            return new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new NotFoundException("Image not found");
        }
    }

    public void deleteFile(String fileName) {
        try {
            Files.delete(Paths.get(fileName.substring(1)));
        } catch (Exception ignored) {
        }
    }

    private void createUploadDirectoryIfNotExists() {
        File f = new File(UPLOAD_PATH);
        if (!f.exists()) f.mkdir();
    }

}
