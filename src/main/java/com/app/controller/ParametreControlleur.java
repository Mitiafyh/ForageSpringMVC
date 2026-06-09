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
import com.app.entities.Statut;
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

        List<DemandeStatut> demandeStatut
                = demandeStatutService.getAllDemandeStatutById(idDemande);

        List<Parametre> parametres
                = parametreService.getAllParametres();

        for (Parametre p : parametres) {

            double totalDt = calculateDtBetweenStatuses(
                    demandeStatut,
                    p.getIdStatut1(),
                    p.getIdStatut2());

            if (totalDt >= p.getDt() && totalDt <= p.getDt2()) {

                String key = p.getIdStatut1() + "-" + p.getIdStatut2();

                Parametre current = matchedParametres.get(key);

                if (current == null
                        || (p.getDt() >= current.getDt()
                        && p.getDt2() >= current.getDt2())) {

                    matchedParametres.put(key, p);
                }
            }
        }

        for (Parametre p : matchedParametres.values()) {

            double totalDt = calculateDtBetweenStatuses(
                    demandeStatut,
                    p.getIdStatut1(),
                    p.getIdStatut2());

            Map<String, String> statutMap = new HashMap<>();

            statutMap.put("Statut1", String.valueOf(p.getIdStatut1()));
            statutMap.put("Statut2", String.valueOf(p.getIdStatut2()));
            statutMap.put("DT", String.valueOf(totalDt));
            statutMap.put("Alerte", p.getAlerte());

            demandeStatutList.add(statutMap);
        }

        return demandeStatutList;
    }

    @GetMapping("/alertes/{idDemande}/resume")
    public Map<String, String> getAlerteResume(@PathVariable("idDemande") int idDemande) {
        Map<String, String> resume = new HashMap<>();

        List<DemandeStatut> demandeStatut
                = demandeStatutService.getAllDemandeStatutById(idDemande);

        if (demandeStatut.isEmpty()) {
            resume.put("isTermine", "false");
            resume.put("totalDt", "0.0");
            return resume;
        }

        DemandeStatut latestStatut = demandeStatut.get(demandeStatut.size() - 1);
        String sigle = latestStatut.getStatut() == null
                ? ""
                : latestStatut.getStatut().getSigle();

        resume.put("sigle", sigle);
        resume.put("nomStatut", latestStatut.getStatut() == null
                ? ""
                : latestStatut.getStatut().getNomStatut());
        resume.put("isTermine", String.valueOf(Statut.SIGLE_FORAGE_TERMINES.equals(sigle)));
        resume.put("totalDt", String.valueOf(calculateTotalDt(demandeStatut)));

        return resume;
    }

    private double calculateDtBetweenStatuses(
            List<DemandeStatut> demandeStatut,
            int idStatut1,
            int idStatut2) {

        if (idStatut1 > idStatut2) {
            return 0.0;
        }

        double totalDt = 0.0;
        boolean started = false;

        for (DemandeStatut ds : demandeStatut) {

            int currentStatutId = ds.getStatut().getId();

            if (!started) {

                if (currentStatutId >= idStatut1
                        && currentStatutId <= idStatut2) {

                    started = true;
                    totalDt += ds.getDt();

                    if (currentStatutId == idStatut2) {
                        return totalDt;
                    }
                }

            } else {

                if (currentStatutId <= idStatut2) {
                    totalDt += ds.getDt();
                }

                if (currentStatutId == idStatut2) {
                    return totalDt;
                }

                if (currentStatutId > idStatut2) {
                    break;
                }
            }
        }

        return 0.0;
    }

    private double calculateTotalDt(List<DemandeStatut> demandeStatut) {
        double totalDt = 0.0;

        for (DemandeStatut ds : demandeStatut) {
            if (ds.getDt() != null) {
                totalDt += ds.getDt();
            }
        }

        return totalDt;
    }
}
