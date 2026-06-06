package com.app.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.entities.Demande;
import com.app.entities.DemandeStatut;
import com.app.entities.Statut;
import com.app.entities.User;
import com.app.services.DemandeService;
import com.app.services.DemandeStatutService;
import com.app.services.StatutService;
import com.app.services.UserService;

@Controller
public class DemandeStatutController {

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private StatutService statutService;
    @Autowired
    private DemandeStatutService demandeStatutService;
    @Autowired
    private UserService userService;

    @GetMapping("/demandes/changeStatutForm")
    public String changeStatutForm(Model model) {
        model.addAttribute("demandes", demandeService.getAllDemandes());
        model.addAttribute("statuts", statutService.getAllStatuts());
        return "changeStatut";
    }

    @GetMapping("/demandes/statut/{id}")
    @ResponseBody
    public Map<String, Object> getStatut(@PathVariable("id") int id) {
        Demande demande = demandeService.getDemandeById(id);
        Map<String, Object> response = new HashMap<>();
        if (demande == null) {
            return response;
        }
        DemandeStatut latestStatut = demandeStatutService.getLatestStatutForDemande(id);

        response.put("statut", latestStatut.getStatut().getId());
        return response;
    }

    @PostMapping("/demandes/changeStatut")
    public String changeStatut(@RequestParam("demande") int idDemande,
            @RequestParam("observation") String observation,
            @RequestParam("statut") int idStatut,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            Model model) {
        Demande demande = demandeService.getDemandeById(idDemande);
        Statut statut = statutService.getStatutById(idStatut);
        User user = userService.getUserById(1);
        DemandeStatut ds = new DemandeStatut(0, demande, statut, date, user, observation);
        demandeStatutService.saveDemandeStatut(ds);

        return "redirect:/demandes";
    }

}
