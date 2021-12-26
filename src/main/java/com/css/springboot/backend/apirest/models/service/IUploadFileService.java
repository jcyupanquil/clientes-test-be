package com.css.springboot.backend.apirest.models.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadFileService {

    Resource get(String fileName) throws MalformedURLException;

    String copy(MultipartFile multipartFile) throws IOException;

    boolean delete(String fileName);

    Path getPath(String fileName);
}
