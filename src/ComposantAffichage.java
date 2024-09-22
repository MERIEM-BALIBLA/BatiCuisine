import entity.MainOeuvre;
import entity.Materiau;
import entity.Projet;
import entity.enums.ComposantType;
import repository.impl.ComposantRepository;
import util.InputValidation;

import java.util.Scanner;

public class ComposantAffichage {

    ComposantRepository composantRepository = new ComposantRepository();

    public void ajouterComposant(Projet projet) {
        if (projet == null) {
            System.out.println("Aucun projet sélectionné. Annulation de l'ajout de composants.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String choix;

        System.out.println("--- Ajout des matériaux ---");
        do {
            System.out.print("Entrez le nom du matériau : ");
            String nom = InputValidation.validateNonEmptyString(scanner);

            ComposantType typeComposant = ComposantType.Materiau;

            System.out.print("Entrez le taux de TVA : ");
            double tauxTVA = InputValidation.validateDoubleInput(scanner);

            System.out.print("Entrez la quantité de ce matériau (en m²) : ");
            double quantite = InputValidation.validateDoubleInput(scanner);

            System.out.print("Entrez le coût unitaire de ce matériau (€/m²) : ");
            double coutUnitaire = InputValidation.validateDoubleInput(scanner);

            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            double coutTransport = InputValidation.validateDoubleInput(scanner);

            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            double coefficientQualite = InputValidation.validateDoubleInput(scanner);

            Materiau materiau = new Materiau(nom, typeComposant, tauxTVA, projet, coutUnitaire, quantite, coutTransport, coefficientQualite);
            composantRepository.ajouterComposant(materiau);

            System.out.println("Matériau ajouté avec succès !");
            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            choix = scanner.nextLine();
        } while (choix.equalsIgnoreCase("y"));

//-----------------------------------------------------------------------------------------------------

        System.out.println("--- Ajout de la main-d'œuvre ---");
        do {
            ComposantType typeComposant = ComposantType.MainOeuvre;

            System.out.print("Entrez le nom de la main-d'œuvre : ");
            String nomMainOeuvre = InputValidation.validateNonEmptyString(scanner);

            System.out.print("Entrez le taux de TVA : ");
            double tauxTVA = InputValidation.validateDoubleInput(scanner);

            System.out.print("Entrez le taux horaire (€) : ");
            double tauxHoraire = InputValidation.validateDoubleInput(scanner);

            System.out.print("Entrez le nombre d'heures de travail : ");
            double heuresTravail = InputValidation.validateDoubleInput(scanner);

            System.out.print("Entrez la productivité de l'ouvrier : ");
            double productiviteOuvrier = InputValidation.validateDoubleInput(scanner);

            MainOeuvre mainOeuvre = new MainOeuvre(nomMainOeuvre, typeComposant, tauxTVA, projet, tauxHoraire, heuresTravail, productiviteOuvrier);
            composantRepository.ajouterComposant(mainOeuvre);

            System.out.println("Main-d'œuvre ajoutée avec succès !");
            System.out.print("Voulez-vous ajouter une autre main-d'œuvre ? (y/n) : ");
            choix = scanner.nextLine();
        } while (choix.equalsIgnoreCase("y"));
        scanner.close();
    }

/*
    public void ajouterComposant(Projet projet) {
        if (projet == null) {
            System.out.println("Aucun projet sélectionné. Annulation de l'ajout de composants.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ComposantRepository composantRepository = new ComposantRepository();
        String choix;

        System.out.println("--- Ajout des matériaux ---");
        do {
            System.out.print("Entrez le nom du matériau : ");
            String nom = scanner.nextLine();

            System.out.print("Entrez le type de composant : ");
            String typeComposant = scanner.nextLine();  // Saisie du type de composant

            System.out.print("Entrez le taux de TVA : ");
            double tauxTVA = scanner.nextDouble();

            System.out.print("Entrez la quantité de ce matériau (en m²) : ");
            double quantite = scanner.nextDouble();

            System.out.print("Entrez le coût unitaire de ce matériau (€/m²) : ");
            double coutUnitaire = scanner.nextDouble();

            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            double coutTransport = scanner.nextDouble();

            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            double coefficientQualite = scanner.nextDouble();
            scanner.nextLine();  // Consomme la nouvelle ligne

            Materiau materiau = new Materiau(nom, typeComposant, tauxTVA, projet, coutUnitaire, quantite, coutTransport, coefficientQualite);
            composantRepository.ajouterComposant(materiau);  // Appel de votre méthode d'ajout

            System.out.println("Matériau ajouté avec succès !");
            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            choix = scanner.nextLine();
        } while (choix.equalsIgnoreCase("y"));

        // Ajout de la main-d'œuvre
        // ...

        scanner.close();  // Ferme le scanner
    }
*/

}
