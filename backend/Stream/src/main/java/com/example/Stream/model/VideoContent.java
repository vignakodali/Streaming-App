package com.example.Stream.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "video_content")
public class VideoContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String s3Url; 
    @Column(nullable = false)
    private String url; 
    private String category;
    private LocalDateTime uploadedAt;
    private int views = 0;
}
