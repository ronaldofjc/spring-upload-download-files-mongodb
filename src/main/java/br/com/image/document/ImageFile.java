package br.com.image.document;

import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "imageFile")
public class ImageFile {
    @Id
    private String id;
    private String title;
    private String type;
    private Binary image;
}