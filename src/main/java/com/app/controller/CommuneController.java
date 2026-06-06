package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.entities.Commune;
import com.app.services.CommuneService;

@Controller
public class CommuneController {

    @Autowired
    private CommuneService communeService;

    @GetMapping("/communes/{districtId}")
    @ResponseBody
    public List<Commune> getCommunes(@PathVariable("districtId") int districtId) {
        return communeService.findCommunesByDistrict(districtId);
    }
}
