package guru.springframework.spring5recipeapp.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void save(Long id, MultipartFile file) throws IOException;

}
