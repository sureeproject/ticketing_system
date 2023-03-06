package com.dxc.ticket.sytem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.dxc.ticket.system.dto.UserDto;
import com.dxc.ticket.system.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    void updateFromDto(UserDto dto, @MappingTarget User user);
    
    User toEntity(UserDto userDto);
    

}
