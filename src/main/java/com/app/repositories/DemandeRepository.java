package com.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Demande;
public interface  DemandeRepository extends JpaRepository<Demande, Integer> {
    
}
