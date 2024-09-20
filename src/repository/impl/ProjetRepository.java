package repository.impl;

import config.Connexion;
import entity.Projet;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class ProjetRepository {

    private final Connexion connection;

    public ProjetRepository() {
        this.connection = Connexion.getInstance();
    }

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

}
