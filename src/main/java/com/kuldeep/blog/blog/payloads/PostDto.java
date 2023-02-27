package com.kuldeep.blog.blog.payloads;

import com.kuldeep.blog.blog.entities.Category;
import com.kuldeep.blog.blog.entities.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class PostDto {
    private String title;
    private String Content;
    private String imageName;
    private Date addedDate;
    private User user;
    private Category category;


}
