package com.JeysonAmado.App.Interfaces.Services;



import com.JeysonAmado.App.Dto.Auth.RegisterDto;
import com.JeysonAmado.App.Dto.User.UserDto;
import com.JeysonAmado.App.Entities.Users.UserEntity;

import java.util.List;

public interface UserServiceInterface {

    UserEntity createUser(UserEntity User, Long userId);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers();

    UserEntity updateUser(UserEntity User, Long userId);

    UserEntity deleteUser(Long UserId, Long userId);

    UserEntity registerUser(RegisterDto registerDto) throws Exception;
}
