-- Database: movieDatabase
-- CREATE DATABASE movieDatabase CHARACTER SET utf8;

-- Table: movie
CREATE TABLE movieDatabase.movie (
  id   INT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nameRussian VARCHAR(50) NOT NULL,
  nameOriginal VARCHAR(50) NULL
) ENGINE = InnoDB;

INSERT INTO movieDatabase.movie VALUES (1, 'Бойцовский клуб', 'Fight Club');
INSERT INTO movieDatabase.movie VALUES (2, 'Темный рыцарь', 'Dark Knight');
INSERT INTO movieDatabase.movie VALUES (3, 'Джанго освобожденный', 'Django Unchained');
INSERT INTO movieDatabase.movie VALUES (4, 'Догма', 'Dogma');
INSERT INTO movieDatabase.movie VALUES (5, 'Форрест Гамп', 'Forrest Gump');
INSERT INTO movieDatabase.movie VALUES (6, 'Омерзительная восьмерка', 'The Hateful Eight');
INSERT INTO movieDatabase.movie VALUES (7, 'Зеленая миля', 'The Green Mile');
INSERT INTO movieDatabase.movie VALUES (8, 'Криминальное чтиво', 'Pulp Fiction');
INSERT INTO movieDatabase.movie VALUES (9, 'Прометей', 'Prometheus');

