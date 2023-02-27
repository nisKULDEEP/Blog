package com.kuldeep.blog.blog.repositories;

import com.kuldeep.blog.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
