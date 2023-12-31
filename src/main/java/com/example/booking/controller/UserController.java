package com.example.booking.controller;

import com.example.booking.dto.UserDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
@SecurityRequirement(name = "token")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> getUser(){
        return userService.getUser().createResponseEntity();
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> postUser(@RequestBody UserDTO userDTO){
        return userService.postUser(userDTO).createResponseEntity();
    }
    @DeleteMapping("{userId}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long id) throws BookingException.NotFoundException {
        return userService.deleteUser(id).createResponseEntity();
    }
    @PatchMapping("{userId}")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public ResponseEntity<?> patchUser(@PathVariable("userId") Long id,
                                       @RequestBody UserDTO userDTO) throws BookingException.NotFoundException, BookingException.DuplicateFieldException {
        return userService.patchUser(id,userDTO).createResponseEntity();
    }
}
