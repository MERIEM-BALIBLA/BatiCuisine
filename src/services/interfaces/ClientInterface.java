package services.interfaces;

import entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientInterface {
    List<Client> clientListe();

    Client ajouterClient(String nom, String adresse, String telephone, boolean professionnel);

    Optional<Client> chercherClient(int id);

    boolean supprimerClient(int id);

    Client modifierClient(int id, String nouveauNom, String nouveauAdresse, String nouveauTelephone, boolean nouveauProfessionnel);
}
