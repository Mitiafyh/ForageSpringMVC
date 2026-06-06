package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.entities.User;
import com.app.services.DemandeService;
import com.app.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DemandeService demandeService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/auth")
        public String authentification(@RequestParam("nom") String nom,
            @RequestParam("pwd") String pwd,
            Model model) {

        User user = userService.findByNomAndMdp(nom, pwd);

        if (user != null) {
            model.addAttribute("demandes", demandeService.getAllDemandes());
            return "demandes";
        } else {
            model.addAttribute("error", "Identifiants incorrects");
            return "login";
        }
    }
}
