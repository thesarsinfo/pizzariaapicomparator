
create table tb_pizza (
    id VARBINARY(36) PRIMARY KEY,
    nome VARCHAR(25) NOT NULL UNIQUE,
    categoria VARCHAR(25) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    urlimagem VARCHAR(255) NOT NULL UNIQUE
);
create table tb_pizzaria (
    id VARBINARY(36) PRIMARY KEY,
    nome VARCHAR(25) NOT NULL UNIQUE,
    endereco VARCHAR(100) NOT NULL UNIQUE,
    cep VARCHAR(10) NOT NULL,
    cidade VARCHAR(25) NOT NULL,
    site VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL,
    avaliacao float(3,2) NOT NULL,
    CONSTRAINT chk_avaliacao CHECK 
            ( avaliacao >= 0 AND avaliacao <= 5) 
);
create table tb_pizzapizzaria (
    id VARBINARY(36) PRIMARY KEY,
    pizzaria_id VARBINARY(36) NOT NULL ,
    pizza_id VARBINARY(36) NOT NULL,
    preco decimal(5,2) NOT NULL
    FOREIGN KEY fk_pizzaria (pizzaria_id) REFERENCES tb_pizzaria(id),
    FOREIGN KEY fk_pizza (pizza_id) REFERENCES tb_pizza(id)
);
create table tb_usuario (
    id VARBINARY(36) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    nome VARCHAR(25) NOT NULL ,
    senha VARCHAR(255) NOT NULL, 
    CONSTRAINT CHK_email_arroba CHECK (email LIKE '%@%'),
    CONSTRAINT CHK_email_com CHECK (email LIKE '.com'),
    CONSTRAINT CHK_senha CHECK (char_length(senha) >= 8)
);
create table tb_role (
    id VARBINARY(36) PRIMARY KEY,
    rolename VARCHAR(255) NOT NULL UNIQUE
); 
create table tb_usuario_roles (
    usuario_id VARBINARY(36) NOT NULL KEY,
    role_id VARBINARY(36) NOT NULL KEY,
    FOREIGN KEY fk_usuario (usuario_id) REFERENCES tb_usuario(id),
    FOREIGN KEY fk_role (role_id) REFERENCES tb_role(id)
);

