import entity.Client;
import entity.Projet;
import services.GestionnaireProjet;
import util.InputValidation;

import java.util.Scanner;

public class ProjetAffichage {

    Scanner scanner = new Scanner(System.in);

    GestionnaireProjet gestionnaireProjet = new GestionnaireProjet();

    ComposantAffichage composantAffichage = new ComposantAffichage();

    public Projet ajouterProjet(Client client) {
        if (client == null) {
            System.out.println("Aucun client sélectionné. Annulation de la création du projet.");
        }

        System.out.print("Entrez le nom du projet : ");
        String nom = InputValidation.validateNonEmptyString(scanner);

        System.out.println("Saisissez la marge bénéficiaire : ");
        double marge = InputValidation.validateDoubleInput(scanner);

        Projet projet = new Projet(client, nom, marge);
        gestionnaireProjet.ajouterProjet(projet);

        composantAffichage.ajouterComposant(projet);
        return projet;
    }


}
