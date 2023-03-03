package com.kuldeep.blog.blog.controllers;
import com.kuldeep.blog.blog.payloads.ApiResponse;
import com.kuldeep.blog.blog.payloads.CategoryDto;
import com.kuldeep.blog.blog.payloads.CategoryResponse;
import com.kuldeep.blog.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
            CategoryDto category = this.categoryService.createCategory(categoryDto);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        CategoryDto category = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
        CategoryDto category = this.categoryService.getCategory( categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<CategoryResponse> getAllCategory(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber, @RequestParam(name = "pageSize", value = "5", required = false) Integer pageSize) {
        CategoryResponse category = this.categoryService.getAllCategory(pageNumber, pageSize);

        return new ResponseEntity<CategoryResponse>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }
}

