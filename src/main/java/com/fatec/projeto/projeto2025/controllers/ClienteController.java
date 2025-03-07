package com.fatec.projeto.projeto2025.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    
    @PostMapping("/criarCliente")
    public String criarCliente(@RequestBody Cliente cliente){
        return "O Cliente"+cliente.getNome()+" de idade"+cliente.getIdade()+"foi criado";
    }
}
