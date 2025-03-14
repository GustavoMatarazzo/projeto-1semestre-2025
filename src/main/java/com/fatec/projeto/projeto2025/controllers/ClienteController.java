package com.fatec.projeto.projeto2025.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
        private static final Logger logger = LoggerFactory.getLogger(ClienteController.class.getName());

        private final List<Clientes> clientes = new ArrayList<>();
        private Long idCount = 1L;

    @PostMapping("/criarCliente")
    public String criarCliente(@RequestBody Cliente cliente){
        cliente.setId(idCount++);
        cliente.add(cliente);


        logger.info(format:"Recibo JSON: nome={}, Idade={}", cliente.getNome(), cliente.getIdade());
        return "O Cliente"+cliente.getNome()+" de idade"+cliente.getIdade()+"foi criado";
    }

    @GetMapping("/ListarClientes")
    public List<Cliente> ListarClientes(){
        return clientes;
    }
    @DeleteMapping("/DeleteClientes/{id}")
    public String DeleteClientes(@PathVariable Long id) {
        for (Cliente cliente: clientes){
            if (cliente.getId().equals(id)){
                clientes.remove(clientes);
                return "Cliente removido com sucesso!"; 
            }
        }
        return "Não existe cliente com id="+id;
    }
    //@PutMapping()
}
