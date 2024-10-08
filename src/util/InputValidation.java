package util;

import java.util.Scanner;

public class InputValidation {

    Scanner scanner = new Scanner(System.in);

    public static double validateDoubleInput(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().replace(",", ".");
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
            }
        }
    }

    public static String validateNonEmptyString(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && input.matches("[a-zA-Zà-ÿÀ-ÿ]+") && input.length() <= 100) {
                return input;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer une chaîne non vide contenant uniquement des lettres.");
            }
        }
    }

    public static String validateAddress(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            // Vérifie que l'adresse n'est pas vide, a une longueur maximale de 200, et accepte certains caractères
            if (!input.isEmpty() && input.length() <= 200 && input.matches("[a-zA-Z0-9 ,.-]+")) {
                return input;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer une adresse valide (max 200 caractères, lettres, chiffres, espaces, virgules, points, tirets).");
            }
        }
    }

    public static String validatePhoneNumber(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.matches("^\\+?\\d{10,15}$")) {
                return input;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un numéro de téléphone valide (10 à 15 chiffres, avec ou sans +).");
            }
        }
    }


    public boolean validerOuiNon(Scanner scanner) {
        String choix = scanner.nextLine().toLowerCase();

        while (!choix.equals("y") && !choix.equals("n")) {
            System.out.println("Veuillez entrer 'y' pour oui ou 'n' pour non.");
            choix = scanner.nextLine().toLowerCase();
        }

        return choix.equals("y");
    }


}
