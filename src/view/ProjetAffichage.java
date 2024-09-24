package view;

import entity.*;
import entity.enums.EtatProjet;
import services.GestionnaireComposant;
import services.GestionnaireProjet;
import util.InputValidation;
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
    InputValidation inputValidation = new InputValidation();
    DevisAffichage devisAffichage = new DevisAffichage();


//    public void projetAffichage(Projet projet) {
//        System.out.println("projet" + projet.getNom_Projet());
//        System.out.println("MARGE" + projet.getMarge_Beneficiaire());
//        for (Composant composant : projet.getComposants()) {
//            System.out.println("tva " + composant.getTauxTVA());
//        }
//        composantAffichage.coutTotal(projet);
//    }

    public void afficherComposants(Projet projet) {
        List<Composant> composants = gestionnaireComposant.composantList(projet);
        System.out.println("--- Composants ---");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-20s %-20s %-15s %-15s %-15s %-15s%n",
                "Type", "Nom", "Coût Transport", "Coût Unitaire", "Quantité", "Coefficient Qualité");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");

        List<Materiau> materiauxList = gestionnaireComposant.materiauxList(projet);
        for (Materiau mat : materiauxList) {
            System.out.printf("%-20s %-20s %-15.2f %-15.2f %-15.2f %-15.2f%n",
                    "Matériau", mat.getNom(),
                    mat.getCoutTransport(),
                    mat.getCoutUnitaire(),
                    mat.getQuantite(),
                    mat.getCoefficientQualite());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-20s %-15s %-15s %-15s%n",
                "Type", "Nom", "Heures de Travail", "Taux Horaire", "Productivité");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        List<MainOeuvre> mainOeuvreList = gestionnaireComposant.mainOeuvreList(projet);
        for (MainOeuvre main : mainOeuvreList) {
            System.out.printf("%-20s %-20s %-15.2f %-15.2f %-15.2f%n",
                    "Main-d'œuvre", main.getNom(),
                    main.getHeuresTravail(),
                    main.getTauxHoraire(),
                    main.getProductiviteOuvrier());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }


    public void projetDetailsAvecCout(int id) {
        Optional<Projet> projetOp = gestionnaireProjet.rechercheProjet(id);
        if (projetOp.isPresent()) {
            Projet projet = projetOp.get();

            System.out.println("--- Détails du Projet ---");
            System.out.println("ID : " + projet.getId());
            System.out.println("Nom du projet : " + projet.getNom_Projet());
            System.out.println("Client : " + (projet.getClient() != null ? projet.getClient().toString() : "Aucun client associé"));
            System.out.println("Marge bénéficiaire : " + projet.getMarge_Beneficiaire());
            System.out.println("État du projet : " + projet.getEtat_Projet());

            afficherComposants(projet);

            double coutMateriau = gestionnaireComposant.coutMateriaux(projet);
            double coutMainoeuvre = gestionnaireComposant.coutMainOeuvre(projet);
            System.out.printf("%-30s : %s%.2f%s%n", "Coût matériaux", "\u001B[31m", coutMateriau, "\u001B[0m");
            System.out.printf("%-30s : %s%.2f%s%n", "Coût main-d'œuvre", "\u001B[31m", coutMainoeuvre, "\u001B[0m");

            double coutTotal = gestionnaireProjet.coutTotal(projet);
            projet.setCout_Total(coutTotal);
            System.out.printf("%-30s : %s%.2f%s%n", "Coût total du projet", "\u001B[31m", coutTotal, "\u001B[0m");
        } else {
            System.out.println("Aucun projet trouvé avec l'ID " + id);
        }
    }
/*
    public Projet ajouterProjet(Client client) {
        Projet projet = new Projet();
        if (client == null || client.getId() <= 0) {
            System.out.println("Erreur : Aucun client valide sélectionné. Annulation de la création du projet.");
            return null;
        }

        projet.setClient(client);
        projet.setEtat_Projet(EtatProjet.En_cours);

        System.out.print("Nom du projet : ");
        String nom = inputValidation.validateNonEmptyString(scanner);
        projet.setNom_Projet(nom);

        // Ajout des matériaux
        List<Materiau> materiaux = composantAffichage.ajouterMateriaux();
        // Ajout de la main d'œuvre
        List<MainOeuvre> mainOeuvres = composantAffichage.ajouterMainOeuvre();

        double coutTotal = 0.0;

        // Calculer le coût des matériaux
        for (Materiau mat : materiaux) {
            double coutMateriau = mat.calculerCoutMateriau();
            coutTotal += coutMateriau;
            projet.addComposant(mat); // Ajoutez le matériau au projet
        }

        // Calculer le coût de la main d'œuvre
        for (MainOeuvre main : mainOeuvres) {
            double coutMainOeuvre = main.calculerCoutMainOeuvre();
            coutTotal += coutMainOeuvre;
            projet.addComposant(main); // Ajoutez la main d'œuvre au projet
        }

        projet.setCout_Total(coutTotal); // Mettre à jour le coût total

        // Suite de votre code...
        System.out.print("Entrez le taux de TVA du projet : ");
        Double tva = InputValidation.validateDoubleInput(scanner);
        materiaux.forEach(component -> component.setTauxTVA(tva));
        mainOeuvres.forEach(component -> component.setTauxTVA(tva));

        System.out.print("Ajoutez la marge bénéficiaire au projet : ");
        Double margeBeneficiaire = InputValidation.validateDoubleInput(scanner);
        projet.setMarge_Beneficiaire(margeBeneficiaire);

        // Vérifications...
        if (projet.getNom_Projet() == null || margeBeneficiaire == null) {
            throw new IllegalArgumentException("Le nom du projet ou la marge bénéficiaire ne peut pas être nul.");
        }

        projet = gestionnaireProjet.ajouterProjet(projet);

        // Suite de votre code...
        if (projet != null) {
            System.out.println("Projet ajouté avec succès !");
            System.out.println("Nom du projet : " + projet.getNom_Projet());
            System.out.println("Marge bénéficiaire : " + projet.getMarge_Beneficiaire());
        } else {
            System.out.println("Erreur lors de l'ajout du projet.");
        }

        assert projet != null;
        projetDetailsAvecCout(projet.getId());
        devisAffichage.devisInputs(projet);

        return projet;
    }
*/

    public Projet ajouterProjet(Client client) {
        Projet projet = new Projet();
        if (client == null || client.getId() <= 0) {
            System.out.println("Erreur : Aucun client valide sélectionné. Annulation de la création du projet.");
            return null;
        }

        projet.setClient(client);
        projet.setEtat_Projet(EtatProjet.En_cours);

        System.out.print("Nom du projet : ");
        String nom = inputValidation.validateNonEmptyString(scanner);
        projet.setNom_Projet(nom);

        List<Materiau> materiaux = composantAffichage.ajouterMateriaux();
        List<MainOeuvre> mainOeuvres = composantAffichage.ajouterMainOeuvre();

        // Configurer la TVA
        System.out.print("Entrez le taux de TVA du projet : ");
        Double tva = InputValidation.validateDoubleInput(scanner);
        materiaux.forEach(component -> component.setTauxTVA(tva));
        mainOeuvres.forEach(component -> component.setTauxTVA(tva));

        // Configurer la marge bénéficiaire
        System.out.print("Ajoutez la marge bénéficiaire au projet : ");
        Double margeBeneficiaire = InputValidation.validateDoubleInput(scanner);
        projet.setMarge_Beneficiaire(margeBeneficiaire);

        // Vérifications des valeurs
        if (projet.getNom_Projet() == null || margeBeneficiaire == null) {
            throw new IllegalArgumentException("Le nom du projet ou la marge bénéficiaire ne peut pas être nul.");
        }

        // Ajouter les composants au projet
        List<Composant> composants = new ArrayList<>();
        composants.addAll(materiaux);
        composants.addAll(mainOeuvres);
        projet.setComposants(composants);

        // Calculer le coût total
        double coutMateriaux = materiaux.stream().mapToDouble(Materiau::calculerCoutMateriau).sum();
        double coutMainOeuvre = mainOeuvres.stream().mapToDouble(MainOeuvre::calculerCoutMainOeuvre).sum();
        double coutTotal = coutMateriaux + coutMainOeuvre;

        projet.setCout_Total(coutTotal); // Mettre à jour le coût total du projet

        // Vérification des valeurs
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

        projet = gestionnaireProjet.ajouterProjet(projet); // Ajoutez le projet au gestionnaire

        // Afficher les informations du projet
        if (projet != null) {
            System.out.println("Projet ajouté avec succès !");
            System.out.println("Nom du projet : " + projet.getNom_Projet());
            System.out.println("Marge bénéficiaire : " + projet.getMarge_Beneficiaire());
            System.out.println("Coût total du projet : " + projet.getCout_Total()); // Afficher le coût total
        } else {
            System.out.println("Erreur lors de l'ajout du projet.");
        }

        assert projet != null;
        projetDetailsAvecCout(projet.getId());

        devisAffichage.devisInputs(projet);

        return projet;
    }


/*
    public Projet ajouterProjet(Client client) {
        Projet projet = new Projet();
        if (client == null || client.getId() <= 0) {
            System.out.println("Erreur : Aucun client valide sélectionné. Annulation de la création du projet.");
            return null;
        }
        projet.setClient(client);
        projet.setEtat_Projet(EtatProjet.En_cours);

        System.out.print("Nom du projet : ");
//        String nom = scanner.nextLine();
        String nom = inputValidation.validateNonEmptyString(scanner);
        projet.setNom_Projet(nom);

        List<Materiau> materiaux = composantAffichage.ajouterMateriaux();
        List<MainOeuvre> mainOeuvres = composantAffichage.ajouterMainOeuvre();

        System.out.print("Entrez le taux de TVA du projet : ");
//        Double tva = scanner.nextDouble();
        Double tva = InputValidation.validateDoubleInput(scanner);
        materiaux.forEach(component -> component.setTauxTVA(tva));
        mainOeuvres.forEach(component -> component.setTauxTVA(tva));

        System.out.print("Ajoutez la marge bénéficiaire au projet : ");
//        Double margeBeneficiaire = scanner.nextDouble();
        Double margeBeneficiaire = InputValidation.validateDoubleInput(scanner);
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

        projet = gestionnaireProjet.ajouterProjet(projet);

        if (projet != null) {
            System.out.println("Projet ajouté avec succès !");
            System.out.println("Nom du projet : " + projet.getNom_Projet());
            System.out.println("Marge bénéficiaire : " + projet.getMarge_Beneficiaire());
        } else {
            System.out.println("Erreur lors de l'ajout du projet.");
        }

        assert projet != null;
        projetDetailsAvecCout(projet.getId());

        devisAffichage.devisInputs(projet);

        return projet;
    }
*/

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
}
