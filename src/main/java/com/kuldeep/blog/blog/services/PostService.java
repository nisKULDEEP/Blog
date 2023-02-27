package com.kuldeep.blog.blog.services;

import com.kuldeep.blog.blog.entities.Post;
import com.kuldeep.blog.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    Post updatePost(PostDto postDto);

    void deletePost(Integer postId);

    List<Post> getAllPost();

    Post getPostById(Integer postId);

    List<Post> getPostByCategory(Integer categoryId);

    List<Post> searchPost(String keyword);




}
