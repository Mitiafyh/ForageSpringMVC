package com.app.services;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.DemandeStatut;
import com.app.repositories.DemandeStatutRepository;

@Service
public class DemandeStatutService {

    @Autowired
    private DemandeStatutRepository demandeStatutRepository;

    public void saveDemandeStatut(DemandeStatut ds) {
        demandeStatutRepository.save(ds);
        recalculateDtForDemande(ds.getDemande().getId());
    }

    public DemandeStatut updateDemandeStatut(DemandeStatut ds) {
        DemandeStatut saved = demandeStatutRepository.save(ds);
        recalculateDtForDemande(saved.getDemande().getId());
        return saved;
    }

    public List<DemandeStatut> getAllDemandeStatuts() {
        return demandeStatutRepository.findAll();
    }

    public DemandeStatut getDemandeStatutById(int id) {
        return demandeStatutRepository.findById(id).orElse(null);
    }

    public void recalculateDtForDemande(int idDemande) {
        List<DemandeStatut> demandeStatuts = demandeStatutRepository
                .findAllByDemandeIdOrderByDateAscIdAsc(idDemande);

        DemandeStatut previous = null;
        for (DemandeStatut current : demandeStatuts) {
            if (previous == null || previous.getDate() == null || current.getDate() == null) {
                current.setDt(0.0);
            } else {
                current.setDt(calculateBusinessMinutes(previous.getDate(), current.getDate()));
            }
            previous = current;
        }

        demandeStatutRepository.saveAll(demandeStatuts);
    }
    public List<DemandeStatut> getAllDemandeStatutById(int id){
        return demandeStatutRepository.findAllByDemandeIdOrderByDateAscIdAsc(id);
    }

    public DemandeStatut getFirstStatutForDemande(int idDemande) {
        List<DemandeStatut> demandeStatuts = demandeStatutRepository.findAllByDemandeIdOrderByDateAscIdAsc(idDemande);
        if (demandeStatuts.isEmpty()) {
            return null;
        }
        return demandeStatuts.get(0);
    }

    public DemandeStatut getLatestStatutForDemande(int idDemande) {
        List<DemandeStatut> demandeStatuts = demandeStatutRepository.findAllByDemandeIdOrderByDateAscIdAsc(idDemande);
        if (demandeStatuts.isEmpty()) {
            return null;
        }
        return demandeStatuts.get(demandeStatuts.size() - 1);
    }

    private double calculateBusinessMinutes(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            return 0.0;
        }

        double minutes = 0.0;
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek day = date.getDayOfWeek();
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                continue;
            }
            LocalDateTime businessStart = date.atTime(8, 0);
            LocalDateTime businessEnd = date.atTime(16, 0);

            LocalDateTime dayStart
                    = date.equals(startDate) ? start : businessStart;

            LocalDateTime dayEnd
                    = date.equals(endDate) ? end : businessEnd;

            if (dayStart.isBefore(businessStart)) {
                dayStart = businessStart;
            }

            if (dayEnd.isAfter(businessEnd)) {
                dayEnd = businessEnd;
            }

            if (dayEnd.isAfter(dayStart)) {
                minutes += Duration.between(dayStart, dayEnd).toMinutes();
            }
        }

        return minutes;
    }

}
