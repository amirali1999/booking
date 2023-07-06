package com.example.booking.service;

import com.example.booking.dto.RoleDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.mapper.RoleMapper;
import com.example.booking.model.Role;
import com.example.booking.model.User;
import com.example.booking.repository.RoleRepository;
import com.example.booking.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    public RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    public Response getRoles() {
        return new Response(
                HttpStatus.OK,
                "Get roles successfully",
                roleRepository.findAll(),
                1);
    }

    public Response addRole(RoleDTO roleDTO) throws BookingException.DuplicateFieldException {
        if(roleRepository.findByName(roleDTO.getName()).isPresent())
            throw new BookingException.DuplicateFieldException("name");
        Role role = roleMapper.roleDTOToRole(roleDTO);
        roleRepository.save(role);
        return new Response(HttpStatus.OK,
                "add.role.successfully",
                roleMapper.roleToRoleDTO(role),
                1
        );
    }

    public Response deleteRole(Long role_id) throws BookingException.NotFoundException {
        Role role = roleRepository.findById(role_id)
                .orElseThrow(() -> new BookingException.NotFoundException("Role not found!"));
        roleRepository.delete(role);
        return new Response(HttpStatus.OK,
                "delete role successfully",
                roleMapper.roleToRoleDTO(role),
                1);
    }

    public Response editRole(Long role_id, RoleDTO roleDTO)
            throws BookingException.DuplicateFieldException, BookingException.NotFoundException {
        Role role = roleRepository.findById(role_id)
                .orElseThrow(() -> new BookingException.NotFoundException("Role not found!"));
        if (role.getName() != null && !role.getName().equals(roleDTO.getName())) {
            if(roleRepository.findByName(roleDTO.getName()).isPresent())
                throw new BookingException.DuplicateFieldException("name");
            role.setName(roleDTO.getName());
        }
        if (roleDTO.getDescription() != null){
            role.setDescription(roleDTO.getDescription());
        }
        roleRepository.save(role);
        return new Response(HttpStatus.OK,
                "update.role.successfully",
                roleMapper.roleToRoleDTO(role),
                1);
    }
    public Response addRoleToUser(long user_id,long role_id) throws BookingException.NotFoundException,
            BookingException.DuplicateFieldException {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new BookingException.NotFoundException("user id"));
        Role newRole = roleRepository.findById(role_id)
                .orElseThrow(() -> new BookingException.NotFoundException("role id"));
        for (Role role : user.getRoles()){
            if(role.equals(newRole)){
                throw new BookingException.DuplicateFieldException("role");
            }
        }
        Set<Role> roles = user.getRoles();
        roles.add(newRole);
        user.setRoles(roles);
        userRepository.save(user);
        return new Response(HttpStatus.OK,
                "add role to user successfully",
                roleMapper.roleToRoleDTO(newRole),
                1);
    }
}
