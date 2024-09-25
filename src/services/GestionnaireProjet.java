package services;

import entity.Client;
import entity.Composant;
import entity.Projet;
import repository.impl.ProjetRepository;
import services.interfaces.ProjetInterface;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GestionnaireProjet implements ProjetInterface {

    private final ProjetRepository projetRepository;

    GestionnaireComposant gestionnaireComposant;

    public GestionnaireProjet() {
        this.projetRepository = new ProjetRepository();
        this.gestionnaireComposant = new GestionnaireComposant();
    }

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

    public Projet modifierEtatProjet(Projet projet) {
        return projetRepository.modifierEtatProjet(projet);
    }


  /*  public static void main(String[] args) {
        GestionnaireProjet gestionnaireProjet = new GestionnaireProjet();

        // Afficher chaque projet filtré
//        gestionnaireProjet.filter().forEach(e -> System.out.println(e));
        gestionnaireProjet.projetListe().forEach(e -> System.out.println(e.getNom_Projet() + e.getComposants()));
    }*/


}


