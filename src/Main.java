import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientAffichage clientAff = new ClientAffichage();

        boolean status = true;

        while (status) {
            affichage();
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    clientAff.clientListe();
                    break;
                case 2:
                    clientAff.ajouterClient();
                    break;
                case 3:
                    clientAff.modifierClient();
                    break;
                case 4:
                    clientAff.supprimerClient();
                    break;
                case 5:
                    clientAff.rechercheClient();
                    break;
                case 6:
                    quitter();
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }

    private static void affichage() {
        System.out.println("=== Menu Utilisateur ===");
        System.out.println("1. La liste des clients");
        System.out.println("2. Ajouter un noueau client");
        System.out.println("3. Modifier un client");
        System.out.println("4. Supprimer un client");
        System.out.println("5. recherche un client");

        System.out.println("n. Quitter");
        System.out.print("Choisissez une option : ");

    }

    private static void quitter() {
        System.out.println("Quitter le programme.");
        boolean status = false;
    }

}
