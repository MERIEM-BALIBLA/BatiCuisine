package services.interfaces;

import entity.Projet;

import java.util.Optional;

public interface ProjetInterface {

    Projet ajouterProjet(Projet projet);

    Optional<Projet> rechercheProjet(int id);

}
