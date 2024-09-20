package services;

import entity.Client;
import entity.Projet;
import repository.impl.ProjetRepository;

import java.util.Optional;

public class GestionnaireProjet {
    ProjetRepository projetRepository = new ProjetRepository();
    ClientService clientService = new ClientService();

    public Projet ajouterProjet(Projet projet, int clientId) {
        Optional<Client> foundClient = clientService.chercherClient(clientId);
        if (foundClient.isPresent()) {
            projet.setClient(foundClient.get());
            return projetRepository.ajouterProjet(projet);
        } else {
            throw new IllegalArgumentException("Le client avec l'ID " + clientId + " n'existe pas.");
        }
    }

}


