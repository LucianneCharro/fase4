package com.fiap.gerenciamento_encomendas.controller;

import com.fiap.gerenciamento_encomendas.model.Usuario;
import com.fiap.gerenciamento_encomendas.repository.UserRepository;
import com.fiap.gerenciamento_encomendas.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Api(value = "Auth Controller", description = "Operações de autenticação")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registrar")
    @ApiOperation(value = "Registrar um novo usuário")
    public ResponseEntity<?> registerUser(@RequestBody Usuario user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Usuario registrado com successo");
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login de usuário")
    public ResponseEntity<?> loginUser(@RequestBody Usuario user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            String jwt = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciais Invalidas");
        }
    }

}
