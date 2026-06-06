package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Commune;
import com.app.repositories.CommuneRepository;

@Service
public class CommuneService {
    @Autowired
    private CommuneRepository communeRepository;

    public List<Commune> getAllCommunes() {
        return communeRepository.findAll();
    }
    public List<Commune> findCommunesByDistrict(int districtId) {
        return communeRepository.findByDistrict_Id(districtId);
    }
    
    public Commune getCommuneById(int id) {
        return communeRepository.findById(id).orElse(null);
    }
}
