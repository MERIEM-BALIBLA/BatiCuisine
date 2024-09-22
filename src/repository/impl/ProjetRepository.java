package repository.impl;

import config.Connexion;
import entity.Projet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ProjetRepository {

    private final Connexion connection;

    public ProjetRepository() {
        this.connection = Connexion.getInstance();
    }

    public Projet ajouterProjet(Projet projet) {
        String sql = "INSERT INTO projets (client_id, nom_projet, marge_beneficiaire, etat_projet) VALUES (?, ?, ?, ?::etatprojet)";

        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, projet.getClient().getId());
            ps.setString(2, projet.getNom_Projet());
            ps.setDouble(3, projet.getMarge_Beneficiaire());
            ps.setString(4, projet.getEtat_Projet().name());

            ps.executeUpdate();

            // Récupérer l'ID généré
            try (ResultSet resultSet = ps.getGeneratedKeys()) {
                if (resultSet.next()) {
                    projet.setId(resultSet.getInt(1)); // Assigner l'ID généré au projet
                    return projet;
                } else {
                    throw new SQLException("Aucun ID généré.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du projet : " + e.getMessage());
            throw new RuntimeException("Échec de l'ajout du projet", e);
        }
    }


//    public Projet ajouterProjet(Projet projet) {
//        System.out.println(projet);
//        String sql = "INSERT INTO projets (client_id, nom_projet, marge_beneficiaire, etat_projet) VALUES (?, ?, ?, ?::etatprojet)";
//
//        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql)) {
//
//            ps.setInt(1, projet.getClient().getId()); // Assurez-vous que l'ID est correct
//            ps.setString(2, projet.getNom_Projet());
//            ps.setDouble(3, projet.getMarge_Beneficiaire());
//            ps.setString(4, projet.getEtat_Projet().name());
//
//            ps.executeUpdate();
//            try (ResultSet resultSet = ps.getGeneratedKeys()) {
//                if (resultSet.next()) {
//                    projet.setId(resultSet.getInt(1)); // Mettre à jour l'ID dans l'objet projet
//                } else {
//                    throw new SQLException("Échec de la récupération de l'ID généré.");
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de l'ajout du projet : " + e.getMessage());
//            throw new RuntimeException("Échec de l'ajout du projet", e);
//        }
//        return projet;
//    }


/*
    public Projet ajouterProjet(Projet projet) {
        System.out.println(projet);
        String sql = "INSERT INTO projets (client_id, nom_projet, marge_beneficiaire, etat_projet) VALUES (?, ?, ?, ?::etatprojet)";

        try {
            PreparedStatement ps = connection.connectToDB().prepareStatement(sql);
            ps.setInt(1, projet.getClient().getId());
            ps.setString(2, projet.getNom_Projet());
            ps.setDouble(3, projet.getMarge_Beneficiaire());
            ps.setString(4, projet.getEtat_Projet().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projet;
    }
*/

}
