package entity;

import entity.enums.ComposantType;

public class MainOeuvre extends Composant {
    private double tauxHoraire;
    private double heuresTravail;
    private double productiviteOuvrier;

    public MainOeuvre(String nom, ComposantType typeComposant, double tauxTVA, Projet projet, double tauxHoraire, double heuresTravail, double productiviteOuvrier) {
        super(nom, typeComposant, tauxTVA, projet);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public MainOeuvre(String nom, ComposantType typeComposant, double tauxTVA, Projet projet) {
        super(nom, typeComposant, tauxTVA, projet);
    }

    public MainOeuvre() {
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public double calculerCoutMainOeuvre() {
        return tauxHoraire * heuresTravail * productiviteOuvrier * (1 + getTauxTVA()/100);
    }

    @Override
    public String toString() {
        return "MainOeuvre{" +
                "tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                ", tva =" + getTauxTVA() +

                '}';
    }
}
