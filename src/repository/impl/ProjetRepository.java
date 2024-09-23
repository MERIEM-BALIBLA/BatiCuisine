package repository.impl;

import config.Connexion;
import entity.*;
import entity.enums.EtatProjet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    public Optional<Projet> afficherProjet(int projetId) {
        String sql = "SELECT * FROM projets WHERE id = ?";
        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql)) {
            ps.setInt(1, projetId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom_projet");
                double marge = rs.getDouble("marge_beneficiaire");
                EtatProjet etat = EtatProjet.valueOf(rs.getString("etat_projet"));

                Projet projet = new Projet(nom, marge, etat);
                projet.setId(id); // Définir l'ID récupéré
                return Optional.of(projet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<Projet> afficherTousLesProjets() {
        List<Projet> projets = new ArrayList<>();
//        String sql = "SELECT * FROM projets";
        String sql = "SELECT p.id, p.nom_projet, p.marge_beneficiaire, p.etat_projet, c.id AS client_id, c.nom AS client_nom "
                + "FROM projets p "
                + "JOIN clients c ON p.client_id = c.id";
        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomProjet = rs.getString("nom_projet");
                double margeBeneficiaire = rs.getDouble("marge_beneficiaire");
                EtatProjet etatProjet = EtatProjet.valueOf(rs.getString("etat_projet")); // Assurez-vous que l'énumération correspond
                int clientId = rs.getInt("client_id");
                String clientNom = rs.getString("client_nom");

                Client client = new Client(clientId, clientNom); // Adapt this to your Client constructor

                Projet projet = new Projet(client, nomProjet, margeBeneficiaire, etatProjet);
                projet.setId(id); // Set the ID

                projets.add(projet);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des projets : " + e.getMessage());
        }

        return projets;
    }

    public List<Composant> getComposantsByProjetId(int projetId) {
        List<Composant> composants = new ArrayList<>();
        String sql = "SELECT * FROM composants WHERE projet_id = ?";

        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql)) {
            ps.setInt(1, projetId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Récupérez les données nécessaires ici
                // Supposons que vous avez une classe Materiau et une classe MainOeuvre
                String type = rs.getString("type"); // Assurez-vous d'avoir un champ pour identifier le type

                if ("materiau".equalsIgnoreCase(type)) {
                    // Créez un objet Materiau et ajoutez-le à la liste
                    Materiau materiau = new Materiau();
                    materiau.setCoutUnitaire(rs.getDouble("cout_unitaire"));
                    materiau.setQuantite(rs.getDouble("quantite"));
                    materiau.setCoefficientQualite(rs.getDouble("coefficient_qualite"));
                    materiau.setCoutTransport(rs.getDouble("cout_transport"));
                    composants.add(materiau);
                } else if ("main_oeuvre".equalsIgnoreCase(type)) {
                    // Créez un objet MainOeuvre et ajoutez-le à la liste
                    MainOeuvre mainOeuvre = new MainOeuvre();
                    mainOeuvre.setTauxHoraire(rs.getDouble("taux_horaire"));
                    mainOeuvre.setHeuresTravail(rs.getDouble("heures_travail"));
                    mainOeuvre.setProductiviteOuvrier(rs.getDouble("productivite"));
                    composants.add(mainOeuvre);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des composants : " + e.getMessage());
        }

        return composants;
    }



}
