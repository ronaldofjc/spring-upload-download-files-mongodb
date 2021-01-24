package br.com.image.constants;

import lombok.Getter;

@Getter
public enum MessageErrors {
    IMAGE_EXISTS("An image with this name already exists: "),
    IMAGE_NOT_FOUND("There is no image with this name: ");

    private final String value;

    MessageErrors(final String value) {
        this.value = value;
    }
}
