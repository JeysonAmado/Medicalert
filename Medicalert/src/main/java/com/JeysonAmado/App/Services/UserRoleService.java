package com.JeysonAmado.App.Services;

import com.JeysonAmado.App.Entities.Users.UserEntity;
import com.JeysonAmado.App.Entities.Users.UserRoleEntity;
import com.JeysonAmado.App.Interfaces.Services.UserRoleServiceInterface;
import com.JeysonAmado.App.Repositories.Users.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements UserRoleServiceInterface {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRoleEntity createUserRole(Long userId, Long RoleId) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userId);
        userRoleEntity.setRoleId(RoleId);
        userRoleEntity.commitCreate(userId);
        return userRoleRepository.save(userRoleEntity);
    }
}
