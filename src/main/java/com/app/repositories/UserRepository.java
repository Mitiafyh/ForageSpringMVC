package com.app.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;


public interface  UserRepository extends JpaRepository<User, Integer>{
    public User findByNomAndMdp(String nom, String mdp);
    public User findById(int id);
}
