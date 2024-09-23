package view;

import entity.MainOeuvre;
import entity.Materiau;
import entity.Projet;
import entity.enums.ComposantType;
import repository.impl.ComposantRepository;
import services.GestionnaireComposant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComposantAffichage {

    Scanner scanner = new Scanner(System.in);
    GestionnaireComposant gestionnaireComposant = new GestionnaireComposant();

    public List<Materiau> ajouterMateriaux() {
        List<Materiau> materiaux = new ArrayList<>();
        boolean continuer = true;
        while (continuer) {

            Materiau materiau = new Materiau();

            System.out.print("Entrez le nom du matériau : ");
            String nom = scanner.nextLine();
            materiau.setNom(nom);

            ComposantType type = ComposantType.Materiau;
            materiau.setTypeComposant(type);

            System.out.println("Entrer le cout unitaire :");
            double coutUnitaire = scanner.nextDouble();
            materiau.setCoutUnitaire(coutUnitaire);
            scanner.nextLine();

            System.out.print("Entrez la quantité : ");
            double quantite = scanner.nextDouble();
            materiau.setQuantite(quantite);

            System.out.print("Entrez le coût de transport : ");
            double coutTransport = scanner.nextDouble();
            materiau.setCoutTransport(coutTransport);

            System.out.print("Entrez le coefficient de qualité : ");
            double coefQualite = scanner.nextDouble();
            materiau.setCoefficientQualite(coefQualite);
            scanner.nextLine();

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

            System.out.print("Entrez le nom du matériau : ");
            String nom = scanner.nextLine();
            mainOeuvre.setNom(nom);

            ComposantType type = ComposantType.MainOeuvre;
            mainOeuvre.setTypeComposant(type);

            System.out.println("Entrer le taux horaire :");
            double tauxHoraire = scanner.nextDouble();
            mainOeuvre.setTauxHoraire(tauxHoraire);
            scanner.nextLine();

            System.out.print("Entrez les heures de travail : ");
            double heureTravail = scanner.nextDouble();
            mainOeuvre.setHeuresTravail(heureTravail);

            System.out.print("Entrez productivité ouvrier : ");
            double productiviteOurier = scanner.nextDouble();
            mainOeuvre.setProductiviteOuvrier(productiviteOurier);
            scanner.nextLine();

            mainOeuvres.add(mainOeuvre);

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            continuer = scanner.nextLine().equalsIgnoreCase("y");
        }
        return mainOeuvres;
    }

    public void coutTotal(Projet projet){
        System.out.println("Le cout total :" + gestionnaireComposant.coutTotal(projet));
    }
}
