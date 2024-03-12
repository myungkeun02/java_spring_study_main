package org.myungkeun.shop_study.service;

import org.myungkeun.shop_study.payload.CategoriesResponseDto;
import org.myungkeun.shop_study.payload.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoriesResponseDto getAllCategory(int pageNo, int pageSize, String sortBy, String sortDir);

    CategoryDto getCategoryById(int id);

    CategoryDto updateCategoryById(int id, CategoryDto categoryDto);

    String deleteCategoryById(int id);

}
