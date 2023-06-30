package com.JeysonAmado.App.Entities.Users;

import com.JeysonAmado.App.Dto.Auth.RegisterDto;
import com.JeysonAmado.App.Entities.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    public void setRegisterUser(RegisterDto registerDto){
        this.setName(registerDto.getName());
        this.setEmail(registerDto.getEmail());
        this.setPassword(registerDto.getPassword());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
