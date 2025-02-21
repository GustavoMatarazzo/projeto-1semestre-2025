package com.fatec.projeto.projeto2025.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class exercicioController {
    
    @GetMapping("{nome}")
    public String HelloWorld(@PathVariable String nome){
        return "NOME"; 

    }
}
