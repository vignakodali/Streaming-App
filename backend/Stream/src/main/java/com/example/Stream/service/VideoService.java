package com.example.Stream.service;

import com.example.Stream.model.VideoContent;
import com.example.Stream.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private S3Service s3Service;

    private static final String BUCKET_NAME = "streaming-app-videos";

    @Transactional
    public VideoContent uploadVideo(String title, String description, String category, MultipartFile file) throws IOException {
        String uniqueId = UUID.randomUUID().toString();
        String fileName = uniqueId + ".mp4";
        String s3Url = "https://" + BUCKET_NAME + ".s3.amazonaws.com/" + fileName;
        s3Service.uploadFile(file, fileName);
        VideoContent video = new VideoContent();
        video.setTitle(title);
        video.setDescription(description);
        video.setCategory(category);
        video.setS3Url(s3Url); 
        video.setUrl(fileName);
        video.setUploadedAt(LocalDateTime.now());
        video.setViews(0);

        return videoRepository.save(video);
    }

    public List<VideoContent> getAllVideos() {
        return videoRepository.findAll();
    }

    public Optional<VideoContent> getVideo(Long id) {
        return videoRepository.findById(id);
    }

    public void deleteVideo(Long videoId) {
        videoRepository.deleteById(videoId);
    }
}
