package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.District;
import com.app.repositories.DistrictRepository;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    public List<District> findDistrictsByRegion(int regionId) {
        return districtRepository.findByRegion_Id(regionId);
    }
}
 