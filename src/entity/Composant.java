package entity;

import entity.enums.ComposantType;

public abstract class Composant {
    private int id;
    private String nom;
    private ComposantType typeComposant;
    private double tauxTVA;
    private Projet projet;

    public Composant() {
    }

    public Composant(String nom, ComposantType typeComposant, double tauxTVA, Projet projet) {
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
        this.projet = projet;
    }

    //    public Composant(int id, String nom, ComposantType typeComposant, double tauxTVA, Projet projet) {
//        this.id = id;
//        this.nom = nom;
//        this.typeComposant = typeComposant;
//        this.tauxTVA = tauxTVA;
//        this.projet = projet;
//    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ComposantType getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(ComposantType typeComposant) {
        this.typeComposant = typeComposant;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        this.tauxTVA = tauxTVA;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
