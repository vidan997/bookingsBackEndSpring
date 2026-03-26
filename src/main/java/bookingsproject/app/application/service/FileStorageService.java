package bookingsproject.app.application.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    List<String> saveAll(List<MultipartFile> files) throws IOException;
    
    public void deleteByUrl(String url) throws IOException;
}

