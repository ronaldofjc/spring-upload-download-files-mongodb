package br.com.image.service;

import br.com.image.document.ImageFile;
import br.com.image.document.ImageFileResponse;
import br.com.image.exception.BusinessException;
import br.com.image.exception.EntityNotFoundException;
import br.com.image.repository.ImageFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.image.constants.MessageErrors.IMAGE_EXISTS;
import static br.com.image.constants.MessageErrors.IMAGE_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageFileService {

    private final ImageFileRepository repository;

    public ImageFileResponse addImageFile(final MultipartFile file) throws IOException {
        final String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        final Optional<ImageFile> imageFile = repository.findByTitle(fileName);

        if (imageFile.isPresent()) {
            throw new BusinessException(IMAGE_EXISTS.getValue() + fileName);
        }

        ImageFile image = ImageFile.builder()
                .title(fileName)
                .type(file.getContentType())
                .image(new Binary(BsonBinarySubType.BINARY, file.getBytes()))
                .build();

        image = repository.insert(image);
        log.info("New image created. ID: {}", image.getId());

        final String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/download/").
                path(image.getTitle()).toUriString();

        return ImageFileResponse.builder()
                .id(image.getId())
                .title(image.getTitle())
                .type(image.getType())
                .uri(fileUri)
                .build();
    }

    public ImageFile getImageFile(final String title) {
        final Optional<ImageFile> imageFile = repository.findByTitle(title);

        if (imageFile.isEmpty()) {
            throw new EntityNotFoundException(IMAGE_NOT_FOUND.getValue() + title);
        }

        return imageFile.get();
    }

    public List<String> findAll() {
        final List<String> list = new ArrayList<>();
        final Iterable<ImageFile> imageFiles = repository.findAll();

        imageFiles.forEach(image -> list.add(image.getTitle()));

        return list;
    }

    public void removeImage(final String title) {
        final Optional<ImageFile> imageFile = repository.findByTitle(title);

        if (imageFile.isEmpty()) {
            throw new EntityNotFoundException(IMAGE_NOT_FOUND.getValue() + title);
        }

        repository.deleteById(imageFile.get().getId());
    }
}
