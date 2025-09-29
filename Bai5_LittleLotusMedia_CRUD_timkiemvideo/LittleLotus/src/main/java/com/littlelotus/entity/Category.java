package com.littlelotus.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data 
@Table(name = "categories")
@EqualsAndHashCode(exclude = "videos") 
@ToString(exclude = "videos") 
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name; 

    @Column(columnDefinition = "TEXT")
    private String description;
    
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videos;
}