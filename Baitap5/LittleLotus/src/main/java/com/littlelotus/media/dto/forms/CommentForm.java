package com.littlelotus.media.dto.forms;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentForm {

    @NotBlank
    private String content;

    private Long postId;
}
