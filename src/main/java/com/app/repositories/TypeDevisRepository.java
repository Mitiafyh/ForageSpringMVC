package com.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.app.entities.Statut;
import com.app.entities.TypeDevis;
import com.app.repositories.StatutRepository;
import com.app.repositories.TypeDevisRepository;


public interface TypeDevisRepository extends JpaRepository<TypeDevis, Integer>{ 
       
}
