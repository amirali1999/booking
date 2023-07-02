package com.example.booking.controller;

import com.example.booking.dto.UserDTO;
import com.example.booking.exception.Response;
import com.example.booking.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<?> getUser(){
        return userService.getUser().createResponseEntity();
    }
    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody UserDTO userDTO){
        return userService.postUser(userDTO).createResponseEntity();
    }
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long id){
        return userService.deleteUser(id).createResponseEntity();
    }
    @PatchMapping("{userId}")
    public ResponseEntity<?> patchUser(@PathVariable("userId") Long id,
                                       @RequestBody UserDTO userDTO){
        return userService.patchUser(id,userDTO).createResponseEntity();
    }
}
