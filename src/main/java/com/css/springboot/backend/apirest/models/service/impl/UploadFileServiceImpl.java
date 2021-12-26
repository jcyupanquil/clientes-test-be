package com.css.springboot.backend.apirest.models.service.impl;

import com.css.springboot.backend.apirest.models.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    private static final String DIRECTORIO_UPLOADS = "uploads";

    @Override
    public Resource get(String fileName) throws MalformedURLException {
        Path filePath = getPath(fileName);

        logger.info("File path is: " + filePath.toString());

        org.springframework.core.io.Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            filePath = Paths.get("src/main/static/images").resolve("no-user.png").toAbsolutePath();
            resource = new UrlResource(filePath.toUri());
            logger.error("No se pudo cargar la imagen " + resource.getFilename());
        }

        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename().replace(" ", "");
        Path destinationFilePath = Paths.get(DIRECTORIO_UPLOADS).resolve(fileName).toAbsolutePath();
        logger.info("Destination file path is: " + destinationFilePath.toString());
        Files.copy(file.getInputStream(), destinationFilePath);
        return fileName;
    }

    @Override
    public boolean delete(String fileName) {
        if (fileName != null && fileName.length() > 0) {
            Path oldFilePath = Paths.get(DIRECTORIO_UPLOADS).resolve(fileName).toAbsolutePath();
            File oldFile = oldFilePath.toFile();
            if (oldFile.exists() && oldFile.canRead()) {
                oldFile.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String fileName) {
        return Paths.get(DIRECTORIO_UPLOADS).resolve(fileName).toAbsolutePath();
    }
}
