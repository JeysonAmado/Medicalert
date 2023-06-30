package com.JeysonAmado.App.Repositories.Users;

import com.JeysonAmado.App.Entities.Users.UserRoleEntity;
import com.JeysonAmado.App.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends BaseRepository<UserRoleEntity,Long> {
}
