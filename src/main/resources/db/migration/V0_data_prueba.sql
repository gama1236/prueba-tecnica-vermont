CREATE TABLE users
(
    ID       serial PRIMARY KEY,
    Fullname character varying(255),
    Phone    character varying(20),
    Address  character varying(255)
);

INSERT INTO users (Fullname, Phone, Address)
VALUES ('Thomson, Elias', '555-8596', 'Diamond St. 4G3 NY'),
       ('Simond, Ella', '415-9687', 'Fleet st. 45 B, 56 BR-NY'),
       ('Clifford, Thomas', '416-69883', 'Meet town, 45 - FL');