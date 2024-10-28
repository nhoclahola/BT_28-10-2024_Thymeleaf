package com.nhoclahola.bt_28102024_thymeleaf.services;

import com.nhoclahola.bt_28102024_thymeleaf.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService
{
    <S extends Category> S save(S entity);

    Optional<Category> findById(Integer integer);

    List<Category> findAll();

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Category entity);

    List<Category> searchByKeyword(String keyword);

    Page<Category> findByCategoryNameContaining(String keyword, Pageable pageable);
}
