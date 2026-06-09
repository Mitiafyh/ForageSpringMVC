insert into TypeDevis (nomType) values ('Etude'), ('Forage');

insert into
    Statut (nomStatut, sigle)
values ('demande cree', 'DC'),
    ('devis cree', 'DEC'),
    ('devis accepte', 'DEA'),
    ('devis refuse', 'DER'),
    ('forage cree', 'DFC'),
    ('forage accepte', 'DFA'),
    ('forage refuse', 'DFR'),
    ('forage commence', 'FC'),
    ('forage termine', 'FT');

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
values (1, 2,80, 160, 'Jaune'),
    (1, 2,60, 180, 'Rouge'),
    (2, 3,50, 67, 'Jaune'),
    (2, 3,70, 80, 'Rouge'),
    (2, 4,20, 160, 'Jaune'),
    (2, 4,30, 190, 'Rouge'),
    (4, 5,40, 100, 'Jaune'),
    (4, 5,60, 190, 'Rouge'),
    (5, 6,70, 132, 'Jaune'),
    (5, 6,90, 190, 'Rouge'),
    (5, 7,80, 186, 'Jaune'),
    (5, 7,100, 190, 'Rouge'),
    (7, 8,90, 166, 'Jaune'),
    (7, 8,60, 180, 'Rouge'),
    (8, 9,10, 19, 'Jaune'),
    (8, 9,80, 190, 'Rouge'),
    (1, 4,100, 150, 'Rouge'),
    (1, 4,120, 180, 'Rouge');