package services.interfaces;

import entity.Composant;
import entity.MainOeuvre;
import entity.Materiau;
import entity.Projet;

import java.util.List;

public interface ComposantInterface {

    Composant ajouterComposant(Composant composant);

    List<Materiau> materiauxList(Projet projet);

    List<MainOeuvre> mainOeuvreList(Projet projet);

    List<Composant> composantList(Projet projet);

    Double coutTotal(Projet projet);


}
