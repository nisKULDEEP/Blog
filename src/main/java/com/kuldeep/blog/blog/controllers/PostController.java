package com.kuldeep.blog.blog.controllers;

import com.kuldeep.blog.blog.entities.Post;
import com.kuldeep.blog.blog.payloads.PostDto;
import com.kuldeep.blog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost (@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto newPost =  this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(newPost, HttpStatus.CREATED);

    }
}
