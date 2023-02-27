package com.kuldeep.blog.blog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @NotEmpty @NotNull @Column(length = 1000)
    private String title;
    @NotEmpty @NotNull
    private String content;
    private String imageName;
    private Date date;
    @ManyToOne
    private User user;
    @ManyToOne @JoinColumn(name = "category_id")
    private Category category;


}
