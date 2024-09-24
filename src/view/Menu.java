package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private ClientAffichage clientAffichage = new ClientAffichage();
    private ProjetAffichage projetAffichage = new ProjetAffichage();
    public void afficherMenuPrincipal() {
        boolean status = true;

        while (status) {
            System.out.println("=====================================");
            System.out.println("          MENU PRINCIPAL            ");
            System.out.println("=====================================");
            System.out.printf("%s1.%s Créer un nouveau projet%n", "\u001B[32m", "\u001B[0m");
            System.out.printf("%s2.%s Afficher les projets existants%n", "\u001B[32m", "\u001B[0m");
            System.out.printf("%s3.%s Calculer le coût d'un projet%n", "\u001B[32m", "\u001B[0m");
            System.out.printf("%s4.%s Quitter%n", "\u001B[32m", "\u001B[0m");
            System.out.print("Choisissez une option : ");

            try {
                int choix = scanner.nextInt(); // Lire le choix de l'utilisateur

                switch (choix) {
                    case 1:
                        clientAffichage.gererClient();
                        break;
                    case 2:
                        projetAffichage.afficherProjets();
                        break;
                    case 3:
                        System.out.println("Id de projet : ");
                        int id = scanner.nextInt();
                        projetAffichage.projetDetailsAvecCout(id);
                        break;
                    case 4:
                        System.out.println("Au revoir !");
                        status = false;
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre (1, 2, 3 ou 4).");
                scanner.next(); // Clear the invalid input
            }
        }
    }

/*
    public void afficherMenuPrincipal() {
        boolean status = true;

        while (status) {
                System.out.println("=====================================");
                System.out.println("          MENU PRINCIPAL            ");
                System.out.println("=====================================");
                System.out.printf("%s1.%s Créer un nouveau projet%n", "\u001B[32m", "\u001B[0m");
                System.out.printf("%s2.%s Afficher les projets existants%n", "\u001B[32m", "\u001B[0m");
                System.out.printf("%s3.%s Calculer le coût d'un projet%n", "\u001B[32m", "\u001B[0m");
                System.out.printf("%s4.%s Quitter%n", "\u001B[32m", "\u001B[0m");
                System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt(); // Lire le choix de l'utilisateur

            switch (choix) {
                case 1:
                    clientAffichage.gererClient();
                    break;
                case 2:
                    projetAffichage.afficherProjets();
                    break;
                case 3:
                    System.out.println("Id de projet : ");
                    int id = scanner.nextInt();
                    projetAffichage.projetDetailsAvecCout(id);
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    status = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }

        }

    }
*/
}
