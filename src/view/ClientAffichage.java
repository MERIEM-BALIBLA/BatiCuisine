package view;

import entity.Client;
import services.ClientService;
import util.InputValidation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class ClientAffichage {
    Scanner scanner = new Scanner(System.in);
    //    private InputValidation inputValidation = new InputValidation();
    ClientService clientService = new ClientService();

    ProjetAffichage projetAffichage = new ProjetAffichage();
    InputValidation inputValidation = new InputValidation();

    public ClientAffichage() {
    }

    public void clientListe() {
        List<Client> clients = clientService.clientListe();
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            clients.forEach(client -> {
                String PRO = client.getEstProfessionnel() ? "C'est un professionnel" : "C'était pas un professionnel";
                System.out.println(
                        "Client ID: " + client.getId() +
                                " Name: " + client.getNom() +
                                " Address: " + client.getAdresse() +
                                " Phone: " + client.getTelephone() + " " + PRO);
            });
        }
    }

    public Client ajouterClient() {
        System.out.println("Saiire le nom de client :");
        String nom = InputValidation.validateNonEmptyString(scanner);

        System.out.println("Saisie l'adresse de client :");
        String adresse = InputValidation.validateAddress(scanner);

        System.out.println("Saisie le numéro de téléphone du client :");
        String numero = InputValidation.validatePhoneNumber(scanner);

//        System.out.println("Ce client est un professionnel : y/NON");
//        String professionnel = scanner.nextLine();
//        boolean pro = professionnel.equals("OUI");

        System.out.println("Ce client est un professionnel : OUI/NON");
        String professionnel = scanner.nextLine().toUpperCase();
        while (!professionnel.equals("OUI") && !professionnel.equals("NON")) {
            System.out.println("Veuillez entrer OUI ou NON.");
            professionnel = scanner.nextLine().toUpperCase();
        }
        boolean pro = professionnel.equals("OUI");
        Client nouveauClient = clientService.ajouterClient(nom, adresse, numero, pro);

        if (nouveauClient != null) {
            String PRO = nouveauClient.getEstProfessionnel() ? "C'est un professionnel" : "pas de profession";
            System.out.println("Added client: " + nouveauClient.getNom() +
                    ", Address: " + nouveauClient.getAdresse() +
                    ", Phone: " + nouveauClient.getTelephone() + " " +
                    PRO
            );
        } else {
            System.out.println("Failed to add client.");
        }
        return nouveauClient;
    }

    public void supprimerClient() {
        System.out.println("Saisie l'id de client souhaiter : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean status = clientService.supprimerClient(id);
        if (status) {
            System.out.println("Le client a ete bien supprimer");
        }
    }

    public void modifierClient() {
        System.out.println("Saisie l'id de client souhaiter : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Saisie le nouveau nom du client :");
        String nom = scanner.nextLine();

        System.out.println("Saisie le nouveau adresse de client :");
        String adresse = scanner.nextLine();

        System.out.println("Saisie le noueau numero souhaiter :");
        String telephone = scanner.nextLine();

        System.out.println("Le client a une profession ? OUI/NON");
        String professionnel = scanner.nextLine();
        boolean pro = professionnel.equals("OUI");

        Client newUpdate = clientService.modifierClient(id, nom, adresse, telephone, pro);
        System.out.println(newUpdate);
    }

    public void rechercheClient() {
        System.out.println("Id client: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Client> client = clientService.chercherClient(id);
        if (client.isEmpty()) {
            System.out.println("Utilisateur non trouvé.");
        } else {
            System.out.println("Client trouvé !\n");
        }

//        Client trouvé !
//                Nom : Mme Dupont
//        Adresse : 12 Rue des Fleurs, Paris
//        Numéro de téléphone : 06 12345678

        System.out.println(client.get());
    }

    private Optional<Client> chercherClientExistant() {
        System.out.print("Entrez le nom du client : ");
        String nom = InputValidation.validateNonEmptyString(scanner);
        return clientService.rechercherClientParNom(nom);
    }

    private Client traiterClientExistant(Client client) {
        System.out.println("Client trouvé !");
        System.out.println("Nom : " + client.getNom());
        System.out.println("Adresse : " + client.getAdresse());
        System.out.println("Numéro de téléphone : " + client.getTelephone());

        System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
        if (inputValidation.validerOuiNon(scanner)) {
            projetAffichage.ajouterProjet(client);
            return client;
        } else {
            return null;
        }
    }

    private Client ajouterNouveauClient() {
        Client nouveauClient = ajouterClient();
        if (nouveauClient != null) {
            System.out.println("Client ajouté avec succès !");
            System.out.println("ID du client : " + nouveauClient.getId());

            System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            if (inputValidation.validerOuiNon(scanner)) {
                projetAffichage.ajouterProjet(nouveauClient);
                return nouveauClient;
            } else {
                return null;
            }
        } else {
            System.out.println("Erreur lors de l'ajout du client.");
        }
        return null;
    }

    public Client gererClient() {
        while (true) {
            System.out.println("--- Gestion des Clients ---");
            System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
            System.out.println("1. Chercher un client existant");
            System.out.println("2. Ajouter un nouveau client");
            System.out.print("Choisissez une option : ");

            try {
                int choix = scanner.nextInt();
                scanner.nextLine(); // Clear the newline

                Optional<Client> client = Optional.empty();

                switch (choix) {
                    case 1:
                        client = chercherClientExistant();
                        if (client.isPresent()) {
                            return traiterClientExistant(client.get());
                        } else {
                            System.out.println("Client non trouvé.");
                        }
                        break;
                    case 2:
                        return ajouterNouveauClient();
                    default:
                        System.out.println("Entrée invalide. Veuillez entrer un nombre (1 ou 2).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre (1 ou 2).");
                scanner.next(); // Clear the invalid input
            }
        }
    }





}
