package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Devis;
import com.app.repositories.DevisRepository;

@Service
public class DevisService {
    @Autowired
    private DevisRepository devisRepository;

    public List<Devis> getAllDevis() {
        return devisRepository.findAllWithSousDevis();
    }

    public Devis getDevisById(int id) {
        return devisRepository.findByIdWithSousDevis(id);
    }

    public void saveDevis(Devis devis) {
        devisRepository.save(devis);
    }

    public void deleteDevis(int id) {
        devisRepository.deleteById(id);
    }
}