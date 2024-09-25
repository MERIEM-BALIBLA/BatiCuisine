package repository.interfaces;

import entity.Composant;
import entity.Projet;

import java.util.List;
import java.util.Optional;

public interface ProjetInterface {
    Projet ajouterProjet(Projet projet);
    Optional<Projet> afficherProjet(int projetId);
    List<Projet> afficherTousLesProjets();
    List<Composant> getComposantsByProjetId(int projetId);
    Boolean supprimerProjet(Projet projet);
    Projet modifierEtatProjet(Projet projet);
}
