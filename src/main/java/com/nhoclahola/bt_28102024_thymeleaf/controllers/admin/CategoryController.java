package com.nhoclahola.bt_28102024_thymeleaf.controllers.admin;

import com.nhoclahola.bt_28102024_thymeleaf.entities.Category;
import com.nhoclahola.bt_28102024_thymeleaf.services.CategoryService;
import com.nhoclahola.bt_28102024_thymeleaf.services.ImageUploadService;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController
{
    private final CategoryService categoryService;
    private final ImageUploadService imageUploadService;
    private ServletContext servletContext;

    @GetMapping("")
    public String list(Model model)
    {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "admin/category-list";
    }

    @GetMapping("/add")
    public String categoryAdd(Model model)
    {
        return "admin/category-add";
    }

    @GetMapping("/edit")
    public String showEditCategory(@RequestParam("id") int categoryId, Model model)
    {
        Category category = categoryService.findById(categoryId).orElse(null);
        if (category == null)
        {
            return "error/404";
        }
        model.addAttribute("category", category);
        return "admin/category-edit";
    }

    @PostMapping("/insert")
    public String insertCategory(@RequestParam("categoryName") String categoryName,
                                 @RequestParam("status") int status,
                                 @RequestParam(value = "imageLink", required = false) String imageLink,
                                 @RequestParam(value = "image", required = false) MultipartFile image,
                                 RedirectAttributes redirectAttributes)
    {
        Category categoryModel = Category.builder()
                .categoryName(categoryName)
                .status(status)
                .build();

        imageUploadService.handleFileUpload(image, imageLink, categoryModel);

        categoryService.save(categoryModel);
        return "redirect:/admin/categories";
    }

    @PostMapping("/edit")
    public String editCategory(@RequestParam("categoryId") int categoryId,
                               @RequestParam("categoryName") String categoryName,
                               @RequestParam("status") int status,
                               @RequestParam(value = "imageLink", required = false) String imageLink,
                               @RequestParam(value = "image", required = false) MultipartFile image,
                               RedirectAttributes redirectAttributes) {
        Category oldCategory = categoryService.findById(categoryId).orElse(null);
        if (oldCategory == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Category not found");
            return "redirect:/admin/categories";
        }

        Category categoryModel = Category.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .status(status)
                .build();

        imageUploadService.handleFileUpload(image, imageLink, categoryModel, oldCategory);

        categoryService.save(categoryModel);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("id") int id, RedirectAttributes redirectAttributes)
    {
        try
        {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Category deleted successfully!");
        }
        catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("error", "Error occurred while deleting category.");
        }
        return "redirect:/admin/categories"; // Redirect to the category list page
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         Model model)
    {
        // Page tại controller sẽ hơn 1 so với service, vì cần đồng bộ với giao diện
        Pageable pageable = PageRequest.of(page - 1, 5); // 5 category mỗi trang

        // Nếu không có keyword, không tìm kiếm, trả về trang tìm kiếm
        if (keyword != null && !keyword.trim().isEmpty())
        {
            Page<Category> categoryPage = categoryService.findByCategoryNameContaining(keyword, pageable);
            model.addAttribute("categories", categoryPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", categoryPage.getTotalPages());
        }
        else
        {
            model.addAttribute("categories", new ArrayList<>());
            model.addAttribute("currentPage", 0);
            model.addAttribute("totalPages", 0);
        }

        model.addAttribute("keyword", keyword != null ? keyword : "");
        return "admin/category-search";
    }
}
