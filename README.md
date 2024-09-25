# Bati-Cuisine

Bati-Cuisine est une application Java destinée aux professionnels de la construction et de la rénovation, permettant d'estimer les coûts des projets de cuisine. Elle calcule le coût total des travaux en tenant compte des matériaux utilisés et des frais de main-d'œuvre, facturés à l'heure. Parmi ses principales fonctionnalités, on trouve la gestion des clients, la création de devis personnalisés, ainsi qu'une vue d'ensemble des aspects financiers et logistiques liés aux projets de rénovation.
## Fonctionnalitées :
### 1/ Getsion des projets :
#### - Association du projet avec client 
#### - Getsion des composants du projet
#### - Calcul du cout 
#### - Les détails du projet :
        nom_orojet (String): Nom du projet.
        marge_beneficiaire (double): Marge bénéficiaire appliquée au coût total.
        etat_projet (Enum): Statut du proejt ( En_cours, Termine, Annule;).
        cout_total (double): Cout total du projet.
### 2/ Gestion des composants :
#### Composants :
        nom (String): nom du composants
        type_composant (Enum) : type du composant (MainOeuvre, Materiau )
        tauxtva (double): Taux de TVA applicable au composant
#### Materiaux :
        cout_unitaire (double): Coût unitaire du composant
        quantite (double): Quantite utilisé 
        cout_transport (double): Coût de transport du matériau.
        coefficient_qualite (double): Coefficient de qualité du matériau. 
#### MainOeuvre :
        taux_horaire (double): Taux horaire de la main-d'œuvre
        heures_travail (double): Heures travaillées
        productivite_ouvrier (double): Facteur de productivité des ouvriers.
### 3/ Getsion des clients :
Enregistrements Clients : 
        Enregistrer les informations de base des clients et différencier entre les clients professionnels et particuliers.
Remises : Appliquer des remises spécifiques en fonction du type de client (par exemple, clients réguliers ou professionnels).
Détails du Client :
        nom (String) : Nom du client.
        adresse (String) : Adresse du client.
        telephone (String) : Numéro de téléphone du client.
        professionnel (boolean) : Indique si le client est un professionnel.
### 4/ Gestion des devis :
Création de Devis : 
        Élaborer des devis avant le début des travaux, incluant les estimations des coûts des matériaux, de la main-d'œuvre, des équipements et des taxes.
Détails du Devis :
        montant_estime (double) : Montant estimé du projet basé sur les devis.
        date_emission (Date) : Date d'émission du devis.
        date_validite (Date) : Date de validité du devis.
        accepte (boolean) : Indique si le devis a été accepté par le client.
### 5/ Calcul du cout :
Intégration des Coûts : 
        Inclure les coûts des composants (matériaux et main-d'œuvre) dans le calcul du coût total du projet.
Marge Bénéficiaire : 
        Appliquer une marge bénéficiaire pour déterminer le coût final du projet.
Taxes et Remises : 
        Prendre en compte la TVA applicable et les remises spécifiques au client.
Ajustements de Coûts : 
        Ajuster les coûts en fonction de la qualité des matériaux ou de la productivité de la main-d'œuvre.
# Installation :
  Cloner le dépôt :
                  https://github.com/MERIEM-BALIBLA/BatiCuisine.git


