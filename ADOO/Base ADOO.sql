CREATE DATABASE INE;
USE INE;
CREATE TABLE ELECTOR(
CE varchar(18) not null primary key,
Nombre varchar(40),
Sexo char(1),
Estado int,
Seccion int 
)ENGINE=InnoDB;

CREATE TABLE VOTO(
CV int not null primary key,
CE varchar(18) NOT NULL,
FOREIGN KEY(CE) REFERENCES ELECTOR(CE)
ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB;

CREATE TABLE BOL_GOB(
CB int not null primary key,
CV INT NOT NULL,
FOREIGN KEY(CV) REFERENCES VOTO(CV),
Partido varchar(25),
Candidato varchar(35)
)ENGINE=InnoDB;

CREATE TABLE BOL_DIP(
CB int not null primary key,
CV INT NOT NULL,
FOREIGN KEY(CV) REFERENCES VOTO (CV),
Partido varchar(25),
Candidato varchar(35)
)ENGINE=InnoDB;

CREATE TABLE BOL_PRE(
CB int not null primary key,
CV INT NOT NULL,
FOREIGN KEY(CV) REFERENCES VOTO(CV),
Partido varchar(25),
Candidato varchar(35)
)ENGINE=InnoDB;