package org.myungkeun.blog_study.service;

import org.myungkeun.blog_study.entity.Category;
import org.myungkeun.blog_study.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);


}
