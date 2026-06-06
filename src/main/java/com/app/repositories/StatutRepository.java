package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Statut;

public interface StatutRepository extends JpaRepository<Statut, Integer>{
}
