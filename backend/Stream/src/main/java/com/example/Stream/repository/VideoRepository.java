package com.example.Stream.repository;
import com.example.Stream.model.VideoContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VideoRepository extends JpaRepository<VideoContent, Long> {
}
