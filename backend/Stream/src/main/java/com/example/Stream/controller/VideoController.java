package com.example.Stream.controller;

import com.example.Stream.model.Role;
import com.example.Stream.model.User;
import com.example.Stream.model.VideoContent;
import com.example.Stream.service.UserService;
import com.example.Stream.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;

    @PostMapping("/upload/{userId}")
    public ResponseEntity<?> uploadVideo(
            @PathVariable Long userId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("file") MultipartFile file) throws IOException {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent() && user.get().getRole() == Role.ADMIN) {
            VideoContent video = videoService.uploadVideo(title, description, category, file);
            return ResponseEntity.ok(video);
        }
        return ResponseEntity.status(403).body("Only Admins can upload videos.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoContent> getVideoById(@PathVariable Long id) {
        return ResponseEntity.of(videoService.getVideo(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VideoContent>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }

    @DeleteMapping("/delete/{userId}/{videoId}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long userId, @PathVariable Long videoId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent() && user.get().getRole() == Role.ADMIN) {
            videoService.deleteVideo(videoId);
            return ResponseEntity.ok("Video deleted successfully.");
        }
        return ResponseEntity.status(403).body("Only Admins can delete videos.");
    }
}
