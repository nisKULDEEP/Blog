package com.kuldeep.blog.blog.repositories;

import com.kuldeep.blog.blog.entities.Category;
import com.kuldeep.blog.blog.entities.Post;
import com.kuldeep.blog.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
