package com.example.webrise.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.example.webrise.user.dto.UserDto;
import com.example.webrise.user.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Получен запрос на создание пользователя {}", userDto);
        return userService.createUser(userDto);
    }

    @GetMapping("/{id}")
    public UserDto getByID(@PathVariable Long id) {
        log.info("Получен запрос на получение пользователя с id = {}", id);
        return userService.getByID(id);
    }


    @PutMapping("/{id}")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long id) {
        log.info("Получен запрос на обновление пользователя с id = {}", id);
        return userService.updateUser(userDto, id);
    }

    @DeleteMapping("{id}")
    public void updateUser(@PathVariable Long id) {
        log.info("Получен запрос на удаление пользователя с id = {}", id);
        userService.deleteByID(id);
    }


}
