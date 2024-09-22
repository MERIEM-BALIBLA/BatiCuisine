package services;

import entity.Client;
import entity.Projet;
import repository.impl.ProjetRepository;
import services.interfaces.ProjetInterface;

import java.util.Optional;

public class GestionnaireProjet implements ProjetInterface {
    ProjetRepository projetRepository = new ProjetRepository();
    ClientService clientService = new ClientService();

    public Projet ajouterProjet(Projet projet) {
        return projetRepository.ajouterProjet(projet);
    }

    public Optional<Projet> rechercheProjet(int id){
        return Optional.empty();
    }



}


