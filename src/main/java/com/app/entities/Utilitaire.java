package com.app.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.app.repositories.StatutRepository;
import com.app.repositories.TypeDevisRepository;

@Component
public class Utilitaire {

    private Map<String, Statut> statutsBySigle;
    private Map<Integer, TypeDevis> typesDevisById;

    private Map<Integer, String> siglesStatutsByIdTypeDevis;

    public Utilitaire(StatutRepository statutRepository, TypeDevisRepository typeDevisRepository) {
        List<Statut> statuts = statutRepository.findAll();
        List<TypeDevis> typesDevis = typeDevisRepository.findAll();

        statutsBySigle = new HashMap<>();
        for (Statut s : statuts) {
            statutsBySigle.put(s.getSigle(), s);
        }
        typesDevisById = new HashMap<>();
        for (TypeDevis td : typesDevis) {
            typesDevisById.put(td.getId(), td);
        }

        siglesStatutsByIdTypeDevis = new HashMap<>();
        siglesStatutsByIdTypeDevis.put(TypeDevis.ID_ETUDE, Statut.SIGLE_DEVIS_ETUDE_CREE);
        siglesStatutsByIdTypeDevis.put(TypeDevis.ID_FORAGE, Statut.SIGLE_DEVIS_FORAGE_CREE);

        
    }
     public Statut getStatutBySigle(String sigle) {
        return statutsBySigle.get(sigle);
    }

    public Statut getStatutByTypeDevis(TypeDevis typeDevis) {
        String sigle = siglesStatutsByIdTypeDevis.get(typeDevis.getId());
        return statutsBySigle.get(sigle);
    }

    public TypeDevis getTypeDevisById(Integer id) {
        return typesDevisById.get(id);
    }

    
}