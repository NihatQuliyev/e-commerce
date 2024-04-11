package delivery.management.system.service;

import delivery.management.system.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileService {
    void upload(MultipartFile multipartFile);

    List<Path> findByName(List<Image> imageName);
}
