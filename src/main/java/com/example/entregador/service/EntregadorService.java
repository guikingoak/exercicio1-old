package com.example.entregador.service;

import com.example.entregador.model.Entregador;
import com.example.entregador.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregadorService {
    @Autowired
    private EntregadorRepository repository;

    public List<Entregador> findAll() {
        return repository.findAll();
    }

    public Optional<Entregador> findById(Long id) {
        return repository.findById(id);
    }

    public Entregador save(Entregador entregador) {
        return repository.save(entregador);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}