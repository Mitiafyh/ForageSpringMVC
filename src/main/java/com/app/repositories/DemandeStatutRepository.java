package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.DemandeStatut;

public interface  DemandeStatutRepository extends JpaRepository<DemandeStatut, Integer> {
    DemandeStatut findTopByDemandeIdOrderByIdDesc(int idDemande);

    @Query("SELECT ds FROM DemandeStatut ds WHERE ds.demande.id = :idDemande ORDER BY ds.date ASC, ds.id ASC")
    List<DemandeStatut> findAllByDemandeIdOrderByDateAscIdAsc(@Param("idDemande") int idDemande);

    @Query("SELECT ds FROM DemandeStatut ds WHERE ds.demande.id = :idDemande")
    List<DemandeStatut> getAllByIdDemande(@Param("idDemande") int idDemande);
}
