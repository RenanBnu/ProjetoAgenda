CREATE DATABASE AgendaDB;

CREATE TABLE pessoa (
  id_pessoa INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100),
  data_nasc DATE,
  cpf VARCHAR(12),
  rg VARCHAR(7),
  data_cadastro DATE
); 
CREATE TABLE endereco (
  id_endereco INT PRIMARY KEY AUTO_INCREMENT,
  pessoa_id INT,
  rua VARCHAR(100),
  numero VARCHAR(10),
  complemento VARCHAR(100),
  cidade VARCHAR(50),
  estado VARCHAR(30),
  cep VARCHAR(8),
  data_cadastro DATE,
  FOREIGN KEY (pessoa_id) REFERENCES pessoa(id_pessoa),
); 
CREATE TABLE contato (
  id_contato INT PRIMARY KEY AUTO_INCREMENT,
  celular_1 VARCHAR(20),
  celular_2 VARCHAR(20),
  telefone VARCHAR(20),
  email VARCHAR(50),
  linkedin VARCHAR(100),
  instagram VARCHAR(100),
  facebook VARCHAR(100),
  pessoa_id INT,
  data_cadastro DATE,
  FOREIGN KEY (pessoa_id) REFERENCES pessoa(id_pessoa)
);