package br.com.image.document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageFileResponse {
    private String id;
    private String title;
    private String type;
    private String uri;
}
