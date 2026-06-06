package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Region;

public interface  RegionRepository extends JpaRepository<Region, Integer>{
    
}
