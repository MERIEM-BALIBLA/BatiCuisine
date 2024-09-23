import entity.Composant;
import entity.MainOeuvre;
import entity.Materiau;
import entity.Projet;
import view.ClientAffichage;
import view.Menu;
import view.ProjetAffichage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
//        ClientAffichage clientService = new ClientAffichage();
//
//        ProjetAffichage projetAffichage = new ProjetAffichage();
//
//        clientService.gererClient();

        menu.afficherMenuPrincipal();

    }

    private static void afficherInfosProjet(Projet projet) {
        System.out.println("\n--- Informations du Projet ---");
        System.out.println("Nom du projet : " + projet.getNom_Projet());
        System.out.println("Composants du projet :");

        for (Composant composant : projet.getComposants()) {
            if (composant instanceof Materiau) {
                Materiau materiau = (Materiau) composant;
                System.out.println("- Matériau : " + materiau.getNom() +
                        ", Coût unitaire : " + materiau.getCoutUnitaire() +
                        ", Quantité : " + materiau.getQuantite());
            } else if (composant instanceof MainOeuvre) {
                MainOeuvre mainOeuvre = (MainOeuvre) composant;
                System.out.println("- Main d'œuvre : " + mainOeuvre.getNom() +
                        ", Taux horaire : " + mainOeuvre.getTauxHoraire() +
                        ", Heures de travail : " + mainOeuvre.getHeuresTravail());
            }
        }
        System.out.println("------------------------------\n");
    }

}
