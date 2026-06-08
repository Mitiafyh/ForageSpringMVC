package com.app.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.entities.Demande;
import com.app.entities.DemandeStatut;
import com.app.entities.Devis;
import com.app.entities.SousDevis;
import com.app.entities.Statut;
import com.app.entities.User;
import com.app.services.ClientService;
import com.app.services.CommuneService;
import com.app.services.DemandeService;
import com.app.services.DemandeStatutService;
import com.app.services.DevisService;
import com.app.services.DistrictService;
import com.app.services.RegionService;
import com.app.services.SousDevisService;
import com.app.services.StatutService;
import com.app.services.UserService;

@Controller
public class DevisController {

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
    @Autowired
    private DevisService devisService;
   
    @Autowired
    private SousDevisService sousDevisService;
 

   @GetMapping("/devis/insert")
    public String showCreateDevisForm(Model model) {
        model.addAttribute("demandes", demandeService.getAllDemandes());
        return "createDevis";
    }

    @PostMapping("/devis/insert")
    public String insertDevis(
            @RequestParam("demande") int idDemande,
            @RequestParam(value = "TypeDevis", required = false) Integer idTypeDevis,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(value = "description", required = false) List<String> descriptions,
            @RequestParam(value = "prix", required = false) List<Double> prixs,
            @RequestParam(value = "quantite", required = false) List<Integer> quantites,
            Model model) {
        if (idTypeDevis == null) {
            model.addAttribute("error", "Veuillez choisir un type de devis.");
            model.addAttribute("demandes", demandeService.getAllDemandes());
            return "createDevis";
        }
        
        Devis devis = new Devis();
        Demande demande = demandeService.getDemandeById(idDemande);
        devis.setDemande(demande);
        devis.setIdTypeDevis(idTypeDevis);
        devisService.saveDevis(devis);
        
        

        
        if (descriptions != null && !descriptions.isEmpty()) {
            for (int i = 0; i < descriptions.size(); i++) {
                SousDevis sd = new SousDevis();
                sd.setDescription(descriptions.get(i));
                
                Double prixUnitaire = (prixs != null && i < prixs.size()) ? prixs.get(i) : 0.0;
                Integer qte = (quantites != null && i < quantites.size()) ? quantites.get(i) : 1;
                
                sd.setPrixUnitaire(prixUnitaire);
                sd.setQuantite(qte);
                sd.setDevis(devis);
                sousDevisService.saveSousDevis(sd);
                
            }
        }
        Statut statut = statutService.getStatutById(2);
        User user = userService.getUserById(1);
        
        DemandeStatut ds = new DemandeStatut();

        ds.setDemande(demande);

        ds.setStatut(statut);

        ds.setDate(date);

        ds.setUser(user);

        ds.setObservation("devis cree");

        demandeStatutService.saveDemandeStatut(ds);

        
        return "redirect:/devis/liste";
    }

    @GetMapping("/devis/liste")
    public String listDevis(Model model) {
        List<Devis> devisList = devisService.getAllDevis();
        model.addAttribute("devisList", devisList);
        return "listeDevis";
    }

    @GetMapping("/devis/edit/{id}")
    public String editDevis(@PathVariable("id") int id, Model model) {
        Devis devis = devisService.getDevisById(id);
        if (devis == null) {
            return "redirect:/devis/liste";
        }
        List<SousDevis> sousDevisList = sousDevisService.getSousDevisByDevisId(id);
        model.addAttribute("devis", devis);
        model.addAttribute("sousDevisList", sousDevisList);
        model.addAttribute("demandes", demandeService.getAllDemandes());
        DemandeStatut demandeStatut = demandeStatutService.getLatestStatutForDemande(devis.getDemande().getId());
        model.addAttribute("dateValue", demandeStatut == null || demandeStatut.getDate() == null
                ? ""
                : demandeStatut.getDate().format(DATETIME_LOCAL_FORMATTER));
        return "editDevis";
    }

    @PostMapping("/devis/update")
    public String updateDevis(@RequestParam("id") int id,
            @RequestParam("demande") int idDemande,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(value = "description", required = false) List<String> descriptions,
            @RequestParam(value = "prix", required = false) List<Double> prix,
            @RequestParam(value = "quantite", required = false) List<Integer> quantites) {
        Devis devis = devisService.getDevisById(id);
        if (devis == null) {
            return "redirect:/devis/liste";
        }
        Demande demande = demandeService.getDemandeById(idDemande);
        devis.setDemande(demande);
        devisService.saveDevis(devis);

        DemandeStatut demandeStatut = demandeStatutService.getLatestStatutForDemande(idDemande);
        if (demandeStatut != null) {
            demandeStatut.setDate(date);
            demandeStatutService.updateDemandeStatut(demandeStatut);
        }
        
        sousDevisService.deleteSousDevisByDevisId(id);
        
        if (descriptions != null && !descriptions.isEmpty()) {
            for (int i = 0; i < descriptions.size(); i++) {
                SousDevis sd = new SousDevis();
                sd.setDescription(descriptions.get(i));
                Double prixUnitaire = (prix != null && i < prix.size()) ? prix.get(i) : 0.0;
                Integer qte = (quantites != null && i < quantites.size()) ? quantites.get(i) : 1;
                sd.setPrixUnitaire(prixUnitaire);
                sd.setQuantite(qte);
                sd.setDevis(devis);
                sousDevisService.saveSousDevis(sd);
            }
        }
        return "redirect:/devis/liste";
    }


}
