package services;

import entity.Composant;
import entity.Projet;
import repository.impl.ProjetRepository;
import services.interfaces.ProjetInterface;

import java.util.List;
import java.util.Optional;

public class GestionnaireProjet implements ProjetInterface {
    ProjetRepository projetRepository = new ProjetRepository();
    GestionnaireComposant gestionnaireComposant = new GestionnaireComposant();

    public Projet ajouterProjet(Projet projet) {
        Projet insertedProjet = projetRepository.ajouterProjet(projet);
        for (Composant composant : projet.getComposants()) {
            composant.setProjet(insertedProjet);
            gestionnaireComposant.ajouterComposant(composant);
        }
        return insertedProjet;
    }

    public Optional<Projet> rechercheProjet(int id) {
        return projetRepository.afficherProjet(id);
    }

    public List<Projet> projetListe() {
        return projetRepository.afficherTousLesProjets();
    }

    public Double coutTotal(Projet projet) {
        if (projet == null || projet.getComposants() == null || projet.getComposants().isEmpty()) {
            return 0.0; // Retourner 0 si le projet ou ses composants ne sont pas valides
        }

//        double tva = 0.0; // Valeur par défaut pour la TVA
//        if (projet.getComposants().size() > 1) {
//            tva = projet.getComposants().get(1).getTauxTVA(); // Assurez-vous que l'index existe
//        }

        double cout = (gestionnaireComposant.coutMateriaux(projet) + gestionnaireComposant.coutMainOeuvre(projet)) * projet.getMarge_Beneficiaire();

        projet.setCout_Total(cout); // Met à jour le coût total du projet
        return cout;
    }

  /*  public Double coutTotal(Projet projet) {
        if(projet != null){
            double tva = projet.getComposants().get(1).getTauxTVA();
        }
        double cout = (gestionnaireComposant.coutMateriaux(projet) + gestionnaireComposant.coutMainOeuvre(projet)) * projet.getMarge_Beneficiaire() * (1 + tva / 100);
        return cout;
    }*/
    public Boolean supprimerProejt(Projet projet){
        return projetRepository.supprimerProjet(projet);
    }

    public Projet modifierEtatProjet(Projet projet){
        return projetRepository.modifierEtatProjet(projet);
    }
}


