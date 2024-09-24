package view;

import entity.Devis;
import entity.Projet;
import services.DevisService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DevisAffichage {
    private static Scanner scanner = new Scanner(System.in);
    private DevisService devisService = new DevisService();
    public Devis devisInputs(Projet projet) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateEmission = null;
        LocalDate dateValidite = null;

        try {
            System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
            String dateEmissionStr = scanner.next();
            dateEmission = LocalDate.parse(dateEmissionStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Erreur de format de date pour la date d'émission.");
            return null;
        }

        try {
            System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
            String dateValiditeStr = scanner.next();
            dateValidite = LocalDate.parse(dateValiditeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Erreur de format de date pour la date de validité.");
            return null;
        }

        Devis devis = new Devis();
        devis.setMontantEstime(projet.getCout_Total());
        devis.setDateEmission(dateEmission);
        devis.setDateValidite(dateValidite);
        devis.setProjet(projet);

        System.out.print("Souhaitez-vous accepter le devis ? (y/n) : ");
        String acceptation = scanner.next();
        if (acceptation.equalsIgnoreCase("y")) {
            devis.setAccepte(true);
            System.out.println("Devis enregistré avec succès !");
        } else {
            System.out.println("Le devis n'a pas été accepté et ne sera pas enregistré.");
            devis.setAccepte(false);
        }

        devisService.ajouterDevis(devis);

        return devis;
    }

/*
    public Devis devisInputs(Projet projet) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateEmission = null;
        LocalDate dateValidite = null;

        try {
            System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
            String dateEmissionStr = scanner.next();
            dateEmission = LocalDate.parse(dateEmissionStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Erreur de format de date pour la date d'émission.");
            return null;
        }

        try {
            System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
            String dateValiditeStr = scanner.next();
            dateValidite = LocalDate.parse(dateValiditeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Erreur de format de date pour la date de validité.");
            return null;
        }

        Devis devis = new Devis();
        System.out.println("Coût total du projet : " + projet.getCout_Total());
        devis.setMontantEstime(projet.getCout_Total());
        devis.setDateEmission(dateEmission);
        devis.setDateValidite(dateValidite);
        devis.setProjet(projet);
        devis.setMontantEstime(projet.getCout_Total());

        System.out.print("Souhaitez-vous accepter le devis ? (y/n) : ");
        String acceptation = scanner.next();
        if (acceptation.equalsIgnoreCase("y")) {
            devis.setAccepte(true);
            devisService.ajouterDevis(devis);
            System.out.println("Devis enregistré avec succès !");
            System.out.println(projet.getCout_Total());
            System.out.println(devis.getMontantEstime());
            return devis;
        } else {
            System.out.println("Le devis n'a pas été accepté et ne sera pas enregistré.");
            return null;
        }
    }
*/
}
