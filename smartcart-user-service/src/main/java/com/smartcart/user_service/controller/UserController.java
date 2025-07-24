package com.smartcart.user_service.controller;

import com.smartcart.user_service.dto.User;
import com.smartcart.user_service.entity.UserEntity;
import com.smartcart.user_service.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping("{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public UserEntity updateUser(@PathVariable Long id, User user){
        return userService.updateUser(id,user);
    }
    @GetMapping("/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.FOUND)
    public UserEntity getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @PutMapping("/{id}/deactivate")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void softDeleteUser(@PathVariable Long id){
        userService.softDeleteUser(id);
    }
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

    }
    @PutMapping("{id}/restore")
    @ResponseStatus(HttpStatus.OK)
    public void restoreUser(@PathVariable Long id){
    userService.restoreUser(id);
    }
    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
    @GetMapping("/filter")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public List<UserEntity> filterByRole(@RequestParam String role){
        return userService.filterUsersByRole(role);
    }
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserEntity> searchUsers(@RequestParam String keyword){
        return userService.searchUsers(keyword);
    }

    @GetMapping("/UsersCount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public long countUsers(){
      return  userService.countUsers();
    }




}
