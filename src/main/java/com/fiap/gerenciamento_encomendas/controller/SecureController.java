package com.fiap.gerenciamento_encomendas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping
    public String secureEndpoint() {
        return "Este é um endpoint seguro";
    }
}