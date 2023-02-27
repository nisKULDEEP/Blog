package com.kuldeep.blog.blog.controllers;

import com.kuldeep.blog.blog.payloads.UserDto;
import com.kuldeep.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    //POST - create user

    @Autowired
    public UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser (@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);
        return  new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }
    //PUT - Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId){
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId ){
        this.userService.deleteUser(userId);
        return  new ResponseEntity(Map.of("message", "User deleted successfully"), HttpStatus.OK);
    }
@GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsersList = this.userService.getAllUsers();
        return ResponseEntity.ok(allUsersList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserData(@PathVariable Integer userId){
        UserDto userData = this.userService.getUserById(userId);
        return ResponseEntity.ok(userData);
    }
}
