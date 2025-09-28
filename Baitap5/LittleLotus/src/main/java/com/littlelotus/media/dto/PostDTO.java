package com.littlelotus.media.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {
    private Long id;
    private String content;
    private String imageUrl;
    private String username;
    private LocalDateTime createdAt;
}
