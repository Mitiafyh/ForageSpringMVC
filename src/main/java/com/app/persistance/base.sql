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
insert into TypeDevis(nomType) values
('Etude'),
('Forage');

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
insert into Parametre(idStatut1, idStatut2, DT, alerte) values
(1, 2, 120.0, 'Jaune'),
(1, 2, 300.0, 'Rouge'),
(2, 3, 120.0, 'Jaune'),
(2, 3, 300.0, 'Rouge'),
(3, 4, 120.0, 'Jaune'),
(3, 4, 300.0, 'Rouge');




insert into Client(nom,contact,adresse) values('Mitia','034 12 345 67','Lot II M 34 bis');
insert into Client(nom,contact,adresse) values('Rasoa','032 98 76 54 32','Lot II M 34 bis');
insert into Client(nom,contact,adresse) values('Noah','032 98 76 54 32','Lot II M 34 bis');



insert into Statut(nomStatut, sigle) values
('demande cree', 'DC'),
('devis cree', 'DEC'),
('devis accepte', 'DEA'),
('devis refuse', 'DER'),
('forage cree', 'DFC'),
('forage accepte', 'DFA'),
('forage refuse', 'DFR'),
('forage commence', 'FC'),
('forage termine', 'FT');

insert into User(nom,mdp) values
('admin', 'admin123'),
('user1', 'password1'),
('user2', 'password2');

insert into Region(nomRegion) values
('Analamanga'),
('Alaotra-Mangoro'),
('Amoroni Mania'),
('Atsinanana'),
('Betsiboka'),
('Boeny'),
('Bongolava'),
('Diana'),
('Haute Matsiatra'),
('Ihorombe'),
('Itasy'),
('Melaky'),
('Menabe'),
('Sava'),
('Sofia'),
('Vakinankaratra'),
('Vatovavy-Fitovinany');

insert into District(nomDistrict, idRegion) values
('Antananarivo', 1),
('Toamasina', 4),
('Mahajanga', 6),
('Antsiranana', 8),
('Fianarantsoa', 9);

insert into Commune(nomCommune, idDistrict) values
('Ankazobe', 1),
('Ambohidratrimo', 1),
('Andramasina', 1),
('Anjozorobe', 1),
('Antananarivo Renivohitra', 1),
('Toamasina I', 2),
('Toamasina II', 2),
('Ambilobe', 3),
('Antsiranana I', 3),
('Antsiranana II', 4),
('Fianarantsoa I', 5),
('Fianarantsoa II', 5);



