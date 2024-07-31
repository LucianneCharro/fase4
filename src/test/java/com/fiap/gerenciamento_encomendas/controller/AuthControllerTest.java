package com.fiap.gerenciamento_encomendas.controller;

import com.fiap.gerenciamento_encomendas.model.Usuario;
import com.fiap.gerenciamento_encomendas.repository.UserRepository;
import com.fiap.gerenciamento_encomendas.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ShouldReturnSuccessMessage() {
        Usuario user = new Usuario();
        user.setUsername("testuser");
        user.setPassword("password");

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        ResponseEntity<?> response = authController.registerUser(user);

        verify(userRepository, times(1)).save(user);
        assertThat(response.getBody()).isEqualTo("Usuario registrado com successo");
    }

    @Test
    void loginUser_ShouldReturnSuccessMessage() {
        Usuario user = new Usuario();
        user.setUsername("testuser");
        user.setPassword("password");

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtUtil.generateToken(user.getUsername())).thenReturn("mockedJwtToken");

        ResponseEntity<?> response = authController.loginUser(user);

        assertThat(response.getBody()).isEqualTo("mockedJwtToken");
    }
}