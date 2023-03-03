package com.kuldeep.blog.blog.controllers;

import com.kuldeep.blog.blog.entities.Post;
import com.kuldeep.blog.blog.payloads.ApiResponse;
import com.kuldeep.blog.blog.payloads.PostDto;
import com.kuldeep.blog.blog.payloads.PostResponse;
import com.kuldeep.blog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser( @PathVariable Integer userId){
        List<PostDto> posts = this.postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts = this.postService.getPostByCategory(categoryId);
        return  new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value= "pageNumber", defaultValue = "1", required = false) Integer pageNumber, @RequestParam(name="pageSize", defaultValue = "10", required = false) Integer pageSize){
        PostResponse posts =  this.postService.getAllPost(pageNumber, pageSize);
        return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);

    }
    @DeleteMapping("/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("post deleted successfully", true);
    }
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId){
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.CREATED);
    }
}
