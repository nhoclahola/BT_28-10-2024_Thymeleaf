package com.nhoclahola.bt_28102024_thymeleaf.repositories;

import com.nhoclahola.bt_28102024_thymeleaf.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>
{
    // Tìm theo tên
    List<Category> findByCategoryName(String name);

    @Query("SELECT c FROM Category c WHERE c.categoryName LIKE %:keyword%")
    List<Category> searchByKeyword(@Param("keyword") String keyword);

    Page<Category> findByCategoryNameContaining(String keyword, Pageable pageable);
}
