package com.JeysonAmado.App.Repositories.Users;

import com.JeysonAmado.App.Entities.Users.UserEntity;
import com.JeysonAmado.App.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);
}
