package repository.interfaces;

import entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientInterface {
    List<Client> clientsListe();
    Optional<Client> foundClient(int id);
    Client ajouterClient (String nom, String adresse, String telephone, boolean Professionnel);
    boolean supprimerClient(int id);
    Client modifierClient(int id, String nouveauNom, String nouveauAdresse, String nouveauTelephone, boolean nouveauProfessionnel);

    Optional<Client> rechercherClientParNom(String nom);

    }

