package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Demande;
import com.app.entities.DemandeStatut;
import com.app.entities.Parametre;
import com.app.services.DemandeService;
import com.app.services.DemandeStatutService;
import com.app.services.ParametreService;

@RestController
@RequestMapping("/api")
public class ParametreControlleur {

    @Autowired
    private ParametreService parametreService;
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private DemandeStatutService demandeStatutService;

    @GetMapping("/rt/demandes")
    public List<Demande> getAllDemande() {
        return demandeService.getAllDemandes();
    }

    @GetMapping("/alertes/{idDemande}")
    public List<Map<String, String>> getParameter(@PathVariable("idDemande") int idDemande) {
        List<Map<String, String>> demandeStatutList = new ArrayList<>();
        Map<String, Parametre> matchedParametres = new HashMap<>();

        List<DemandeStatut> demandeStatut = demandeStatutService.getAllDemandeStatutById(idDemande);
        List<Parametre> parametres = parametreService.getAllParametres();

        for (Parametre p : parametres) {
            double totalDt = calculateDtBetweenStatuses(demandeStatut, p.getIdStatut1(), p.getIdStatut2());
            if (totalDt > p.getDt()) {
                String key = p.getIdStatut1() + "-" + p.getIdStatut2();
                Parametre current = matchedParametres.get(key);
                if (current == null || p.getDt() > current.getDt()) {
                    matchedParametres.put(key, p);
                }
            }
        }

        for (Parametre p : matchedParametres.values()) {
            double totalDt = calculateDtBetweenStatuses(demandeStatut, p.getIdStatut1(), p.getIdStatut2());
            Map<String,String> statutMap = Map.of(
                "Statut1", String.valueOf(p.getIdStatut1()),
                "Statut2", String.valueOf(p.getIdStatut2()),
                "DT", String.valueOf(totalDt),
                "Alerte", p.getAlerte()
            );
            demandeStatutList.add(statutMap);
        }
        return demandeStatutList;
    }

    private double calculateDtBetweenStatuses(List<DemandeStatut> demandeStatut, int idStatut1, int idStatut2) {
        double totalDt = 0.0;
        boolean started = false;

        for (DemandeStatut ds : demandeStatut) {
            int currentStatutId = ds.getStatut().getId();

            if (!started) {
                if (currentStatutId == idStatut1) {
                    started = true;
                    totalDt += ds.getDt();
                    if (idStatut1 == idStatut2) {
                        return totalDt;
                    }
                }
            } else {
                totalDt += ds.getDt();
                if (currentStatutId == idStatut2) {
                    return totalDt;
                }
            }
        }

        return 0.0;
    }


}
