package com.kuldeep.blog.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private  int id;
    @NotEmpty @Size(min = 4, message = "Username must be min of 4 characters")
    private  String name;
    @Email(message = "Please enter a valid email address")
    private  String email;
    @NonNull @NotEmpty
    private String password;

    @NonNull @NotEmpty
    private  String about;

}
