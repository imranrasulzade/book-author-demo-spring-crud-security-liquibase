package com.matrix.springpracticeapp.mapper;

import com.matrix.springpracticeapp.dto.UserDto;
import com.matrix.springpracticeapp.entity.AuthorityEntity;
import com.matrix.springpracticeapp.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default UserEntity mapToEntity(UserDto userDto){


        var user = UserEntity.builder().username(userDto.getUsername())
                .password(userDto.getPassword())
                .enabled(true)
                .build();

        var authorities = userDto.getRoles().stream()
                .map(it -> new AuthorityEntity(null, user, it)).toList();

        user.setAuthorityEntityList(authorities);
        return user;
    }
}
