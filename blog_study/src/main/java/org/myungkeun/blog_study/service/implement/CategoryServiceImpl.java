package org.myungkeun.blog_study.service.implement;

import org.modelmapper.ModelMapper;
import org.myungkeun.blog_study.entity.Category;
import org.myungkeun.blog_study.entity.Product;
import org.myungkeun.blog_study.exception.ResourceNotFoundException;
import org.myungkeun.blog_study.payload.CategoryDto;
import org.myungkeun.blog_study.payload.CategoryResponseDto;
import org.myungkeun.blog_study.repository.CategoryRepository;
import org.myungkeun.blog_study.service.CategoryService;
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
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
         return categories.stream().map( category ->
                modelMapper.map(category, CategoryDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return modelMapper.map(category, CategoryDto.class);
    }
}
