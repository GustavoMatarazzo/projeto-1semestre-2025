package com.fatec.projeto.projeto2025.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class exercicioController {
    
    @GetMapping("")
    public String HelloWord(){
        return "hello";
    }
}
