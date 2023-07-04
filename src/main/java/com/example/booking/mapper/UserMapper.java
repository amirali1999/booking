package com.example.booking.mapper;

import com.example.booking.dto.CityDTO;
import com.example.booking.dto.UserDTO;
import com.example.booking.model.City;
import com.example.booking.model.Passenger;
import com.example.booking.model.User;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

public interface UserMapper {
    @Mapping(source = "passengers",target = "passengersId",qualifiedByName = "mapPassengersToId")
    UserDTO ObjectToDTO(User user);
    User DTOToObject(UserDTO userDTO);
    List<UserDTO> DTOList(List<User> users);
    @Named("mapPassengersToId")
    static List<Long> mapPassengersToId(List<Passenger> passengers){
        return passengers.stream().map(Passenger::getId).collect(Collectors.toList());
    }
}
