package repository.impl;

import config.Connexion;
import entity.Devis;
import repository.interfaces.DevisInterface;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DevisRepository implements DevisInterface {
    private final Connexion connection;
    private static ProjetRepository projetRepository = new ProjetRepository();

    public DevisRepository() {
        this.connection = Connexion.getInstance();
    }

    @Override
    public Devis ajouterDevis(Devis devis) {
        String sql = "INSERT INTO devis (montant_estime, date_emission, date_validite, accepte, projet_id) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = connection.connectToDB().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, devis.getMontantEstime());
            statement.setDate(2, java.sql.Date.valueOf(devis.getDateEmission()));
            statement.setDate(3, java.sql.Date.valueOf(devis.getDateValidite()));
            statement.setBoolean(4, devis.isAccepte());
            statement.setInt(5, devis.getProjet().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return devis;
    }

}
