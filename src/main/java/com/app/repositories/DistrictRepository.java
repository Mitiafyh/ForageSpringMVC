package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.District;
public interface DistrictRepository extends JpaRepository<District, Integer>{
    public List<District> findByRegion_Id(int regionId);
}
