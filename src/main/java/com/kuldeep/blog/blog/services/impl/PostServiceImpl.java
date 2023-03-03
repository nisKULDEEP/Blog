package com.kuldeep.blog.blog.services.impl;

import com.kuldeep.blog.blog.entities.Category;
import com.kuldeep.blog.blog.entities.Post;
import com.kuldeep.blog.blog.entities.User;
import com.kuldeep.blog.blog.exceptions.ResourceNotFoundException;
import com.kuldeep.blog.blog.payloads.PostDto;
import com.kuldeep.blog.blog.payloads.PostResponse;
import com.kuldeep.blog.blog.repositories.CategoryRepo;
import com.kuldeep.blog.blog.repositories.PostRepo;
import com.kuldeep.blog.blog.repositories.UserRepo;
import com.kuldeep.blog.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

       Post post = this.modelMapper.map(postDto, Post.class);
       post.setImageName("default.png");
       post.setDate(new Date());
       post.setUser(user);
       post.setCategory(category);
       Post newPost =  this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post oldPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
//        oldPost.setCategory(postDto.getCategory());
        oldPost.setTitle(postDto.getTitle());
        oldPost.setContent(postDto.getContent());
        oldPost.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(oldPost);

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        this.postRepo.deleteById(postId);
        return;
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> posts= pagePost.getContent();
        List<PostDto> postDtos =  posts.stream().map(e -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setIsLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
       return posts.stream().map((e) -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }

    @Override
    public List<PostDto> getPostsByUserId(Integer userId){
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        List<Post> posts= this.postRepo.findByUser(user);
        return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
