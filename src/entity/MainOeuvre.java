package entity;

public class MainOeuvre extends Composant {
    private double tauxHoraire;
    private double heuresTravail;
    private double productiviteOuvrier;

//    public MainOeuvre(int id, String nom, String typeComposant, double tauxTVA, double tauxHoraire, double heuresTravail, double productiviteOuvrier) {
//        super(id, nom, typeComposant, tauxTVA);
//        this.tauxHoraire = tauxHoraire;
//        this.heuresTravail = heuresTravail;
//        this.productiviteOuvrier = productiviteOuvrier;
//    }

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

//    public double calculerCoutTotal() {
//        return (tauxHoraire * heuresTravail * productiviteOuvrier) * (1 + tauxTVA / 100);
//    }
}
