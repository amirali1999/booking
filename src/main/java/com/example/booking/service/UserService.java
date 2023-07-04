package com.example.booking.service;

import com.example.booking.dto.UserDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.mapper.UserMapper;
import com.example.booking.model.User;
import com.example.booking.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Response getUser(){
        List<User> users = userRepository.findAll();
        return new Response(
                HttpStatus.OK,
                "Get user successfully",
                userMapper.DTOList(users),
                1);
    }
    public Response postUser(UserDTO userDTO){
        User user = userMapper.DTOToObject(userDTO);
        userRepository.save(user);
        return new Response(
                HttpStatus.OK,
                "Post user successfully",
                userMapper.ObjectToDTO(user),
                1
        );
    }
    public Response deleteUser(Long id) throws BookingException.NotFoundException {
        User user = userRepository.findById(id).orElseThrow(()-> new BookingException.NotFoundException("id"));
        userRepository.delete(user);
        return new Response(HttpStatus.OK,"Delete user successfully",null,1);
    }
    public Response patchUser(Long id, UserDTO userDTO){
        return new Response(HttpStatus.OK,"Patch user successfully",null,1);
    }
}
