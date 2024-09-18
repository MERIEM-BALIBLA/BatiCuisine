package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    private static Connexion instance;
    private Connection connection;
    private String dbName = "Bati-Cuisine";
    private String user = "GreenPulse";
    private String password = "";

    private Connexion() {}

    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    public Connection connectToDB() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/" + dbName;
            Connection connection = DriverManager.getConnection(url, user, password);
//            if (connection != null) {
//                System.out.println("Connected successfully");
//            } else {
//                System.out.println("Connection failed");
//            }
            return connection;
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
// Method to connect to the database
}
