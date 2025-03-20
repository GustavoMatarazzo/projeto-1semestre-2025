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

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class.getName());
    private final List<Cliente> clientes = new ArrayList<>();
    private Long idCount = 1L;

    public ClienteController() {} // Construtor vazio
    
    // http://localhost:8080/api/cliente/criarCliente => POST
    @PostMapping("/criarCliente")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        if (cliente == null || cliente.getNome() == null || cliente.getIdade() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        cliente.setId(idCount++);
        clientes.add(cliente);
        logger.info("Recebido JSON: Nome={}, Idade={}", cliente.getNome(), cliente.getIdade());
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping("/listarClientes")
    public List<Cliente> listarClientes() {
        return clientes;
    }

    @PutMapping("/atualizarCliente/{id}")
    public ResponseEntity<String> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        if (clienteAtualizado == null || clienteAtualizado.getNome() == null || clienteAtualizado.getIdade() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos para atualização.");
        }
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                cliente.setNome(clienteAtualizado.getNome());
                cliente.setIdade(clienteAtualizado.getIdade());
                logger.info("Cliente atualizado: Id={}, Nome={}, Idade={}", cliente.getId(), cliente.getNome(), cliente.getIdade());
                return ResponseEntity.ok("Cliente atualizado com sucesso!");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente com ID " + id + " não encontrado.");
    }

    @DeleteMapping("/deletarCliente/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable Long id) {
        boolean removido = clientes.removeIf(cliente -> cliente.getId().equals(id));
        if (removido) {
            return ResponseEntity.ok("Cliente removido com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe cliente com id: " + id);
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
