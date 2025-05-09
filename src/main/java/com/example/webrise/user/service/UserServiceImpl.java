package com.example.webrise.user.service;

import com.example.webrise.exceptions.AlreadyExist;
import com.example.webrise.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.webrise.user.dto.UserDto;
import com.example.webrise.user.mapper.UserMapper;
import com.example.webrise.user.model.User;
import com.example.webrise.user.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDto createUser(UserDto userDto) {
        if (userDto == null) {
            throw new NullPointerException("На вход не передан объект для создания");
        }
        User user = UserMapper.mapToModel(userDto);

        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new AlreadyExist(String.format("Пользователь с email = %s уже зарегистрирован", user.getEmail()));
        }

        return UserMapper.mapToDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id = %d не найден", id)));

        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }

        return UserMapper.mapToDto(userRepository.save(user));
    }

    @Override
    @SneakyThrows
    public void deleteByID(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id = %d не найден", id)));

        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getByID(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id = %d не найден", id)));

        return UserMapper.mapToDto(user);
    }
}
