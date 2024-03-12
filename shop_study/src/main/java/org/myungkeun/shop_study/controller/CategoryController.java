package org.myungkeun.shop_study.controller;

import org.myungkeun.shop_study.payload.CategoriesResponseDto;
import org.myungkeun.shop_study.payload.CategoryDto;
import org.myungkeun.shop_study.service.CategoryService;
import org.myungkeun.shop_study.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<CategoriesResponseDto> getAllCategory(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return new ResponseEntity<>(categoryService.getAllCategory(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(categoryService.deleteCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable(name = "id") int id, CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.updateCategoryById(id, categoryDto), HttpStatus.OK);
    }

}
