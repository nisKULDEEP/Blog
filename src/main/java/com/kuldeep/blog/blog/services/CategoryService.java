package com.kuldeep.blog.blog.services;


import com.kuldeep.blog.blog.payloads.CategoryDto;
import com.kuldeep.blog.blog.payloads.CategoryResponse;

import java.util.List;

public interface CategoryService {

    // create
    public CategoryDto createCategory(CategoryDto categoryDto);

    // update
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //delete
    public void deleteCategory(Integer categoryId);

    //get
    public CategoryDto getCategory(Integer categoryId);

    //get all
    public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize);
}
