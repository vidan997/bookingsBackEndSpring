package bookingsproject.app.application.service.imp;

import bookingsproject.app.application.service.FileStorageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImp implements FileStorageService {

    private final Path root = Paths.get("uploads/places");

    @Override
    public List<String> saveAll(List<MultipartFile> files) throws IOException {
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;

            String original = file.getOriginalFilename() == null ? "image" : file.getOriginalFilename();
            String safeName = UUID.randomUUID() + "-" + original.replaceAll("[^a-zA-Z0-9._-]", "_");
            Path dest = root.resolve(safeName);

            Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

            urls.add("/uploads/places/" + safeName);
        }

        return urls;
    }

    @Override
    public void deleteByUrl(String url) throws IOException {
        if (url == null || url.isBlank()) return;

        // očekujemo format: /uploads/places/filename.jpg
        String filename = Paths.get(url).getFileName().toString();
        Path filePath = root.resolve(filename);

        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }
}

