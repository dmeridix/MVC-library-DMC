CREATE DATABASE IF NOT EXISTS library;
USE library;

CREATE TABLE books (
    id_llibre INT PRIMARY KEY AUTO_INCREMENT,
    titol VARCHAR(255),
    autor VARCHAR(255),
    editorial VARCHAR(255),
    datapublicacio date,
    tematica VARCHAR(2515)
    isbn VARCHAR(13) unique NOT NULL,
);