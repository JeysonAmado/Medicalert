package com.JeysonAmado.App.Interfaces.Services;

import com.JeysonAmado.App.Entities.Users.UserEntity;
import com.JeysonAmado.App.Entities.Users.UserRoleEntity;

public interface UserRoleServiceInterface {

    UserRoleEntity createUserRole(Long userId, Long RoleId);
}
