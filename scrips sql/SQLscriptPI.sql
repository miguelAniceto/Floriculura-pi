create database floriculturaPI;
USE floriculturaPi;

CREATE TABLE Produto(
	idProduto INT NOT NULL auto_increment,
    nomeProduto VARCHAR(30) NOT NULL,
    tipoProduto VARCHAR(30) NOT NULL,
    qtdProduto INT NOT NULL,
    valorProduto FLOAT NOT NULL,
    primary key (idProduto)
);

CREATE TABLE Cliente(
	idCliente INT NOT NULL auto_increment,
    CPF VARCHAR(14) NOT NULL,
    nomeCliente varchar(30) NOT NULL,
    emailCliente VARCHAR(50) NOT NULL,
    telefoneCliente VARCHAR(14) NOT NULL,
    enderecoCliente VARCHAR(50) NOT NULL,
    dataNasc date NOT NULL,
    estadoCivil varchar(30) NOT NULL,
    sexoCliente varchar(9),
    primary key (idCliente)
);

CREATE TABLE Venda(
	idVenda int NOT NULL AUTO_INCREMENT,
    dataVenda Date NOT NULL,
    valorVenda FLOAT NOT NULL,
    idCliente INT NOT NULL,
    
    PRIMARY KEY (idVenda)
);

CREATE TABLE ItemVenda(
 idItemVenda int NOT NULL auto_increment,
 idVenda int NOT NULL,
 idProduto int NOT NULL,
 qtdProduto int NOT NULL,
 valorUnitario float NOT NULL,
 
 primary key(idItemVenda),
 foreign key(idVenda) references Venda(idVenda),
 foreign key(idProduto) references Produto(idProduto)
);



