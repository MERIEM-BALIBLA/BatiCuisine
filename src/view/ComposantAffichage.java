package view;

import entity.MainOeuvre;
import entity.Materiau;
import entity.Projet;
import entity.enums.ComposantType;
import repository.impl.ComposantRepository;
import services.GestionnaireComposant;
import util.InputValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComposantAffichage {

    Scanner scanner = new Scanner(System.in);
    GestionnaireComposant gestionnaireComposant = new GestionnaireComposant();
    private static InputValidation inputValidation = new InputValidation();

    public List<Materiau> ajouterMateriaux() {
        List<Materiau> materiaux = new ArrayList<>();
        boolean continuer = true;
        while (continuer) {

            Materiau materiau = new Materiau();

            System.out.print("Entrez le nom du matériau : ");
            String nom = InputValidation.validateNonEmptyString(scanner);
            materiau.setNom(nom);

            ComposantType type = ComposantType.Materiau;
            materiau.setTypeComposant(type);

            System.out.print("Entrer le cout unitaire :");
            double coutUnitaire = InputValidation.validateDoubleInput(scanner);
            materiau.setCoutUnitaire(coutUnitaire);

            System.out.print("Entrez la quantité : ");
            double quantite = InputValidation.validateDoubleInput(scanner);
            materiau.setQuantite(quantite);

            System.out.print("Entrez le coût de transport : ");
            double coutTransport = InputValidation.validateDoubleInput(scanner);
            materiau.setCoutTransport(coutTransport);

            System.out.print("Entrez le coefficient de qualité : ");
            double coefQualite = InputValidation.validateDoubleInput(scanner);
            materiau.setCoefficientQualite(coefQualite);

            materiaux.add(materiau);

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            continuer = scanner.nextLine().equalsIgnoreCase("y");
        }
        return materiaux;
    }

    public List<MainOeuvre> ajouterMainOeuvre() {
        List<MainOeuvre> mainOeuvres = new ArrayList<>();
        boolean continuer = true;
        while (continuer) {

            MainOeuvre mainOeuvre = new MainOeuvre();

            System.out.print("Entrez le nom de la mainOeuvre : ");
            String nom = InputValidation.validateNonEmptyString(scanner);
            mainOeuvre.setNom(nom);

            ComposantType type = ComposantType.MainOeuvre;
            mainOeuvre.setTypeComposant(type);

            System.out.print("Entrer le taux horaire :");
            double tauxHoraire = InputValidation.validateDoubleInput(scanner);
            mainOeuvre.setTauxHoraire(tauxHoraire);

            System.out.print("Entrez les heures de travail : ");
            double heureTravail = InputValidation.validateDoubleInput(scanner);
            mainOeuvre.setHeuresTravail(heureTravail);

            System.out.print("Entrez productivité ouvrier : ");
            double productiviteOurier = InputValidation.validateDoubleInput(scanner);
            mainOeuvre.setProductiviteOuvrier(productiviteOurier);

            mainOeuvres.add(mainOeuvre);

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            continuer = scanner.nextLine().equalsIgnoreCase("y");
        }
        return mainOeuvres;
    }

}
