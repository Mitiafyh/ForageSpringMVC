package com.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Demande;
import com.app.entities.Statut;
import com.app.entities.TypeDevis;
import com.app.entities.Utilitaire;
import com.app.repositories.DemandeRepository;
import com.app.repositories.DemandeStatutRepository;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DemandeStatutRepository demandeStatutRepository;
    
    @Autowired
    private Utilitaire utils;

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    public Demande getDemandeById(int id) {
        return demandeRepository.findById(id).orElse(null);
    }

    public Demande saveDemande(Demande demande) {
        return demandeRepository.save(demande);
    }

    public void deleteDemande(int id) {
        demandeRepository.deleteById(id);
    }

    public List<TypeDevis> getCreatableTypesForDemande(Demande demande) {
        Statut currentStatut = demandeStatutRepository.findTopByDemandeIdOrderByIdDesc(demande.getId()).getStatut();

        List<TypeDevis> typesDevis = new ArrayList<TypeDevis>();

        typesDevis.add(utils.getTypeDevisById(TypeDevis.ID_ETUDE));

        Statut statutDevisForageCree = utils.getStatutBySigle(Statut.SIGLE_DEVIS_FORAGE_CREE);
        if (currentStatut.getId() >= statutDevisForageCree.getId()) {
            typesDevis.add(utils.getTypeDevisById(TypeDevis.ID_FORAGE));
        }

        return typesDevis;
    }
}
