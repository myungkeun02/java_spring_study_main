package org.myungkeun.shop_study.service.impl;

import org.modelmapper.ModelMapper;
import org.myungkeun.shop_study.entity.Category;
import org.myungkeun.shop_study.exception.ResourceNotFoundException;
import org.myungkeun.shop_study.payload.CategoriesResponseDto;
import org.myungkeun.shop_study.payload.CategoryDto;
import org.myungkeun.shop_study.repository.CategoryRepository;
import org.myungkeun.shop_study.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapToEntity(categoryDto);
        Category newCategory = categoryRepository.save(category);
        CategoryDto categoryResponse = mapToDto(newCategory);
        return categoryResponse;
    }

    @Override
    public CategoriesResponseDto getAllCategory(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<Category> listOfCategories = categories.getContent();
        List<CategoryDto> contents = listOfCategories.stream().map(category ->
                mapToDto(category)).collect(Collectors.toList());
        CategoriesResponseDto categoriesResponseDto = new CategoriesResponseDto();
        categoriesResponseDto.setContent(contents);
        categoriesResponseDto.setPageNo(categories.getNumber());
        categoriesResponseDto.setPageSize(categories.getSize());
        categoriesResponseDto.setTotalPages(categories.getTotalPages());
        categoriesResponseDto.setTotalElements(categories.getTotalElements());
        categoriesResponseDto.setLast(categories.isLast());
        return categoriesResponseDto;
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return mapToDto(category);
    }

    @Override
    public CategoryDto updateCategoryById(int id, CategoryDto categoryDto) {
        Category oldCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        oldCategory.setId(categoryDto.getId());
        oldCategory.setName(categoryDto.getName());
        oldCategory.setDescription(categoryDto.getDescription());
        Category updateCategory = categoryRepository.save(oldCategory);
        return mapToDto(updateCategory);
    }

    @Override
    public String deleteCategoryById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
        return "deleted";
    }

    public CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    public Category mapToEntity(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }
}
