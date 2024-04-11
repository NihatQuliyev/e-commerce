package delivery.management.system.service.impl;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.model.entity.Image;
import delivery.management.system.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IFileService implements FileService {

    private final Path ROOT = Paths.get("upload");
    private final ExceptionService exceptionService;

    @SneakyThrows
    private void init(){
        if (!Files.exists(ROOT)) {
            Files.createDirectory(ROOT);
        }
    }

    @SneakyThrows
    public void upload(MultipartFile multipartFile) {
        init();
        Files.copy(multipartFile.getInputStream(), this.ROOT.resolve(
                        Objects.requireNonNull(
                                multipartFile.getOriginalFilename())));
    }

    @SneakyThrows
    public List<Path> findByName(List<Image> images) {

        List<Path> imagesPaths = new LinkedList<>();
        for (Image image : images) {
            Path imagePath = Files.walk(ROOT)
                    .filter(path -> Files.isRegularFile(path) && path.getFileName().toString().equals(image.getName()))
                    .findFirst()
                    .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse("File not found: " + image.getName(), HttpStatus.NOT_FOUND)));
            imagesPaths.add(imagePath);
        }
        return imagesPaths;
    }
}
