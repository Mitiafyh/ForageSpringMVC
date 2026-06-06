package com.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Client;

public interface  ClientRepository extends JpaRepository<Client, Integer> {
    
}
