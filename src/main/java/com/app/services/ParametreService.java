package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Parametre;
import com.app.repositories.ParametreRepository;
@Service
public class ParametreService {

    @Autowired
    private ParametreRepository parametreRepository;

    public List<Parametre> getAllParametres() {
        return parametreRepository.findAll();
    }
}
