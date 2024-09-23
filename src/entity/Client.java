package entity;

public class Client {
    private int id;
    private String nom;
    private String adresse;
    private String telephone;
    private Boolean Professionnel;


    public Client(int id, String nom, String adresse, String telephone, Boolean professionnel) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        Professionnel = professionnel;
    }

    public Client(String nom, String adresse, String telephone, Boolean professionnel) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        Professionnel = professionnel;
    }

    public Client() {
    }

    public Client(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getEstProfessionnel() {
        return Professionnel;
    }

    public void setEstProfessionnel(Boolean estProfessionnel) {
        this.Professionnel = estProfessionnel;
    }

    @Override
    public String toString() {
        return "Client" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", Professionnel=" + Professionnel + "\n";
    }
}
