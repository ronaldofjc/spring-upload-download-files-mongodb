package br.com.image.controller;

import br.com.image.document.ImageFile;
import br.com.image.document.ImageFileResponse;
import br.com.image.exception.ControllerExceptionHandler;
import br.com.image.service.ImageFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "Images")
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageFileController extends ControllerExceptionHandler {

    private final ImageFileService imageFileService;

    @PostMapping(value = "/upload")
    @Operation(summary = "Realiza o upload de uma imagem")
    public ImageFileResponse uploadImageFile(@RequestParam("file") final MultipartFile image) throws IOException {
        return imageFileService.addImageFile(image);
    }

    @GetMapping(value = "/download/{title}")
    @Operation(summary = "Realiza o download de uma imagem")
    public ResponseEntity<Resource> getImageFile(@PathVariable final String title) {
        final ImageFile imageFile = imageFileService.getImageFile(title);
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + imageFile.getTitle() + "\"").
                body(new ByteArrayResource(imageFile.getImage().getData()));
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtém uma lista com o nome das imagens cadastradas")
    public List<String> findAllNames() {
        return imageFileService.findAll();
    }

    @DeleteMapping(value = "/remove/{fileName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Realiza a remoção de uma imagem")
    public void removeImage(@PathVariable final String fileName) {
        imageFileService.removeImage(fileName);
    }

}
