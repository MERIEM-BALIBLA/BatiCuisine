package entity;

import entity.enums.EtatProjet;

import java.util.List;

public class Projet {
    private int id;
    private Client client;
    private String nom_Projet;
    private double marge_Beneficiaire;
    private double cout_Total = 0;
    private EtatProjet etat_Projet;
    private List<Composant> composants;

//    public Projet(Client client, String nom_Projet, double marge_Beneficiaire, EtatProjet etat_Projet) {
//        this.client = client;
//        this.nom_Projet = nom_Projet;
//        this.marge_Beneficiaire = marge_Beneficiaire;
//        this.etat_Projet = etat_Projet;
//    }


    public Projet(Client client, String nom_Projet, double marge_Beneficiaire, double cout_Total, EtatProjet etat_Projet) {
        this.client = client;
        this.nom_Projet = nom_Projet;
        this.marge_Beneficiaire = marge_Beneficiaire;
        this.cout_Total = cout_Total;
        this.etat_Projet = etat_Projet;
    }

    public Projet() {
    }

    public Projet(String nom_Projet, double marge_Beneficiaire, double cout_Total, EtatProjet etat_Projet) {
        this.nom_Projet = nom_Projet;
        this.marge_Beneficiaire = marge_Beneficiaire;
        this.cout_Total = cout_Total;
        this.etat_Projet = etat_Projet;
    }

    public void addComposant(Composant composant) {
        this.composants.add(composant);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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



//    public double setCout_Total(double cout_Total) {
//        this.cout_Total = cout_Total;
//        return cout_Total;
//    }

    public double setCout_Total(double cout_Total) {
        this.cout_Total = cout_Total;
        return this.cout_Total; // Assurez-vous que cela renvoie la valeur mise à jour
    }


    public EtatProjet getEtat_Projet() {
        return etat_Projet;
    }

    public List<Composant> getComposants() {
        return composants;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }

    public void setEtat_Projet(EtatProjet etat_Projet) {
        this.etat_Projet = etat_Projet;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", client=" + client +
                ", nom_Projet='" + nom_Projet + '\'' +
                ", marge_Beneficiaire=" + marge_Beneficiaire +
                ", cout_Total=" + cout_Total +
                ", etat_Projet=" + etat_Projet +
                ", composants=" + composants +
                '}';
    }
}
