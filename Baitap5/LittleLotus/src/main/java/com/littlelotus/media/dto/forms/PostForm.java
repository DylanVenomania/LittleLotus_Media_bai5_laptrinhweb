package com.littlelotus.media.dto.forms;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostForm {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
