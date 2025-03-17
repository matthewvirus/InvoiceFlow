package com.invoiceflow.mapper;

import com.invoiceflow.dto.user.UserCreateDTO;
import com.invoiceflow.dto.user.UserDTO;
import com.invoiceflow.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return user != null ? new UserDTO(user.getId(), user.getEmail(), user.getRole()) : null;
    }

    public static User toEntity(UserCreateDTO dto) {
        return dto != null
                ? User.builder()
                    .email(dto.getEmail())
                    .password(dto.getPassword())
                    .role(dto.getRole())
                    .build()
                : null;
    }

    public static List<UserDTO> toDTOList(List<User> userList) {
        return userList != null
                ? userList.stream()
                    .map(UserMapper::toDTO)
                    .collect(Collectors.toList())
                : null;
    }
}