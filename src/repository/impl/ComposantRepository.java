package repository.impl;

import config.Connexion;
import entity.Composant;
import entity.MainOeuvre;
import entity.Materiau;
import entity.Projet;
import entity.enums.ComposantType;
import repository.interfaces.ComposantInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComposantRepository implements ComposantInterface {

    private final Connexion connection;
    private static ProjetRepository projetRepository = new ProjetRepository();

    public ComposantRepository() {
        this.connection = Connexion.getInstance();
    }

    @Override
    public Composant ajouterComposant(Composant composant) {
        String sql = null;
        if (composant instanceof Materiau) {
            sql = "INSERT INTO materiaux (nom, type_composant, tauxtva,projet_id, cout_unitaire, quantite, cout_transport, coefficient_qualite ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else if (composant instanceof MainOeuvre) {
            sql = "INSERT INTO mainoeuvre (nom, type_composant, tauxtva,projet_id, taux_horaire, heures_travail, productivite_ouvrier) VALUES (?, ?, ?, ?, ?, ?, ?)";
        }
        if (sql != null) {
            Connection conn = connection.connectToDB();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, composant.getNom());
                ps.setString(2, composant.getTypeComposant().name()); // Utilisez .name() pour obtenir le nom de l'énumération
                ps.setDouble(3, composant.getTauxTVA());
                ps.setInt(4, composant.getProjet().getId());

                if (composant instanceof Materiau) {
                    Materiau materiau = (Materiau) composant;
                    ps.setDouble(5, materiau.getCoutUnitaire());
                    ps.setDouble(6, materiau.getQuantite());
                    ps.setDouble(7, materiau.getCoutTransport());
                    ps.setDouble(8, materiau.getCoefficientQualite());
                } else {
                    MainOeuvre mainOeuvre = (MainOeuvre) composant;
                    ps.setDouble(5, mainOeuvre.getTauxHoraire());
                    ps.setDouble(6, mainOeuvre.getHeuresTravail());
                    ps.setDouble(7, mainOeuvre.getProductiviteOuvrier());
                }
                ps.executeUpdate();
                return composant;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return composant;
    }

}
