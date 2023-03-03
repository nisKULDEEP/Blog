package com.kuldeep.blog.blog.payloads;

import com.kuldeep.blog.blog.entities.Category;
import com.kuldeep.blog.blog.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
@Getter
@Setter
public class PostDto {
    private String title;
    private String Content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;


}
