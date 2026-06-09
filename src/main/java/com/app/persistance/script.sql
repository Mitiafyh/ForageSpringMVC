insert into TypeDevis (nomType) values ('Etude'), ('Forage');

insert into
    Statut (nomStatut, sigle)
values ('demande cree', 'DC'),1
    ('devis cree', 'DEC'),2
    ('devis accepte', 'DEA'),3
    ('devis refuse', 'DER'),
    ('forage cree', 'DFC'),4
    ('forage accepte', 'DFA'),5
    ('forage refuse', 'DFR'),
    ('forage commence', 'FC'),6
    ('forage termine', 'FT');7
   

insert into
    User (nom, mdp)
values ('admin', 'admin123'),
    ('user1', 'password1'),
    ('user2', 'password2');

insert into
    Region (nomRegion)
values ('Analamanga'),
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

insert into
    District (nomDistrict, idRegion)
values ('Antananarivo', 1),
    ('Toamasina', 4),
    ('Mahajanga', 6),
    ('Antsiranana', 8),
    ('Fianarantsoa', 9);

insert into
    Commune (nomCommune, idDistrict)
values ('Ankazobe', 1),
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

insert into
    Client (nom, contact, adresse)
values (
        'Mitia',
        '034 12 345 67',
        'Lot II M 34 bis'
    );

insert into
    Client (nom, contact, adresse)
values (
        'Rasoa',
        '032 98 76 54 32',
        'Lot II M 34 bis'
    );

insert into
    Client (nom, contact, adresse)
values (
        'Noah',
        '032 98 76 54 32',
        'Lot II M 34 bis'
    );

insert into Parametre (idStatut1,idStatut2,DT,DT2,alerte)
values (1, 2,480, 600, 'Jaune'),
(1,2,1440,2880,'Rouge'),
(2,3,240,300,'Jaune'),
(2,3,360,480,'Rouge'),
(3,5,60,120,'Jaune'),
(3,5,180,240,'Rouge'),
(5,6,240,480,'Jaune'),
(5,6,600,720,'Rouge'),
(6,8,1200,1800,'Jaune'),
(6,8,1801,3600,'Rouge'),
(8,9,3600,4200,'Jaune'),
(8,9,4800,6000,'Rouge');

   