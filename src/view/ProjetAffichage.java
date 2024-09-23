package view;

import entity.*;
import entity.enums.EtatProjet;
import services.GestionnaireComposant;
import services.GestionnaireProjet;
import view.ComposantAffichage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProjetAffichage {

    Scanner scanner = new Scanner(System.in);

    GestionnaireProjet gestionnaireProjet = new GestionnaireProjet();

    ComposantAffichage composantAffichage = new ComposantAffichage();

    GestionnaireComposant gestionnaireComposant = new GestionnaireComposant();

    public void projetAffichage(Projet projet) {
        System.out.println("projet" + projet.getNom_Projet());
        System.out.println("MARGE" + projet.getMarge_Beneficiaire());
        for (Composant composant : projet.getComposants()) {
            System.out.println("tva " + composant.getTauxTVA());
        }
        composantAffichage.coutTotal(projet);
    }

    public Projet ajouterProjet(Client client) {
        Projet projet = new Projet();
        if (client == null || client.getId() <= 0) {
            System.out.println("Erreur : Aucun client valide sélectionné. Annulation de la création du projet.");
            return null;
        }
        projet.setClient(client); // Assurez-vous que le client est associé au projet
        projet.setEtat_Projet(EtatProjet.En_cours); // Définir l'état par défaut

        System.out.print("Nom du projet : ");
        String nom = scanner.nextLine();
        projet.setNom_Projet(nom);

        List<Materiau> materiaux = composantAffichage.ajouterMateriaux();
        List<MainOeuvre> mainOeuvres = composantAffichage.ajouterMainOeuvre();

        System.out.print("Entrez le taux de TVA du projet : ");
        Double tva = scanner.nextDouble();
        materiaux.forEach(component -> component.setTauxTVA(tva));
        mainOeuvres.forEach(component -> component.setTauxTVA(tva));

        System.out.print("Ajoutez la marge bénéficiaire au projet : ");
        Double margeBeneficiaire = scanner.nextDouble();
        projet.setMarge_Beneficiaire(margeBeneficiaire);

        // Vérification des valeurs
        if (projet.getNom_Projet() == null || margeBeneficiaire == null) {
            throw new IllegalArgumentException("Le nom du projet ou la marge bénéficiaire ne peut pas être nul.");
        }

        List<Composant> composants = new ArrayList<>();
        composants.addAll(materiaux);
        composants.addAll(mainOeuvres);
        projet.setComposants(composants);

        if (projet.getClient() == null || projet.getClient().getId() <= 0) {
            throw new IllegalArgumentException("Le client du projet est invalide.");
        }
        if (projet.getNom_Projet() == null) {
            throw new IllegalArgumentException("Le nom du projet ne peut pas être null.");
        }
        if (projet.getMarge_Beneficiaire() == 0) {
            throw new IllegalArgumentException("La marge bénéficiaire ne peut pas être null.");
        }
        if (projet.getEtat_Projet() == null) {
            throw new IllegalArgumentException("L'état du projet ne peut pas être null.");
        }

        // Insérer le projet dans la base de données
        projet = gestionnaireProjet.ajouterProjet(projet);

        if (projet != null) {
            System.out.println("Projet ajouté avec succès !");
            System.out.println("Nom du projet : " + projet.getNom_Projet());
            System.out.println("Marge bénéficiaire : " + projet.getMarge_Beneficiaire());
        } else {
            System.out.println("Erreur lors de l'ajout du projet.");
        }
        return projet;
    }

    public void afficherProjets() {
        List<Projet> projets = gestionnaireProjet.projetListe();
        if (projets.isEmpty()) {
            System.out.println("Aucun projet trouvé.");
        } else {
            for (Projet projet : projets) {
                System.out.println("ID: " + projet.getId() +
                        ", Nom du Projet: " + projet.getNom_Projet() +
                        ", Marge Bénéficiaire: " + projet.getMarge_Beneficiaire() +
                        ", État: " + projet.getEtat_Projet() +
                        ", Client: " + projet.getClient().getNom());
            }
        }
    }

    public void projetDetailsAvecCout() {
        System.out.print("Projet ID : ");
        int id = scanner.nextInt();

        Optional<Projet> projetOp = gestionnaireProjet.rechercheProjet(id);

        if (projetOp.isPresent()) {
            Projet projet = projetOp.get();

            // Affichage des détails du projet
            System.out.println("--- Détails du Projet ---");
            System.out.println("ID : " + projet.getId());
            System.out.println("Nom du projet : " + projet.getNom_Projet());
            System.out.println("Client : " + (projet.getClient() != null ? projet.getClient().toString() : "Aucun client associé"));
            System.out.println("Marge bénéficiaire : " + projet.getMarge_Beneficiaire());
            System.out.println("État du projet : " + projet.getEtat_Projet());

            // Récupération et affichage des composants
            List<Composant> composants = gestionnaireComposant.composantList(projet);
            if (composants.isEmpty()) {
                System.out.println("Aucun composant associé à ce projet.");
            } else {
                System.out.println("--- Détails des Composants ---");
                composants.forEach(System.out::println);
            }

            // Calcul et affichage du coût total
//            double coutTotal = projet.getCout_Total()
            double coutTotal = gestionnaireProjet.coutTotal(projet);
            projet.setCout_Total(coutTotal);

            System.out.println("Coût total du projet : " + projet.getCout_Total());
        } else {
            System.out.println("Aucun projet trouvé avec l'ID " + id);
        }
    }


/*
    public void projetDetailsAvecCout() {
        System.out.print("Projet ID : ");
        int id = scanner.nextInt();

        Optional<Projet> projetOp = gestionnaireProjet.rechercheProjet(id);

        if (projetOp.isPresent()) {
            Projet projet = projetOp.get();

            System.out.println(projet);

            List<Composant> composants = gestionnaireComposant.composantList(projet);
            if (composants.isEmpty()) {
                System.out.println("Aucun composant associé à ce projet.");
            } else {
                System.out.println("--- Détails des Composants ---");
                composants.forEach(System.out::println);
            }

            double coutTotal = gestionnaireProjet.coutTotal(projet);
            System.out.println("Coût total du projet : " + coutTotal);
        } else {
            System.out.println("Aucun projet trouvé avec l'ID " + id);
        }
    }
*/


}
