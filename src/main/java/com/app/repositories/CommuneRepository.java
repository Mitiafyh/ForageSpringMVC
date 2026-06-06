package com.app.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Commune;
public interface CommuneRepository extends JpaRepository<Commune, Integer> {
    public List<Commune> findByDistrict_Id(int districtId);
    
}
