import entity.Client;
import services.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientAffichage {
    Scanner scanner = new Scanner(System.in);
    ClientService clientService = new ClientService();

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

    public void ajouterClient() {
        System.out.println("Saiire le nom de client :");
        String nom = scanner.nextLine();

        System.out.println("Saisie l'adresse de client :");
        String adresse = scanner.nextLine();

        System.out.println("Saisie le numéro de téléphone du client :");
        String numero = scanner.nextLine();

        System.out.println("Ce client est un professionnel : OUI/NON");
        String professionnel = scanner.nextLine();
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

    public void rechercheClient(){
        System.out.println("Id client: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Client> client = clientService.chercherClient(id);

        System.out.println(client.isPresent() ? client.get():"not found");
    }

    public void rechercheClientParNom(){
        System.out.println("Nom de client: ");
        String  nom = scanner.nextLine();

        Optional<Client> client = clientService.rechercheParNom(nom);
        System.out.println(client.isPresent() ? client.get():"not found");

    }
}
