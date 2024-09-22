package com.Obakeng.ImageService.service;

import com.Obakeng.ImageService.model.ImageData;
import com.Obakeng.ImageService.repository.ImageRepository;
import com.Obakeng.ImageService.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        if (file.isEmpty()){
            return "file has not been uploaded";
        }
        return "file uploaded successfully :" + file.getOriginalFilename();

    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);

        return ImageUtils.decompressImage(dbImageData.get().getImageData());

    }
}
