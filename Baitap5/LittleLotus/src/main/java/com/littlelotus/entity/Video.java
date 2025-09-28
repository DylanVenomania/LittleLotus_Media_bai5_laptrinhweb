package com.littlelotus.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private String videoUrl; 
    
    private LocalDateTime uploadDate = LocalDateTime.now();
    
    private boolean isPublished = false;


    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "category_id", nullable = false) 
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) 
    private User uploader;
}