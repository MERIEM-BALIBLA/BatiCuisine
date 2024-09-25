package services;

import entity.Devis;
import entity.Projet;
import entity.enums.EtatProjet;
import repository.impl.DevisRepository;
import repository.interfaces.DevisInterface;


public class DevisService implements DevisInterface {
    private static DevisRepository devisRepository;
    GestionnaireProjet gestionnaireProjet;

    public DevisService() {
        this.gestionnaireProjet = new GestionnaireProjet();
        this.devisRepository = new DevisRepository();
    }

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
