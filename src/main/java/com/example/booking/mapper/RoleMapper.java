package com.example.booking.mapper;

import com.example.booking.dto.RoleDTO;
import com.example.booking.model.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleDTO roleToRoleDTO(Role role);
    Role roleDTOToRole(RoleDTO roleDTO);
}
