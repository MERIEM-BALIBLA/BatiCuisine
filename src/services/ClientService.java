
package services;

import entity.Client;
import repository.impl.ClientRepository;
import services.interfaces.ClientInterface;
//import re
import java.util.List;
import java.util.Optional;

public class ClientService implements ClientInterface {
    //    private ClientRepository clientRepository = new ClientRepository();
    private final ClientRepository clientRepository;

    // Injection par constructeur
    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public List<Client> clientListe() {
        return clientRepository.clientsListe();
    }

    public Client ajouterClient(String nom, String adresse, String telephone, boolean professionnel) {
        return clientRepository.ajouterClient(nom, adresse, telephone, professionnel);
    }

    public Optional<Client> chercherClient(int id) {
        Optional<Client> client = clientRepository.foundClient(id);
        return client;
    }

    public boolean supprimerClient(int id) {
        if (clientRepository.foundClient(id).isEmpty()) {
            System.out.println("Utilisateur non trouvé.");
        }
        return clientRepository.supprimerClient(id);
    }

    public Client modifierClient(int id, String nouveauNom, String nouveauAdresse, String nouveauTelephone, boolean nouveauProfessionnel) {
        if (clientRepository.foundClient(id).isEmpty()) {
            System.out.println("Utilisateur non trouvé.");
        }
        return clientRepository.modifierClient(id, nouveauNom, nouveauAdresse, nouveauTelephone, nouveauProfessionnel);
    }

    public Optional<Client> rechercherClientParNom(String nom) {
        return clientRepository.rechercherClientParNom(nom);
    }

}
