package com.ihrsachin.blogappapi.services;

import com.ihrsachin.blogappapi.model.User;
import com.ihrsachin.blogappapi.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, int userId);
    UserDto getUserById(int id);
    List<UserDto> getAllUsers();
    void deleteUser(int id);
}
