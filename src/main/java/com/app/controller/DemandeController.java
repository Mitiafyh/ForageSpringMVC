package com.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.app.entities.Client;
import com.app.entities.Commune;
import com.app.entities.Demande;
import com.app.entities.DemandeStatut;
import com.app.entities.Statut;
import com.app.entities.TypeDevis;
import com.app.entities.User;
import com.app.services.ClientService;
import com.app.services.CommuneService;
import com.app.services.DemandeService;
import com.app.services.DemandeStatutService;
import com.app.services.DistrictService;
import com.app.services.RegionService;
import com.app.services.StatutService;
import com.app.services.UserService;

@Controller
public class DemandeController {

    private static final DateTimeFormatter DATETIME_LOCAL_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Autowired
    private ClientService clientService;
    @Autowired
    private CommuneService communeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private StatutService statutService;
    @Autowired
    private DemandeStatutService demandeStatutService;
    @Autowired
    private UserService userService;

    @GetMapping("/demandes")
    public String listDemandes(Model model) {
        model.addAttribute("demandes", demandeService.getAllDemandes());

        Map<Integer, String> communeNames = new HashMap<>();
        for (Commune commune : communeService.getAllCommunes()) {
            communeNames.put(commune.getId(), commune.getNomCommune());
        }
        model.addAttribute("communeNames", communeNames);

        return "demandes";
    }

    @GetMapping("/demandes/new")
    public String newDemande(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("regions", regionService.getAllRegions());
        return "insertionDemande";
    }

    @PostMapping("/insertdemande")
    public String insertDemande(@RequestParam("idClient") int idClient,
            @RequestParam("communeId") int communeId,
            @RequestParam("lieu") String lieu,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            Model model) {
        Client client = clientService.getClientById(idClient);
        if (client == null || communeId == 0) {
            model.addAttribute("error", "Client ou commune introuvable");
            model.addAttribute("clients", clientService.getAllClients());
            model.addAttribute("regions", regionService.getAllRegions());
            return "insertionDemande";
        }

        Demande demande = new Demande();
        demande.setClient(client);
        demande.setIdCommune(communeId);
        demande.setLieu(lieu);
        demandeService.saveDemande(demande);

        Statut statut = statutService.getStatutById(1);
        User user = userService.getAllUsers().get(0);
        DemandeStatut demandeStatut = new DemandeStatut();
        demandeStatut.setDemande(demande);
        demandeStatut.setStatut(statut);
        demandeStatut.setDate(date);
        demandeStatut.setUser(user);
        demandeStatut.setObservation("Demande initiale");
        demandeStatutService.saveDemandeStatut(demandeStatut);

        return "redirect:/demandes";
    }

    @GetMapping("/demandes/edit/{id}")
    public String editDemande(@PathVariable("id") int id, Model model) {
        Demande demande = demandeService.getDemandeById(id);
        if (demande == null) {
            return "redirect:/demandes";
        }

        Commune commune = communeService.getCommuneById(demande.getIdCommune());

        model.addAttribute("demande", demande);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("regions", regionService.getAllRegions());
        DemandeStatut demandeStatut = demandeStatutService.getFirstStatutForDemande(id);
        model.addAttribute("dateValue", demandeStatut == null || demandeStatut.getDate() == null
            ? ""
            : demandeStatut.getDate().format(DATETIME_LOCAL_FORMATTER));
        if (commune != null) {
            model.addAttribute("selectedCommune", commune);
            model.addAttribute("selectedDistrict", commune.getDistrict());
            model.addAttribute("selectedRegion", commune.getDistrict().getRegion());
            model.addAttribute("districts", districtService.findDistrictsByRegion(commune.getDistrict().getRegion().getId()));
            model.addAttribute("communes", communeService.findCommunesByDistrict(commune.getDistrict().getId()));
        }

        return "editDemande";
    }

    @PostMapping("/demandes/update")
    public String updateDemande(@RequestParam("id") int id,
            @RequestParam("idClient") int idClient,
            @RequestParam("communeId") int communeId,
            @RequestParam("lieu") String lieu,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            Model model) {
        Demande demande = demandeService.getDemandeById(id);
        Client client = clientService.getClientById(idClient);

        if (demande == null || client == null || communeId == 0) {
            model.addAttribute("error", "Demande, client ou commune introuvable");
            model.addAttribute("clients", clientService.getAllClients());
            if (demande != null) {
                model.addAttribute("demande", demande);
            }
            return "editDemande";
        }

        demande.setClient(client);
        demande.setIdCommune(communeId);
        demande.setLieu(lieu);
        demandeService.saveDemande(demande);

        DemandeStatut demandeStatut = demandeStatutService.getFirstStatutForDemande(id);
        if (demandeStatut != null) {
            demandeStatut.setDate(date);
            demandeStatutService.updateDemandeStatut(demandeStatut);
        }

        return "redirect:/demandes";
    }

    @GetMapping("/demandes/delete/{id}")
    public String deleteDemande(@PathVariable("id") int id) {
        demandeService.deleteDemande(id);
        return "redirect:/demandes";
    }

    @GetMapping("/demandes/detail/{id}")
    @ResponseBody
    public Map<String, Object> detailDemande(@PathVariable("id") int id) {
        Demande demande = demandeService.getDemandeById(id);
        Map<String, Object> response = new HashMap<>();
        if (demande == null) {
            return response;
        }

        String commune = communeService.getCommuneById(demande.getIdCommune()).getNomCommune();
        String clientName = demande.getClient().getNom();
        String lieu = demande.getLieu();

        DemandeStatut latestStatut = demandeStatutService.getLatestStatutForDemande(id);
        String statutActuel = latestStatut.getStatut().getNomStatut();
        String dateStatut = latestStatut.getDate() == null
            ? ""
            : latestStatut.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String sigle = latestStatut.getStatut().getSigle();
        List<TypeDevis> typesDevis = demandeService.getCreatableTypesForDemande(demande);
        List<Map<String, Object>> types = new ArrayList<>();

        for (TypeDevis td : typesDevis) {

            Map<String, Object> map = new HashMap<>();

            map.put("id", td.getId());
            map.put("nomType", td.getNomType());

            types.add(map);
        }

        response.put("typesDevis", types);

        response.put("client", clientName);
        response.put("lieu", lieu);
        response.put("commune", commune);
        response.put("statut", statutActuel);
        response.put("dateStatut", dateStatut);
        response.put("sigle", sigle);
        return response;

    }

}
