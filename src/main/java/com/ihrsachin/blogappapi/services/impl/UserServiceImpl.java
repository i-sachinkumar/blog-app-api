package com.ihrsachin.blogappapi.services.impl;

import com.ihrsachin.blogappapi.exceptions.ResourceNotFoundException;
import com.ihrsachin.blogappapi.model.User;
import com.ihrsachin.blogappapi.payloads.UserDto;
import com.ihrsachin.blogappapi.repositories.UserRepo;
import com.ihrsachin.blogappapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userDtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return this.userToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {

        User currUser = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        currUser.setName(userDto.getName());
        currUser.setEmail(userDto.getEmail());
        currUser.setPassword(userDto.getPassword());
        currUser.setAbout(userDto.getAbout());
        userRepo.save(currUser);
        return userToUserDto(currUser);
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));
        return userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepo.findAll();
        List<UserDto> allUsersDto = new ArrayList<>();
        for (User user : allUsers) {
            allUsersDto.add(userToUserDto(user));
        }
        return allUsersDto;
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

    User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }


    UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
