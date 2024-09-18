package repository.impl;

import config.Connexion;
import entity.Client;
import repository.interfaces.ClientInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ll implements ClientInterface {
    private final Connexion connection;

    public ll() {
        this.connection = Connexion.getInstance();
    }

    @Override
    public List<Client> clientsListe() {
        List<Client> liste = new ArrayList<>();
        String query = "SELECT * FROM clients"; // Example query

        try (Connection conn = connection.connectToDB();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Client client = new Client(rs.getInt("id"), rs.getString("nom"),
                        rs.getString("adresse"), rs.getString("telephone"),
                        rs.getBoolean("professionnel"));
                liste.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public Client ajouterClient(String nom, String adresse, String telephone, boolean professionnel) {
        String query = "INSERT INTO clients (nom, adresse, telephone, professionnel) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.connectToDB().prepareStatement(query);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, adresse);
            preparedStatement.setString(3, telephone);
            preparedStatement.setBoolean(4, professionnel);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Client added successfully!");
                return new Client(nom, adresse, telephone, professionnel);
            } else {
                System.out.println("Failed to add client.");
            }
        } catch (SQLException e) {
            System.out.println("Error while adding client: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Client> foundClient(int id) {
        try {
            String sql = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement ps = connection.connectToDB().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                String tele = rs.getString("telephone");
                boolean pro = rs.getBoolean("professionnel");
                return Optional.of(new Client(id, nom, adresse, tele, pro));
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return Optional.empty();

    }

    @Override
    public boolean supprimerClient(int id) {
        String resultMessage = "Client supprimé avec succès.";
        try {
            String sql = "DELETE FROM clients WHERE id=?";
            PreparedStatement ps = connection.connectToDB().prepareStatement(sql);

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            resultMessage = "Erreur lors de la suppression du client: " + e.getMessage();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Client modifierClient(int id, String nouveauNom, String nouveauAdresse, String nouveauTelephone, boolean nouveauProfessionnel) {
        try {
            String sql = "UPDATE clients SET nom = ?, adresse = ?, telephone = ?, professionnel = ? WHERE id = ?";
            PreparedStatement ps = connection.connectToDB().prepareStatement(sql);
            ps.setString(1, nouveauNom);
            ps.setString(2, nouveauAdresse);
            ps.setString(3, nouveauTelephone);
            ps.setBoolean(4, nouveauProfessionnel);
            ps.setInt(5, id);
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                return new Client(id, nouveauNom, nouveauAdresse, nouveauTelephone, nouveauProfessionnel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}


