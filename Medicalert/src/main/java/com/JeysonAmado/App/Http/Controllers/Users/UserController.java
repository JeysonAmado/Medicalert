package com.JeysonAmado.App.Http.Controllers.Users;

import com.JeysonAmado.App.Dto.User.UserDto;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import com.JeysonAmado.App.Interfaces.Services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        Long userId = getCurrentUserId();
        UserEntity createdUser = userService.createUser(user,userId);
        return ResponseEntity.ok(createdUser);
    }

    private Long getCurrentUserId() {
        return 1L;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto user = userService.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        Long userId = getCurrentUserId();
        if (userService.getUser(id) == null) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        return ResponseEntity.ok(userService.updateUser(user,userId));
    }

    @DeleteMapping("delete/{userEntityId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userEntityId) {
        Long userId = getCurrentUserId();
        userService.deleteUser(userEntityId, userId);
        return ResponseEntity.noContent().build();
    }
}
