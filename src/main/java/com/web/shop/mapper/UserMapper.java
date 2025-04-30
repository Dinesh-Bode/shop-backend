package com.web.shop.mapper;

import com.web.shop.model.api.UserRequest;
import com.web.shop.model.table.User;

public class UserMapper {
  public static User userRequestUserMapper(UserRequest userRequest) {
    return User.builder()
        .firstName(userRequest.getFirstName())
        .lastName(userRequest.getLastName())
        .phoneNumber(userRequest.getPhoneNumber())
        .email(userRequest.getEmail())
        .password(userRequest.getPassword())
        .build();
  }
}
