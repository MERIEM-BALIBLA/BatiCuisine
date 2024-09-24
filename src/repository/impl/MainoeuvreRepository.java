package repository.impl;

import config.Connexion;
import entity.MainOeuvre;
import entity.Materiau;
import entity.Projet;
import entity.enums.ComposantType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainoeuvreRepository {
    private final Connexion connection;
    private static ProjetRepository projetRepository = new ProjetRepository();

    public MainoeuvreRepository() {
        this.connection = Connexion.getInstance();
    }

    public List<MainOeuvre> selectMainOeuvre(int projet_id) {
        Optional<Projet> projetOptional = projetRepository.afficherProjet(projet_id);
        List<MainOeuvre> mainOeuvres = new ArrayList<>();
        String sql = "SELECT * FROM mainoeuvre WHERE projet_id = ?";

        try (PreparedStatement ps = connection.connectToDB().prepareStatement(sql)) {
            ps.setInt(1, projet_id);
            ResultSet rs = ps.executeQuery();

            if (projetOptional.isPresent()) {
                Projet projet = projetOptional.get();
                while (rs.next()) {
                    String nom = rs.getString("nom");
                    double tauxTVA = rs.getDouble("tauxtva");
                    double taux_horaire = rs.getDouble("taux_horaire");
                    double heures_travail = rs.getDouble("heures_travail");
                    double productivite_ouvrier = rs.getDouble("productivite_ouvrier");

                    MainOeuvre mainOeuvre= new MainOeuvre(nom, ComposantType.MainOeuvre, tauxTVA, projet, taux_horaire, heures_travail, productivite_ouvrier);

                    mainOeuvres.add(mainOeuvre);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mainOeuvres;
    }

}
