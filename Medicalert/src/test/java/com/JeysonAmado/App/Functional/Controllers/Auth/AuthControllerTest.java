package com.JeysonAmado.App.Functional.Controllers.Auth;

import com.JeysonAmado.App.Dto.Auth.LoginDto;
import com.JeysonAmado.App.Dto.Auth.RegisterDto;
import com.JeysonAmado.App.Http.Controllers.Auth.AuthController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("register-test")
public class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @BeforeEach
    public void setUp(){
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("Juanin");
        registerDto.setEmail("Juanin@example.com");
        registerDto.setPassword("password");
        registerDto.setConfirmPassword("password");
        authController.register(registerDto);
    }

    @Test
    public void isRegisterSuccessWorking() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("Ejemplon");
        registerDto.setEmail("user@example.com");
        registerDto.setPassword("password");
        registerDto.setConfirmPassword("password");

        ResponseEntity<String> response = authController.register(registerDto);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals("Usuario Registrado",response.getBody());
    }

    @Test
    public void isRegisterFailByUserAlreadyRegisteredWorking() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("Juanin");
        registerDto.setEmail("Juanin@example.com");
        registerDto.setPassword("password");
        registerDto.setConfirmPassword("password");

        ResponseEntity<String> response = authController.register(registerDto);
        assertEquals(HttpStatus.UNAUTHORIZED,response.getStatusCode());
        assertEquals("Error al registrar usuario: Correo electrónico ya se encuentra en uso",response.getBody());
    }

    @Test
    public void isRegisterFailByPasswordWorking() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("Pedrito Sola");
        registerDto.setEmail("pedrito@example.com");
        registerDto.setPassword("password2");
        registerDto.setConfirmPassword("password");

        ResponseEntity<String> response = authController.register(registerDto);
        assertEquals(HttpStatus.UNAUTHORIZED,response.getStatusCode());
        assertEquals("Las contraseñas no coinciden",response.getBody());

    }

    @Test
    public void isLoginWorking() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("Juanin@example.com");
        loginDto.setPassword("password");

        ResponseEntity<String> response = authController.login(loginDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Inicio de sesión exitoso",response.getBody());

    }

    @Test
    public void isFailLoginWorking() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("Juanin@example.com");
        loginDto.setPassword("password2");

        ResponseEntity<String> response = authController.login(loginDto);
        assertEquals(HttpStatus.UNAUTHORIZED,response.getStatusCode());
        assertEquals("Usuario o contraseña incorrecta",response.getBody());
    }
}
