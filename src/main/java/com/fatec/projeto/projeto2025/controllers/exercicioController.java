package com.fatec.projeto.projeto2025.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class exercicioController {
    
    @GetMapping("{nome}")
    public String HelloWorld(@PathVariable String nome){
        return "NOME2"; 

    }

    @GetMapping("/get-idade/{idade}")
    public String RetornaIdade(@PathVariable Integer idade) {
        try {
            if (idade < 0 ) {
                throw new NumberFormatException();
            }

            if (idade < 12 ) {
                return "Criança";
            } else if (idade <=18 ){
                return "Adolescente"; 
            } else if (idade <=60 ) {
                return "Adulto";
            } else {
                return "idoso"; 
            }

        } catch (NumberFormatException e) {
            return "idade inválida";
        }
    }

    @GetMapping("/get-par-impar/{numero}")
    public String RetornaParImpar(@PathVariable Integer numero){
    }

}
