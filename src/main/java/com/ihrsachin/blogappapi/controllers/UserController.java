package com.ihrsachin.blogappapi.controllers;


import com.ihrsachin.blogappapi.payloads.UserDto;
import com.ihrsachin.blogappapi.services.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    //GET:
    @GetMapping("/")
    public List<UserDto> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable int id){
        return userService.getUserById(id);
    }


    //POST : create user
    @PostMapping("/")
    public UserDto createUSer(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    //POST: add list of users
    @PostMapping("/addFromCSV")
    public List<UserDto> addUsersFromCSV(@RequestParam("file") MultipartFile file) {
        List<UserDto> addedUsers = new ArrayList<>();

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<UserDto> csvToBean = new CsvToBeanBuilder<UserDto>(reader)
                    .withType(UserDto.class)
//                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<UserDto> userDtoList = csvToBean.parse();

            for (UserDto userDto : userDtoList) {
                UserDto addedUser = userService.createUser(userDto);
                addedUsers.add(addedUser);
            }

        } catch (IOException e) {
            // Log or handle the exception, e.g., print the error message
            System.err.println("Error creating user: " + e.getMessage());
        }

        return addedUsers;
    }

    // Update
    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable int id ){
        return userService.updateUser(userDto, id);
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }



}
