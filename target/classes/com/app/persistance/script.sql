insert into TypeDevis(nomType) values
('Etude'),
('Forage');

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

insert into Client(nom,contact,adresse) values('Mitia','034 12 345 67','Lot II M 34 bis');
insert into Client(nom,contact,adresse) values('Rasoa','032 98 76 54 32','Lot II M 34 bis');
insert into Client(nom,contact,adresse) values('Noah','032 98 76 54 32','Lot II M 34 bis');

insert into Parametre(idStatut1, idStatut2, DT, alerte) values
(1, 2, 60.0, 'Jaune'),
(1, 2, 120.0, 'Rouge'),
(2, 3, 60.0, 'Jaune'),
(2, 3, 120.0, 'Rouge'),
(3, 4, 60.0, 'Jaune'),
(3, 4, 120.0, 'Rouge'),
(4, 5, 60.0, 'Jaune'),
(4, 5, 120.0, 'Rouge'),
(5, 6, 60.0, 'Jaune'),
(5, 6, 120.0, 'Rouge'),
(6, 7, 60.0, 'Jaune'),
(6, 7, 120.0, 'Rouge'),
(7, 8, 60.0, 'Jaune'),
(7, 8, 120.0, 'Rouge');





