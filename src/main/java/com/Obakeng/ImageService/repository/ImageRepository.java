package com.Obakeng.ImageService.repository;

import com.Obakeng.ImageService.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository <ImageData, Long> {
    Optional<ImageData> findByName(String fileName);
}
