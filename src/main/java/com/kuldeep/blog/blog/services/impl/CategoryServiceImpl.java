package com.kuldeep.blog.blog.services.impl;

import com.kuldeep.blog.blog.entities.Category;
import com.kuldeep.blog.blog.exceptions.ResourceNotFoundException;
import com.kuldeep.blog.blog.payloads.CategoryDto;
import com.kuldeep.blog.blog.repositories.CategoryRepo;
import com.kuldeep.blog.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;


import java.util.List;
import java.util.stream.Collectors;

@ApplicationScope
@Service
public class CategoryServiceImpl implements CategoryService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto){
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category addedResponse = this.categoryRepo.save(category);
        CategoryDto response = this.modelMapper.map(addedResponse, CategoryDto.class);

        return response;
    };
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId){

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "Category Id", categoryId));
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        Category savedData = this.categoryRepo.save(category);

        return this.modelMapper.map(savedData, CategoryDto.class);
    };

     @Override
    public void deleteCategory(Integer categoryId){
         Category category =  this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "Category Id", categoryId));
         this.categoryRepo.deleteById(categoryId);
     };

    @Override
    public CategoryDto getCategory(Integer categoryId){
        Category category =  this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "Category Id", categoryId));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory(){
         List<Category> categoryList =  this.categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream().map(e -> this.modelMapper.map(e, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    };


}
