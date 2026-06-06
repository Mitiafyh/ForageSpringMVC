package com.app.controller;

import java.util.ArrayList;
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

        List<DemandeStatut> demandeStatut = demandeStatutService.getAllDemandeStatutById(idDemande);
        List<Parametre> parametres = parametreService.getAllParametres();


        for (DemandeStatut ds1 : demandeStatut) {
            for (DemandeStatut ds2 : demandeStatut) {
                for (Parametre p : parametres) {
                    if (ds1.getStatut().getId() == p.getIdStatut1() && ds2.getStatut().getId() == p.getIdStatut2()) {


                        if (ds2.getDt() > p.getDt()) {
                            
                            Map<String,String> statutMap = Map.of(
                                "idDemandeStatut 1", String.valueOf(ds1.getId()),
                                "idDemandeStatut 2", String.valueOf(ds2.getId()),
                                "Statut1", ds1.getStatut().getNomStatut(),
                                "Statut2", ds2.getStatut().getNomStatut(),
                                "DT", String.valueOf(ds2.getDt()),
                                "Alerte", p.getAlerte()
                            );
                            demandeStatutList.add(statutMap);
                        }
                    }
                }
            }
        }
        return demandeStatutList;
    }


}
