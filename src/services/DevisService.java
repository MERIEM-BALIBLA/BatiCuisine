package services;

import entity.Devis;
import entity.Projet;
import entity.enums.EtatProjet;
import repository.impl.DevisRepository;
import repository.interfaces.DevisInterface;

import java.util.List;

public class DevisService implements DevisInterface {
    private static DevisRepository devisRepository = new DevisRepository();
    GestionnaireProjet gestionnaireProjet = new GestionnaireProjet();
    GestionnaireComposant gestionnaireComposant = new GestionnaireComposant();


    public Devis ajouterDevis(Devis devis) {
        Projet projet = devis.getProjet();

        if (devis.isAccepte()) {
            projet.setEtat_Projet(EtatProjet.Termine);
            devisRepository.ajouterDevis(devis);
            gestionnaireProjet.modifierEtatProjet(projet);
            return devis;
        } else {
            projet.setEtat_Projet(EtatProjet.Annule);
            gestionnaireProjet.modifierEtatProjet(projet);
            return null;
        }
    }


}
