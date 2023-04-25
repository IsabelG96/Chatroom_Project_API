package com.example.chatroom_project.controllers;

import com.example.chatroom_project.models.User;
import com.example.chatroom_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> displayAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> displayUserById (@PathVariable Long id) {
       return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(String.format("user of id %s has been deleted", id), HttpStatus.OK);
    }

    @PatchMapping(value = "/email/{id}")
    public ResponseEntity<User> changeEmail(@PathVariable Long id, @RequestBody String email){
        return new ResponseEntity<>(userService.updateUserEmail(email, id), HttpStatus.OK);
    }

    @PatchMapping(value = "/name/{id}")
    public ResponseEntity<User> changeName(@PathVariable Long id, @RequestBody String name){
        return new ResponseEntity<>(userService.updateUserName(name, id), HttpStatus.OK);
    }



}
