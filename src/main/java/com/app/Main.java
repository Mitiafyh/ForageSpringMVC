package com.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.app.config.ApplicationConfig;
import com.app.services.ClientService;

public class Main {
    public static void main(String[] args) {
        // 1. On charge le contexte
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ClientService clientService = context.getBean(ClientService.class);
        System.out.println("Liste des clients :");
        clientService.getAllClients().forEach(client -> System.out.println(client.getNom()));
    }
}