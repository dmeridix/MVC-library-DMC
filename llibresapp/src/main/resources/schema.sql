CREATE DATABASE IF NOT EXISTS library;
USE library;

CREATE TABLE IF NOT EXISTS books (
    id_llibre INT PRIMARY KEY AUTO_INCREMENT,
    titol VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editorial VARCHAR(255),
    datapublicacio DATE,
    tematica VARCHAR(255),
    isbn VARCHAR(13) UNIQUE NOT NULL
);