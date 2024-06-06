package com.example.entregador.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entregador.model.Entregador;
import com.example.entregador.service.EntregadorService;

@RestController
@RequestMapping("/api/entregadores")
public class EntregadorController {
    @Autowired
    private EntregadorService service;

    @GetMapping
    public List<Entregador> getAllEntregadores() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entregador> getEntregadorById(@PathVariable Long id) {
        Optional<Entregador> entregador = service.findById(id);
        return entregador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Entregador createEntregador(@RequestBody Entregador entregador) {
        return service.save(entregador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entregador> updateEntregador(@PathVariable Long id, @RequestBody Entregador entregadorDetails) {
        Optional<Entregador> entregador = service.findById(id);

        if (entregador.isPresent()) {
            Entregador updatedEntregador = entregador.get();
            updatedEntregador.setNome(entregadorDetails.getNome());
            updatedEntregador.setEmail(entregadorDetails.getEmail());
            updatedEntregador.setTelefone(entregadorDetails.getTelefone());
            service.save(updatedEntregador);
            return ResponseEntity.ok(updatedEntregador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntregador(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
 