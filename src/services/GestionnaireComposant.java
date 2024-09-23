package services;

import entity.*;
import entity.enums.EtatProjet;
import repository.impl.ComposantRepository;
import repository.impl.MainoeuvreRepository;
import repository.impl.MateriauRepository;
import repository.impl.ProjetRepository;
import services.interfaces.ComposantInterface;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireComposant implements ComposantInterface {

    private static ComposantRepository composantRepository = new ComposantRepository();
    private static MateriauRepository materiauRepository = new MateriauRepository();
    private static MainoeuvreRepository mainoeuvreRepository = new MainoeuvreRepository();

    public Composant ajouterComposant(Composant composant) {
        return composantRepository.ajouterComposant(composant);
    }


    //    recuperer la liste des composants
    public List<Composant> composantList(Projet projet) {
        List<Composant> composants = new ArrayList<>();

        // Récupérer les matériaux
        List<Materiau> materiaux = materiauRepository.selectMateriaux(projet.getId());
        composants.addAll(materiaux);

        // Récupérer la main d'œuvre
        List<MainOeuvre> mainOeuvres = mainoeuvreRepository.selectMainOeuvre(projet.getId());
        composants.addAll(mainOeuvres);

        projet.setComposants(composants);

        return composants;
    }


    public double coutMateriaux(Projet projet) {
        List<Materiau> composants = materiauRepository.selectMateriaux(projet.getId());
        return composants.stream()
                .mapToDouble(composant -> composant.calculerCoutMateriau())
                .sum();
    }

    public double coutMainOeuvre(Projet projet) {
        List<MainOeuvre> composants = mainoeuvreRepository.selectMainOeuvre(projet.getId());
        return composants.stream()
                .mapToDouble(composant -> composant.calculerCoutMainOeuvre())
                .sum();
    }

    public Double coutTotal(Projet projet) {
        double cout = coutMateriaux(projet) + coutMainOeuvre(projet);
        return projet.setCout_Total(cout);
    }

    public void afficherComposantsEtCoutTotal(Projet projet) {
        // Vérifier si le projet a des composants
        if (projet.getComposants() == null || projet.getComposants().isEmpty()) {
            System.out.println("Aucun composant associé à ce projet.");
            return;
        }
        double coutTotal = coutTotal(projet); // Appel à la méthode existante
        System.out.println("Coût total du projet : " + coutTotal);
    }


}
