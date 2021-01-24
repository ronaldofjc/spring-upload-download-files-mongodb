package br.com.image.repository;

import br.com.image.document.ImageFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageFileRepository extends MongoRepository<ImageFile, String> {

    Optional<ImageFile> findByTitle(String title);
}
