package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Statut;
import com.app.repositories.StatutRepository;

@Service
public class StatutService {
    @Autowired
    private StatutRepository statutRepository;

    public Statut getStatutById(int id) {
        return statutRepository.findById(id).orElse(null);
    }
    public List<Statut> getAllStatuts() {
        return statutRepository.findAll();
    }
}
