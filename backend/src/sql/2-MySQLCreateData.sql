INSERT INTO Category(id, name)
VALUES (1, 'Portátiles');
INSERT INTO Category(id, name)
VALUES (2, 'Pantallas');

INSERT INTO User(id, userName, password, firstName, lastName, email, role)
VALUES (1,
        'usuario1',
        '$2a$10$zZnDjiu51rH1SeuL6HqH9ORE1ZHRaJGLBzNi.vjYfhLJ5ZVYF0RMC',
        'Usuario',
        '1',
        'usuario1@pa-gei.udc.es',
        0);

INSERT INTO User(id, userName, password, firstName, lastName, email, role)
VALUES (2,
        'usuario2',
        '$2a$10$zZnDjiu51rH1SeuL6HqH9ORE1ZHRaJGLBzNi.vjYfhLJ5ZVYF0RMC',
        'Usuario',
        '2',
        'usuario2@pa-gei.udc.es',
        0);

INSERT INTO User(id, userName, password, firstName, lastName, email, role)
VALUES (3,
        'usuario3',
        '$2a$10$zZnDjiu51rH1SeuL6HqH9ORE1ZHRaJGLBzNi.vjYfhLJ5ZVYF0RMC',
        'Usuario',
        '3',
        'usuario3@pa-gei.udc.es',
        0);

INSERT INTO User(id, userName, password, firstName, lastName, email, role)
VALUES (4,
        'admin',
        '$2a$10$zZnDjiu51rH1SeuL6HqH9ORE1ZHRaJGLBzNi.vjYfhLJ5ZVYF0RMC',
        'admin',
        'admin',
        'admin@pa-gei.udc.es',
        0);

INSERT INTO User(id, userName, password, firstName, lastName, email, role)
VALUES (5,
        'test',
        '$2a$10$71bIh7PjPIAYpQ4v/M.VIu.dFePYqpBcQHwOyxDFj6R46bbkVL23m',
        'mrtest',
        'testson',
        'test@pa-gei.udc.es',
        0);

INSERT INTO Product(userId, name, description, publicationDate, expirationDate, startPrice, currentPrice, categoryId,
                    shipInfo, winningBid, hasBidder, version)
VALUES (1,
        'Portátil 1',
        'Mejor portátil del mercado.',
        '2021-01-20 14:33:17',
        '2021-01-25 14:33:17',
        1300,
        1478,
        1,
        'Envío gratis a península en 10 días.',
        null,
        0,
        1);

INSERT INTO Product(userId, name, description, publicationDate, expirationDate, startPrice, currentPrice, categoryId,
                    shipInfo, winningBid, hasBidder, version)
VALUES (1,
        'Portátil 2',
        'Casi el Mejor portátil del mercado.',
        '2022-01-01 00:00:00',
        '2022-09-01 00:00:00',
        10,
        10,
        1,
        'Envío gratis a península en 10 días.',
        null,
        0,
        1);

INSERT INTO Product(userId, name, description, publicationDate, expirationDate, startPrice, currentPrice, categoryId,
                    shipInfo, winningBid, hasBidder, version)
VALUES (1,
        'Pantalla 1',
        'Perfecta para el Mejor portátil del mercado.',
        '2022-01-01 00:00:00',
        '2022-10-01 00:00:00',
        500,
        532,
        2,
        'Envío gratis a península en 10 días.',
        null,
        0,
        1);

INSERT INTO Product(userId, name, description, publicationDate, expirationDate, startPrice, currentPrice, categoryId,
                    shipInfo, winningBid, hasBidder, version)
VALUES (1,
        'Pantalla 2',
        'Perfecta para el Casi Mejor portátil del mercado.',
        '2022-01-01 00:00:00',
        '2022-11-01 00:00:00',
        400,
        417,
        2,
        'Envío gratis a península en 10 días.',
        null,
        0,
        1);

INSERT INTO Bid(id, userId, price, productId, bidDate, bidStatus)

VALUES (1,
        4,
        0,
        2,
        '2022-11-01 00:00:00',
       1);

UPDATE Product
SET WinningBid = 1
WHERE id = 1;

UPDATE Product
SET WinningBid = 1
WHERE id = 2;

UPDATE Product
SET WinningBid = 1
WHERE id = 3;

UPDATE Product
SET WinningBid = 1
WHERE id = 4;

