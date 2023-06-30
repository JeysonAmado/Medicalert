package com.JeysonAmado.App.Services;

import com.JeysonAmado.App.Dto.Auth.RegisterDto;
import com.JeysonAmado.App.Dto.User.UserDto;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import com.JeysonAmado.App.Entities.Users.UserRoleEntity;
import com.JeysonAmado.App.Interfaces.Services.UserServiceInterface;
import com.JeysonAmado.App.Maps.Users.UserMap;
import com.JeysonAmado.App.Repositories.Users.UserRepository;
import com.JeysonAmado.App.Repositories.Users.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    @Autowired
    private UserMap userMap;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }
    @Override
    public UserEntity createUser(UserEntity user, Long userId) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.commitCreate(userId);
        return userRepository.save(user);
    }

    @Override
    public UserDto getUser(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        return userMap.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userMap::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserEntity updateUser(UserEntity user, Long userId) {
        user.commitUpdate(userId);
        return userRepository.save(user);
    }

    @Override
    public UserEntity deleteUser(Long userEntityId, Long userId) {
        UserEntity user = userRepository.findById(userEntityId).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        user.commitDelete(userId);
        userRepository.save(user);
        return user;
    }

    @Override
    public UserEntity registerUser(RegisterDto registerDto) throws Exception {
        if(userRepository.findByEmail(registerDto.getEmail()) != null){
            throw new Exception("Correo electrónico ya se encuentra en uso");
        }
        UserEntity user = new UserEntity();
        user.setRegisterUser(registerDto);
        return this.createUser(user,null);
    }
}
