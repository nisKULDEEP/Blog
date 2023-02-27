package com.kuldeep.blog.blog.services;

import com.kuldeep.blog.blog.entities.User;
import com.kuldeep.blog.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser (UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);

}
