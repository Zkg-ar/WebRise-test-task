package com.example.webrise.user.service;

import com.example.webrise.user.dto.UserDto;

public interface UserService {
    UserDto createUser (UserDto userDto);
    UserDto updateUser (UserDto userDto,Long id);
    void deleteByID(Long id);
    UserDto getByID(Long id);

}
