# Tecnow
Plataforma de eventos do universo Geek: Jogos e Animes, onde os participantes do evento podem se cadastrar e cadastrar seus animes e jogos favoritos.
CRUD de usuários, animes e jogos, autenticação, autorização, permissão utilizando a linguagem Java, Spring MVC, Hibernate e Java Serves Pages(JSP) com páginas estilizadas através da framework Materialize.

### Utilização
Neste exemplo, a utilização é do banco de dados MySQL.
Para editar as configurações do banco de dados MySQL, acesse a classe br.com.tecnow.dao.jdbc.FabricaDeConexoes e configure.

## Criar o banco de dados
CREATE SCHEMA tecnow;

## Colocá-lo em uso
USE tecnow;

## Criar a tabela de usuarios
CREATE TABLE usuario (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(60) NOT NULL,
data_nascimento DATE NOT NULL,
sexo ENUM('FEMININO', 'MASCULINO') NOT NULL,
email VARCHAR(120) NOT NULL UNIQUE,
senha VARCHAR(32) NOT NULL
);

## Criar a tabela de animes
CREATE TABLE anime (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
Nome VARCHAR(40) NOT NULL,
data_cadastro DATE,
id_usuario BIGINT
);
ALTER TABLE anime ADD FOREIGN KEY(id_usuario) REFERENCES usuario (id);


## Criar a tabela de jogos
CREATE TABLE jogo (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
Nome VARCHAR(40) NOT NULL,
Categoria ENUM('TIRO', 'RPG', 'PLATAFORMA', 'ESPORTE', 'HACK_AND_SLASH', 'OUTRO') NOT NULL,
data_cadastro DATE,
id_usuario BIGINT
);
ALTER TABLE jogo ADD FOREIGN KEY(id_usuario) REFERENCES usuario (id);
