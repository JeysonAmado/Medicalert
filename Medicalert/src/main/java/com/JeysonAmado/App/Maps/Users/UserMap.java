package com.JeysonAmado.App.Maps.Users;

import com.JeysonAmado.App.Dto.User.UserDto;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import com.JeysonAmado.App.Maps.BaseMap;
import org.springframework.stereotype.Service;

@Service
public class UserMap extends BaseMap<UserDto, UserEntity> {
    public UserMap() {
        super(UserDto.class, UserEntity.class);
    }
}
