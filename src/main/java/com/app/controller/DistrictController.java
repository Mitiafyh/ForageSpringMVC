package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.entities.District;
import com.app.services.DistrictService;

@Controller
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping("/districts/{regionId}")
    @ResponseBody
    public List<District> getDistricts(@PathVariable("regionId") int regionId) {
        return districtService.findDistrictsByRegion(regionId);
    }
}
