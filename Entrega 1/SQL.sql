DROP TABLE TipoTrabajador;

CREATE TABLE TipoTrabajador(
  idTipo INT NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  PRIMARY KEY (idTipo));

DROP TABLE Centro;

CREATE TABLE Centro(
  idCentro INT NOT NULL ,
  nombre VARCHAR(45) NOT NULL ,
  telefono VARCHAR(9) NOT NULL ,
  PRIMARY KEY (idCentro));

DROP TABLE Trabajador;

CREATE TABLE Trabajador(
  DNI VARCHAR(9) NOT NULL,
  idCentro INT NOT NULL,
  idTipo INT NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  ape1 VARCHAR(45) NOT NULL,
  ape2 VARCHAR(45) NOT NULL,
  fechaNac DATE NULL,
  salario DECIMAL(7,2) NULL,
  PRIMARY KEY (DNI),
  CONSTRAINT fk_Trabajador_TipoTrabajador1
    FOREIGN KEY (idTipo)
    REFERENCES TipoTrabajador (idTipo),
  CONSTRAINT fk_Trabajador_Centro1
    FOREIGN KEY (idCentro)
    REFERENCES Centro (idCentro));

--CREATE INDEX fk_Trabajador_TipoTrabajador1_idx ON Trabajador (idTipo ASC)  ;

--CREATE INDEX fk_Trabajador_Centro1_idx ON Trabajador (idCentro ASC)  ;

DROP TABLE LocCentro;

CREATE TABLE LocCentro(
  idCentro INT NOT NULL ,
  calle VARCHAR(45) NOT NULL,
  numero VARCHAR(2) NOT NULL,
  CP VARCHAR(5) NOT NULL,
  ciudad VARCHAR(45) NOT NULL,
  provincia VARCHAR(45) NOT NULL,
  PRIMARY KEY (idCentro),
  CONSTRAINT fk_LocCentro_Centro
    FOREIGN KEY (idCentro)
    REFERENCES Centro (idCentro)
    ON DELETE CASCADE);
    
DROP TABLE LocTrabajador;

CREATE TABLE LocTrabajador(
  DNI VARCHAR(9) NOT NULL ,
  calle VARCHAR(45) NOT NULL ,
  portal VARCHAR(2) NOT NULL ,
  piso VARCHAR(2) NOT NULL ,
  mano VARCHAR(1) NOT NULL ,
  PRIMARY KEY (DNI)  ,
  CONSTRAINT fk_LocTrabajador_Trabajador1
    FOREIGN KEY (DNI)
    REFERENCES Trabajador (DNI)
    ON DELETE CASCADE);
    
DROP TABLE Contacto;

CREATE TABLE Contacto(
  DNI VARCHAR(9) NOT NULL ,
  movilEmp VARCHAR(9) NOT NULL ,
  tlfPersonal VARCHAR(9) NOT NULL ,
  PRIMARY KEY (DNI)  ,
  CONSTRAINT fk_Contacto_Trabajador1
    FOREIGN KEY (DNI)
    REFERENCES Trabajador (DNI)
    ON DELETE CASCADE);

DROP TABLE TipoParte;

CREATE TABLE TipoParte(
  idTipoParte INT NOT NULL ,
  nombre VARCHAR(20) NOT NULL ,
  PRIMARY KEY (idTipoParte));

DROP TABLE Parte;

CREATE TABLE Parte(
  idParte INT NOT NULL ,
  DNI VARCHAR(9) NOT NULL ,
  idTipo INT NOT NULL ,
  fecha DATE NOT NULL,
  kmIni VARCHAR(10) NOT NULL ,
  kmFin VARCHAR(10) NULL ,
  gastoPeaje DECIMAL(5,2) NULL ,
  gastoDieta DECIMAL(5,2) NULL ,
  gastoGasoil DECIMAL(5,2) NULL ,
  gastoAutopista DECIMAL(5,2) NULL ,
  otrosGastos DECIMAL(5,2) NULL ,
  PRIMARY KEY (idParte)  ,
  CONSTRAINT fk_Parte_Trabajador1
    FOREIGN KEY (DNI)
    REFERENCES Trabajador (DNI),
  CONSTRAINT fk_Parte_TipoParte1
    FOREIGN KEY (idTipo)
    REFERENCES TipoParte (idTipoParte))
;

--CREATE INDEX fk_Parte_Trabajador1_idx ON Parte (DNI ASC)  ;

--CREATE INDEX fk_Parte_TipoParte1_idx ON Parte (idTipo ASC)  ;


DROP TABLE Coche;

CREATE TABLE Coche (
  matricula VARCHAR(7) NOT NULL ,
  marca VARCHAR(45) NOT NULL ,
  PRIMARY KEY (matricula));


DROP TABLE Albaran;

CREATE TABLE Albaran(
  idAlbaran INT NOT NULL ,
  idParte INT NOT NULL ,
  matricula VARCHAR(7) NOT NULL ,
  horaSalida VARCHAR(5) NOT NULL ,
  horaLlegada VARCHAR(5) NOT NULL ,
  PRIMARY KEY (idAlbaran, idParte)  ,
  CONSTRAINT fk_Albaran_Parte1
    FOREIGN KEY (idParte)
    REFERENCES Parte (idParte),
  CONSTRAINT fk_Albaran_Coche1
    FOREIGN KEY (matricula)
    REFERENCES Coche (matricula));

--CREATE INDEX fk_Albaran_Parte1_idx ON Albaran (idParte ASC)  ;

--CREATE INDEX fk_Albaran_Coche1_idx ON Albaran (matricula ASC)  ;

