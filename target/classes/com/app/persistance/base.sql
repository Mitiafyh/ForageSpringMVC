create database forage;
use forage;


-- Table User
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    mdp VARCHAR(255) NOT NULL
);

-- Table Client
CREATE TABLE Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    contact VARCHAR(50),
    adresse VARCHAR(255)
);

-- Table Region
CREATE TABLE Region (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomRegion VARCHAR(100) NOT NULL
);

-- Table District
CREATE TABLE District (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomDistrict VARCHAR(100) NOT NULL,
    idRegion INT,
    FOREIGN KEY (idRegion) REFERENCES Region(id)
);

-- Table Commune
CREATE TABLE Commune (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomCommune VARCHAR(100) NOT NULL,
    idDistrict INT,
    FOREIGN KEY (idDistrict) REFERENCES District(id)
);

-- Table Demande
CREATE TABLE Demande (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idClient INT,
    idCommune INT,
    lieu VARCHAR(255),
    FOREIGN KEY (idClient) REFERENCES Client(id) ,
    FOREIGN KEY (idCommune) REFERENCES Commune(id)
   
);

-- Table Statut
CREATE TABLE Statut (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomStatut VARCHAR(100) NOT NULL,
    sigle VARCHAR(10) NOT NULL
);

-- Table DemandeStatut
CREATE TABLE DemandeStatut (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idDemande INT,
    idStatut INT,
    date DATETIME,
    DT DOUBLE,
    idUser INT,
    observation TEXT,
    FOREIGN KEY (idDemande) REFERENCES Demande(id) ON DELETE CASCADE,
    FOREIGN KEY (idStatut) REFERENCES Statut(id),
    FOREIGN KEY (idUser) REFERENCES User(id)
);


-- Table Devis
CREATE TABLE Devis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idDemande INT,
    idTypeDevis INT,
    FOREIGN KEY (idDemande) REFERENCES Demande(id)
);

CREATE TABLE SousDevis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idDevis INT,
    description TEXT,
    prixUnitaire DOUBLE,
    quantite INT,
    FOREIGN KEY (idDevis) REFERENCES Devis(id)
);


create table TypeDevis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomType VARCHAR(100) NOT NULL
);

create table utilitaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idStatut INT,
    nomUtilitaire VARCHAR(100) NOT NULL
);
create Table Parametre(
    id INT AUTO_INCREMENT PRIMARY KEY,
    idStatut1 INT,
    idStatut2 INT,
    DT DOUBLE,
    alerte VARCHAR(20)
);
