package com.example.demo.user.mapper;


import com.example.demo.item.mapper.ItemMapper;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface UserMapper {
    UserInfoDto toDto(User user);

    User toModel(UserInfoDto userInfoDto);

}
