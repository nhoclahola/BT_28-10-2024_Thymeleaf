package com.nhoclahola.bt_28102024_thymeleaf.services.implement;

import com.nhoclahola.bt_28102024_thymeleaf.entities.Category;
import com.nhoclahola.bt_28102024_thymeleaf.services.ImageUploadService;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageUploadServiceImpl implements ImageUploadService
{
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Override
    public void handleFileUpload(MultipartFile image, String imageLink, Category categoryModel)
    {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdir();

        if (image != null && !image.isEmpty())
        {
            try
            {
                String fileName = Paths.get(image.getOriginalFilename()).getFileName().toString();
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                String fname = System.currentTimeMillis() + "." + ext;
                File file = new File(uploadDir, fname);
                image.transferTo(file);
                categoryModel.setImage(fname);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (imageLink != null && !imageLink.isBlank())
        {
            categoryModel.setImage(imageLink);
        }
        else
        {
            categoryModel.setImage("avatar.png");
        }
    }

    @Override
    public void handleFileUpload(MultipartFile image, String imageLink, Category categoryModel, Category oldCategory)
    {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdir();

        if (image != null && !image.isEmpty())
        {
            try
            {
                String fileName = Paths.get(image.getOriginalFilename()).getFileName().toString();
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                String fname = System.currentTimeMillis() + "." + ext;
                File file = new File(uploadDir, fname);
                image.transferTo(file);
                categoryModel.setImage(fname);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (imageLink != null && !imageLink.isBlank())
        {
            categoryModel.setImage(imageLink);
        }
        else
        {
            categoryModel.setImage(oldCategory.getImage());
        }
    }
}
