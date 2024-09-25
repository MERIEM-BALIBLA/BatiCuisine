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
        LocalDate today = LocalDate.now();

        // Validation de la date d'émission
        while (dateEmission == null) {
            System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
            String dateEmissionStr = scanner.next();
            try {
                dateEmission = LocalDate.parse(dateEmissionStr, formatter);
                if (dateEmission.isBefore(today)) {
                    System.out.println("La date d'émission doit être aujourd'hui ou une date future. Veuillez réessayer.");
                    dateEmission = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Date invalide, veuillez réessayer.");
            }
        }

        // Validation de la date de validité (sans condition par rapport à la date d'émission)
        while (dateValidite == null) {
            System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
            String dateValiditeStr = scanner.next();
            try {
                dateValidite = LocalDate.parse(dateValiditeStr, formatter);
                // Pas de vérification ici
            } catch (DateTimeParseException e) {
                System.out.println("Date invalide, veuillez réessayer.");
            }
        }

        Devis devis = new Devis();
        devis.setMontantEstime(projet.getCout_Total());
        devis.setDateEmission(dateEmission);
        devis.setDateValidite(dateValidite);
        devis.setProjet(projet);

        System.out.print("Souhaitez-vous accepter le devis ? (y/n) : ");
        String acceptation = scanner.next();
        devis.setAccepte(acceptation.equalsIgnoreCase("y"));

        if (devis.isAccepte()) {
            System.out.println("Devis enregistré avec succès !");
        } else {
            System.out.println("Le devis n'a pas été accepté et ne sera pas enregistré.");
        }

        devisService.ajouterDevis(devis);

        return devis;
    }

}
