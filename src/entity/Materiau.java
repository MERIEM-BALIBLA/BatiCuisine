package entity;

import entity.enums.ComposantType;

public class Materiau extends Composant {
    private double coutUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQualite;

    public Materiau() {
    }

    public Materiau(String nom, ComposantType typeComposant, double tauxTVA, Projet projet, double coutUnitaire, double quantite, double coutTransport, double coefficientQualite) {
        super(nom, typeComposant, tauxTVA, projet);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public Materiau(String nom, ComposantType typeComposant, double tauxTVA, Projet projet) {
        super(nom, typeComposant, tauxTVA, projet);
    }

    public Materiau(double coutUnitaire, double quantite, double coutTransport, double coefficientQualite) {
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    public double calculerCoutMateriau() {
        return (coutUnitaire * quantite * coefficientQualite) + coutTransport * (1 + getTauxTVA()/100);
    }

    @Override
    public String toString() {
        return "Materiau{" +
                "coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                '}';
    }
}
