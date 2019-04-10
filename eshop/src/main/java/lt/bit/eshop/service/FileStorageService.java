package lt.bit.eshop.service;


import lt.bit.eshop.config.StorageProperties;
import lt.bit.eshop.validation.exceptions.FileFormatException;
import lt.bit.eshop.validation.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileStorageService {
    private final Path location;

    private final Path tmpLocation;

    private final Map<String, String> allowedContentTypes = new HashMap<>();

    @Autowired
    public FileStorageService(StorageProperties properties) {
        this.location = Paths.get(properties.getLocation());
        this.tmpLocation = Paths.get(properties.getTmp());
    }

    @PostConstruct
    public void init() throws IOException {
        allowedContentTypes.put("image/jpeg", "JPG");
        allowedContentTypes.put("image/png", "PNG");

        if (Files.notExists(this.location)) {
            Files.createDirectories(this.location);
        }


        if (Files.notExists(this.tmpLocation)) {
            Files.createDirectories(this.tmpLocation);
        }
    }

    public void store(MultipartFile file) throws StorageException, FileFormatException {
        this.store(file, file.getOriginalFilename().split(".")[0]);
    }

    public String store(MultipartFile file, String fileName) throws StorageException, FileFormatException {
        String storedFileName;

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            if (!allowedContentTypes.containsKey(file.getContentType())) {
                throw new FileFormatException("Unsupported file extention");
            }

            StringBuilder builder = new StringBuilder();


            builder.append(fileName);
            builder.append(".");
            builder.append(allowedContentTypes.get(file.getContentType()));

            storedFileName = builder.toString().toLowerCase();

            Files.copy(file.getInputStream(), this.tmpLocation.resolve(storedFileName));

        } catch (IOException | StorageException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }

        return storedFileName;
    }

    public void move(String fileName) throws StorageException {
        try {
            Files.move(this.tmpLocation.resolve(fileName), this.location.resolve(fileName));
        } catch (IOException e) {
            throw new StorageException(e.getMessage(), e);
        }
    }
}
