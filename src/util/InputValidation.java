package util;

import java.util.Scanner;

public class InputValidation {

    Scanner scanner = new Scanner(System.in);

    public static double validateDoubleInput(Scanner scanner) {
        while (true) {
//            System.out.print("Entrez un nombre : ");
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
//            System.out.print("Entrez une chaîne : ");
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                return input;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer une chaîne non vide.");
            }
        }
    }
}
