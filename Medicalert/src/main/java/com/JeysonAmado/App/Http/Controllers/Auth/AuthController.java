package com.JeysonAmado.App.Http.Controllers.Auth;


import com.JeysonAmado.App.Dto.Auth.LoginDto;
import com.JeysonAmado.App.Dto.Auth.RegisterDto;
import com.JeysonAmado.App.Entities.Users.UserEntity;
import com.JeysonAmado.App.Http.Config.CustomUserDetails;
import com.JeysonAmado.App.Http.Config.JWTUtilities;
import com.JeysonAmado.App.Interfaces.Services.UserRoleServiceInterface;
import com.JeysonAmado.App.Interfaces.Services.UserServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.JeysonAmado.App.Utilities.Constants.UserRoleConstant.CUSTOMER_ID;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JWTUtilities jwtUtilities;

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private UserRoleServiceInterface userRoleService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTUtilities jwtUtilities) {
        this.authenticationManager = authenticationManager;
        this.jwtUtilities = jwtUtilities;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        String jwt = this.jwtUtilities.create(loginDto.getEmail(), userId);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if (registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            try {
                UserEntity user = userService.registerUser(registerDto);
                userRoleService.createUserRole(user.getId(),CUSTOMER_ID);
                return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Registrado");
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar usuario: " + e.getMessage());
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Las contraseñas no coinciden");
        }
    }
}
