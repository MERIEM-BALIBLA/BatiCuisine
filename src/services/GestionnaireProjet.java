package services;

import entity.Client;
import entity.Composant;
import entity.Projet;
import entity.enums.ComposantType;
import repository.impl.ProjetRepository;
import services.interfaces.ProjetInterface;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GestionnaireProjet implements ProjetInterface {

    ProjetRepository projetRepository = new ProjetRepository();
    GestionnaireComposant gestionnaireComposant = new GestionnaireComposant();
    ClientService clientService = new ClientService();

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


  /*  public Map<String, List<String>> clientListMap() {
        List<Client> clientList = clientService.clientListe();

        return clientList.stream().collect(Collectors.toMap(
                Client::getNom, // Utilisez le nom du client comme clé
                client -> {
                    List<String> projetNames = projetRepository.selectProjetParClient(client.getId()).stream()
                            .map(Projet::getNom_Projet)
                            .collect(Collectors.toList());
                    return projetNames;
                },
                (exesting, newproj) -> {
                    exesting.addAll(newproj);
                    return exesting;
                }
        ));
    }*/

/*
    public static void main(String[] args) {
        GestionnaireProjet gestionnaireProjet = new GestionnaireProjet();
        Map<String, List<String>> projetLiset = gestionnaireProjet.clientListMap();
        projetLiset.forEach((elem, com) -> {
            System.out.println(elem + com);
        });

    }*/
}


