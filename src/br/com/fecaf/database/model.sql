CREATE DATABASE sistema_automotivo;
USE sistema_automotivo;


CREATE TABLE marcas (
                        idMarcas INT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL
);

CREATE TABLE modelos (
                         idModelos INT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         idMarcas INT,
                         FOREIGN KEY (idMarcas) REFERENCES marcas(idMarcas)
);

CREATE TABLE veiculos (
                          idVeiculos INT AUTO_INCREMENT PRIMARY KEY,
                          ano INT NOT NULL,
                          cor VARCHAR(50) NOT NULL,
                          preco DECIMAL(10, 2) NOT NULL,
                          quilometragem INT NOT NULL,
                          status VARCHAR(50) NOT NULL,
                          idModelos INT,
                          FOREIGN KEY (idModelos) REFERENCES modelos(idModelos)
);


INSERT INTO marcas (nome) VALUES ('Honda'), ('Chevrolet'), ('Volkswagen');

INSERT INTO modelos (nome, idMarcas) VALUES ('Civic', 1), ('Onix', 2), ('Gol', 3);

INSERT INTO veiculos (ano, cor, preco, quilometragem, status, idModelos)
VALUES
    (2020, 'Prata', 115000.00, 45000, 'DISPONIVEL', 1),
    (2022, 'Preto', 78000.00, 28000, 'DISPONIVEL', 2),
    (2018, 'Branco', 42000.00, 85000, 'VENDIDO', 3);
