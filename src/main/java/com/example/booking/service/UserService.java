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
    public Response patchUser(Long id, UserDTO userDTO) throws BookingException.NotFoundException, BookingException.DuplicateFieldException {
        User user = userRepository.findById(id).orElseThrow(()->new BookingException.NotFoundException("id"));
        if(userDTO.getFirstName() != null){
            user.setFirstName(userDTO.getFirstName());
        }
        if(userDTO.getLastName() != null){
            user.setLastName(userDTO.getLastName());
        }
        if(userDTO.getEmail() != null){
            if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
               throw new BookingException.DuplicateFieldException("email");
            }
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getPhoneNumber() != 0){
            if(userRepository.findByPhoneNumber(userDTO.getPhoneNumber()).isPresent()){
                throw new BookingException.DuplicateFieldException("phone number");
            }
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if(userDTO.getPassword() != null){
            user.setPassword(userDTO.getPassword());
        }
        if(userDTO.getAddress() != null){
            user.setAddress(userDTO.getAddress());
        }
        if(userDTO.getBirthday() != null){
            user.setBirthday(userDTO.getBirthday());
        }
        userRepository.save(user);
        return new Response(HttpStatus.OK,"Patch user successfully",null,1);
    }
}
