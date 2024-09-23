package view;

import entity.Composant;
import entity.MainOeuvre;
import entity.Projet;
import repository.impl.MainoeuvreRepository;
import repository.impl.ProjetRepository;
import services.GestionnaireComposant;
import services.GestionnaireProjet;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private ClientAffichage clientAffichage = new ClientAffichage();
    private ProjetAffichage projetAffichage = new ProjetAffichage();

    public void afficherMenuPrincipal() {
        boolean status = true;
        GestionnaireComposant gestionnaireComposant = new GestionnaireComposant();
        GestionnaireProjet gestionnaireProjet = new GestionnaireProjet();
        ProjetAffichage projetAffichage = new ProjetAffichage();




//        double coutTotal = gestionnaireComposant.coutTotal(projetOP.get());
//        System.out.println("Coût total du projet : " + coutTotal);/*
        while (status) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt(); // Lire le choix de l'utilisateur

            switch (choix) {
                case 1:
                    clientAffichage.gererClient();
                    break;
                case 2:
                    projetAffichage.afficherProjets();
                    break;
                case 3:
                    projetAffichage.projetDetailsAvecCout();
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    status = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }

    }
}
