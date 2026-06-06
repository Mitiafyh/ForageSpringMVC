package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.SousDevis;
import com.app.repositories.SousDevisRepository;

@Service
public class SousDevisService {
    @Autowired
    private SousDevisRepository sousDevisRepository;

    public void saveSousDevis(SousDevis sd) {
        sousDevisRepository.save(sd);
    }

    public List<SousDevis> getSousDevisByDevisId(int devisId) {
        return sousDevisRepository.findByDevisId(devisId);
    }

    @Transactional
    public void deleteSousDevisByDevisId(int devisId) {
        sousDevisRepository.deleteByDevisId(devisId);
    }
}
