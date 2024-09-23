package repository.impl;

import config.Connexion;
import entity.Materiau;
import entity.Projet;
import entity.enums.ComposantType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MateriauRepository {

    private final Connexion connection;
    private static ProjetRepository projetRepository = new ProjetRepository();

    public MateriauRepository() {
        this.connection = Connexion.getInstance();
    }

    public List<Materiau> selectMateriaux(int projet_id) {
        Optional<Projet> projetOptional = projetRepository.afficherProjet(projet_id);
        List<Materiau> materiaux = new ArrayList<>();
        String sql = "SELECT * FROM materiaux WHERE projet_id = ?";

        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql)) {
            ps.setInt(1, projet_id);
            ResultSet rs = ps.executeQuery();

            if (projetOptional.isPresent()) {
                Projet projet = projetOptional.get();
                while (rs.next()) {
                    String nom = rs.getString("nom");
                    double tauxTVA = rs.getDouble("tauxtva");
                    double coutUnitaire = rs.getDouble("cout_unitaire");
                    double quantite = rs.getDouble("quantite");
                    double coutTransport = rs.getDouble("cout_transport");
                    double coefficientQualite = rs.getDouble("coefficient_qualite");

                    Materiau materiau = new Materiau(nom, ComposantType.Materiau, tauxTVA, projet, coutUnitaire, quantite, coutTransport, coefficientQualite);

                    materiaux.add(materiau);
                }
            } else {
                System.out.println("Aucun projet trouvé avec l'ID " + projet_id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return materiaux;
    }

}
