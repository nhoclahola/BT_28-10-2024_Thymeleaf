package com.nhoclahola.bt_28102024_thymeleaf.services;

import com.nhoclahola.bt_28102024_thymeleaf.entities.Category;
import jakarta.servlet.ServletContext;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService
{
    void handleFileUpload(MultipartFile image, String imageLink, Category categoryModel);

    void handleFileUpload(MultipartFile image, String imageLink, Category categoryModel, Category oldCategory);
}
