package com.nhoclahola.bt_28102024_thymeleaf.services.implement;

import com.nhoclahola.bt_28102024_thymeleaf.entities.Category;
import com.nhoclahola.bt_28102024_thymeleaf.repositories.CategoryRepository;
import com.nhoclahola.bt_28102024_thymeleaf.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService
{
    private final CategoryRepository categoryRepository;

    @Override
    public <S extends Category> S save(S entity)
    {
        return categoryRepository.save(entity);
    }

    @Override
    public Optional<Category> findById(Integer integer)
    {
        return categoryRepository.findById(integer);
    }

    @Override
    public List<Category> findAll()
    {
        return categoryRepository.findAll();
    }

    @Override
    public boolean existsById(Integer integer)
    {
        return categoryRepository.existsById(integer);
    }

    @Override
    public long count()
    {
        return categoryRepository.count();
    }

    @Override
    public void deleteById(Integer integer)
    {
        categoryRepository.deleteById(integer);
    }

    @Override
    public void delete(Category entity)
    {
        categoryRepository.delete(entity);
    }

    @Override
    public List<Category> searchByKeyword(String keyword)
    {
        return categoryRepository.searchByKeyword(keyword);
    }

    @Override
    public Page<Category> findByCategoryNameContaining(String keyword, Pageable pageable)
    {
        return categoryRepository.findByCategoryNameContaining(keyword, pageable);
    }
}
