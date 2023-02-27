package com.kuldeep.blog.blog.services.impl;

import com.kuldeep.blog.blog.entities.Category;
import com.kuldeep.blog.blog.entities.Post;
import com.kuldeep.blog.blog.entities.User;
import com.kuldeep.blog.blog.exceptions.ResourceNotFoundException;
import com.kuldeep.blog.blog.payloads.PostDto;
import com.kuldeep.blog.blog.repositories.CategoryRepo;
import com.kuldeep.blog.blog.repositories.PostRepo;
import com.kuldeep.blog.blog.repositories.UserRepo;
import com.kuldeep.blog.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user= this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId",categoryId));

       Post post = (Post) this.modelMapper.map(postDto, postRepo.getClass());
       post.setImageName("default.png");
       post.setDate(new Date());
       post.setUser(user);
       post.setCategory(category);
       Post newPost =  this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}
