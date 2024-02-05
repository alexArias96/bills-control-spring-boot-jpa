package com.bolsadeideas.springboot.app.models.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class UploadsFileServiceImpl implements IUploadsFileService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final static String UPLOADS_FOLDER = "uploads";

    @Override
    public Resource load(String filename) throws MalformedURLException {

        Path pathPhoto = getPath(filename);

        log.info("pathFoto: " + pathPhoto);

        Resource resource = new UrlResource(pathPhoto.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Error: Can't display the image: " + pathPhoto.toString());
        }

        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {

        //String rootPath = "C://Temp//uploads";
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFileName);
        log.info("rootPath: " + rootPath);
        Files.copy(file.getInputStream(), rootPath);

        return uniqueFileName;
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File archivo = rootPath.toFile();

        if (archivo.exists() &&  archivo.canRead()){
            if(archivo.delete()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        Files.createDirectory(Paths.get(UPLOADS_FOLDER));
    }

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }
}
