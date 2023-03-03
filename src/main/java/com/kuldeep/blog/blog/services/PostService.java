package com.kuldeep.blog.blog.services;

import com.kuldeep.blog.blog.entities.Post;
import com.kuldeep.blog.blog.payloads.PostDto;
import com.kuldeep.blog.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> searchPost(String keyword);

    List<PostDto> getPostsByUserId(Integer userId);




}
