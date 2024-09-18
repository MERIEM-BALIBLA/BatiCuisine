package entity;

import entity.enums.EtatProjet;

public class Projet {
    private int id;
    private Client client;
    private String nom_Projet;
    private double marge_Beneficiaire;
    private double cout_Total;
    private EtatProjet etat_Projet;

    public Projet(int id, Client client, String nom_Projet, double marge_Beneficiaire, double cout_Total, EtatProjet etat_Projet) {
        this.id = id;
        this.client = client;
        this.nom_Projet = nom_Projet;
        this.marge_Beneficiaire = marge_Beneficiaire;
        this.cout_Total = cout_Total;
        this.etat_Projet = etat_Projet;
    }

    public Projet() {
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getNom_Projet() {
        return nom_Projet;
    }

    public void setNom_Projet(String nom_Projet) {
        this.nom_Projet = nom_Projet;
    }

    public double getMarge_Beneficiaire() {
        return marge_Beneficiaire;
    }

    public void setMarge_Beneficiaire(double marge_Beneficiaire) {
        this.marge_Beneficiaire = marge_Beneficiaire;
    }

    public double getCout_Total() {
        return cout_Total;
    }

    public void setCout_Total(double cout_Total) {
        this.cout_Total = cout_Total;
    }

    public EtatProjet getEtat_Projet() {
        return etat_Projet;
    }

    public void setEtat_Projet(EtatProjet etat_Projet) {
        this.etat_Projet = etat_Projet;
    }
}
