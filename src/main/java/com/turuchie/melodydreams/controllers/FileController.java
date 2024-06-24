package com.turuchie.melodydreams.controllers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.turuchie.melodydreams.services.FileService;

@RestController
public class FileController {


    @Value("${file.upload-dir}")
    private String FILE_UPLOAD_DIR;
    
    @Autowired
    private FileService fileService;

    @GetMapping("/encodedFilePath/{fileName:.+}")
    public String getEncodedFilePath(@PathVariable String fileName) {
        String encodedFilePath = fileService.getEncodedFilePath(fileName);
        return encodedFilePath;
    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        Path filePath = Paths.get(FILE_UPLOAD_DIR).resolve(fileName);
        Resource resource;
        try {
            resource = new org.springframework.core.io.PathResource(filePath);
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        // Try to determine file's content type
        String contentType;
        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        // Fallback to the application/octet-stream content type if type could not be determined
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @GetMapping("/files")
    public ResponseEntity<List<Resource>> serveAllFiles() {
        Path uploadPath = Paths.get(FILE_UPLOAD_DIR);
        try {
            // Get list of all files in the directory
            List<Resource> resources = Files.walk(uploadPath)
                    .filter(Files::isRegularFile)
                    .map(filePath -> {
                        try {
                            return new org.springframework.core.io.PathResource(filePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .filter(resource -> resource != null && resource.exists())
                    .collect(Collectors.toList());

            // Return response with list of files
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(resources);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
