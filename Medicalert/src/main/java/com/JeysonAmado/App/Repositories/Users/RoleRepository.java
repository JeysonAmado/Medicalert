package com.JeysonAmado.App.Repositories.Users;

import com.JeysonAmado.App.Entities.Users.RoleEntity;
import com.JeysonAmado.App.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity,Long> {
}
