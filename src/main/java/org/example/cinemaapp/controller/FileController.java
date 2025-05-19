package org.example.cinemaapp.controller;

import org.springframework.core.io.Resource;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {

    private final String uploadDir = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File saveFile = new File(System.getProperty("user.dir") + File.separator + uploadDir + fileName);

        saveFile.getParentFile().mkdirs(); // создаёт директорию при необходимости
        file.transferTo(saveFile);

        String downloadUrl = "/files/download/" + fileName;
        return ResponseEntity.ok(downloadUrl);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        Path path = Paths.get("uploads").resolve(fileName);
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(path.toUri());

        // Определяем тип файла (image/png, text/plain и т.д.)
        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = "application/octet-stream"; // если не распознано — скачивание
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }

}
