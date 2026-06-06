package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.User;
import com.app.repositories.UserRepository;

@Service
public class UserService {
     @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByNomAndMdp(String nom, String mdp) {
        return userRepository.findByNomAndMdp(nom, mdp);
    }

    public User getUserById(int id){
        return userRepository.findById(id);

    }

}
