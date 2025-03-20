
package com.fatec.projeto.projeto2025.controllers;

import org.springframework.web.bind.annotation.*;

import com.fatec.projeto.projeto2025.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ExercicioController exercicioController;
        private static final Logger logger = 
        LoggerFactory.getLogger(ClienteController.class.getName());

        private final List<Cliente> clientes = new ArrayList<>();
        private Long idCount = 1L;

    ClienteController(ExercicioController exercicioController) {
        this.exercicioController = exercicioController;
    }
        
    //http://localhost:8080/api/cliente/criarCliente => POST
    @PostMapping("/criarCliente")
    public ResponseEntity<Cliente> CriarCliente(@RequestBody Cliente cliente) {
        cliente.setId(idCount++);
        clientes.add(cliente);

        logger.info("Recebido JSON: Nome={}, Idade={}", cliente.getNome(), cliente.getIdade());
        //return "O Cliente "+cliente.getNome()+ " de idade "+cliente.getIdade()+" foi criado";
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/listarClientes")
    public List<Cliente> ListarClientes() {
        return clientes;
    }

    @PutMapping("/atualizarCliente/{id}")
    public ResponseEntity<String> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                cliente.setNome(clienteAtualizado.getNome());
                cliente.setIdade(clienteAtualizado.getIdade());

                logger.info("Cliente atualizado: Id={}, Nome={}, Idade={}",
                        cliente.getId(), cliente.getNome(), cliente.getIdade());

                return ResponseEntity.ok("Cliente atualizado com sucesso!");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente com ID " + id + " não encontrado.");
    }

    @DeleteMapping("/deletarCliente/{id}")
    public String DeletarClientes(@PathVariable Long id) {
        for( Cliente cliente: clientes) {
            if (cliente.getId().equals(id)) {
                clientes.remove(cliente);
                return "Cliente removido com sucesso!";
            }
        }

        return "Não existe cliente com id: "+id;
    }

    @GetMapping("/buscarCliente/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return ResponseEntity.ok(cliente);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente com ID " + id + " não encontrado.");
    }

}
