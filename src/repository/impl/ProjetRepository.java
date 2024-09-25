package repository.impl;

import config.Connexion;
import entity.*;
import entity.enums.EtatProjet;
import repository.interfaces.ProjetInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetRepository implements ProjetInterface {

    private final Connexion connection;

    public ProjetRepository() {
        this.connection = Connexion.getInstance();
    }

    public Projet ajouterProjet(Projet projet) {
        String sql = "INSERT INTO projets (client_id, nom_projet, marge_beneficiaire, etat_projet,cout_total) VALUES (?, ?, ?, ?::etatprojet, ?)";

        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, projet.getClient().getId());
            ps.setString(2, projet.getNom_Projet());
            ps.setDouble(3, projet.getMarge_Beneficiaire());
            ps.setString(4, projet.getEtat_Projet().name());
            ps.setDouble(5, projet.getCout_Total());

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
                double cout = rs.getDouble("cout_total");

                Projet projet = new Projet(nom, marge, cout, etat);
                projet.setId(id);
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
        String sql = "SELECT p.id, p.nom_projet, p.marge_beneficiaire, p.etat_projet, p.cout_total, c.id AS client_id, c.nom AS client_nom "
                + "FROM projets p "
                + "JOIN clients c ON p.client_id = c.id";
        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomProjet = rs.getString("nom_projet");
                double margeBeneficiaire = rs.getDouble("marge_beneficiaire");
                EtatProjet etatProjet = EtatProjet.valueOf(rs.getString("etat_projet")); // Assurez-vous que l'énumération correspond
                double cout = rs.getDouble("cout_total");
                int clientId = rs.getInt("client_id");
                String clientNom = rs.getString("client_nom");

                Client client = new Client(clientId, clientNom); // Adapt this to your Client constructor

                Projet projet = new Projet(client, nomProjet, margeBeneficiaire, cout, etatProjet);
                projet.setId(id);

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


    public Boolean supprimerProjet(Projet projet) {
        Optional<Projet> projetOP = afficherProjet(projet.getId());
        int id = projetOP.get().getId();
        String sql = "DELETE FROM projets WEHERE id = ?";
        try {
            PreparedStatement ps = connection.connectToDB().prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Projet modifierEtatProjet(Projet projet) {
        String sql = "UPDATE projets SET etat_projet = CAST(? AS etatprojet) WHERE id = ?";
        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql)) {
            ps.setString(1, projet.getEtat_Projet().name()); // Use the enum's name
            ps.setInt(2, projet.getId()); // Set the project ID
            ps.executeUpdate(); // Execute the update
        } catch (SQLException e) {
            throw new RuntimeException(e); // Handle exceptions appropriately
        }
        return projet; // Return the updated project
    }

    public List<Projet> selectProjetParClient(int id){
        List<Projet> projets = new ArrayList<>();
        String sql ="SELECT * FROM projets WHERE client_id = ?";
        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
//                int id = rs.getInt("id");
                String nom = rs.getString("nom_projet");
                double marge = rs.getDouble("marge_beneficiaire");
                EtatProjet etat = EtatProjet.valueOf(rs.getString("etat_projet"));
                double cout = rs.getDouble("cout_total");

                Projet projet = new Projet(nom, marge, cout, etat);
                projet.setId(id);
                projets.add(projet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projets;
    }


}
