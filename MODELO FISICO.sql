DROP TABLE TipoTrabajador CASCADE CONSTRAINTS;

CREATE TABLE TipoTrabajador(
  idTipo NUMBER(10),
  tipo VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_TipoTrabajador_idTipo PRIMARY KEY (idTipo));

DROP TABLE Login CASCADE CONSTRAINTS;

CREATE TABLE Login(
  idLogin NUMBER (10),
  usuario VARCHAR2(20) NOT NULL,
  pass VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_Login_Usuario PRIMARY KEY (idLogin)); 
  
DROP TABLE Centro CASCADE CONSTRAINTS;

CREATE TABLE Centro(
  idCentro NUMBER(10),
  nombre VARCHAR2(45) NOT NULL,
  telefono VARCHAR2(9) NOT NULL,
  calle VARCHAR2(45) NOT NULL,
  numero VARCHAR2(2) NOT NULL,
  CP VARCHAR2(5) NOT NULL,
  ciudad VARCHAR2(45) NOT NULL,
  provincia VARCHAR2(45) NOT NULL,
  CONSTRAINT pk_Centro_idCentro PRIMARY KEY (idCentro));

DROP TABLE Trabajador CASCADE CONSTRAINTS;

CREATE TABLE Trabajador(
  idTrabajador NUMBER(10),
  DNI VARCHAR2(9) NOT NULL,
  nombre VARCHAR2(20) NOT NULL,
  ape1 VARCHAR2(20) NOT NULL,
  ape2 VARCHAR2(20) NOT NULL,
  fechaNac DATE,
  salario NUMBER(7,2),
  movilEmp VARCHAR2(9) NOT NULL,
  tlfPersonal VARCHAR2(9),
  calle VARCHAR2(45) NOT NULL,
  portal VARCHAR2(2) NOT NULL,
  piso VARCHAR2(2) NOT NULL,
  mano VARCHAR2(1) NOT NULL,
  CONSTRAINT pk_Trabajador_DNI PRIMARY KEY (DNI),
  CONSTRAINT fk_Trabajador_TipoTrabajador1
    FOREIGN KEY (idTipo)
    REFERENCES TipoTrabajador (idTipo),
  CONSTRAINT fk_Trabajador_Centro1
    FOREIGN KEY (idCentro)
    REFERENCES Centro (idCentro));

--CREATE INDEX fk_Trabajador_TipoTrabajador1_idx ON Trabajador (idTipo ASC)  ;

--CREATE INDEX fk_Trabajador_Centro1_idx ON Trabajador (idCentro ASC)  ;

DROP TABLE TipoParte CASCADE CONSTRAINTS;

CREATE TABLE TipoParte(
  idTipoParte NUMBER(10),
  nombre VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_TipoParte_idTipoParte PRIMARY KEY (idTipoParte));

DROP TABLE Parte CASCADE CONSTRAINTS;

CREATE TABLE Parte(
  idParte NUMBER(10),
  fecha DATE NOT NULL,
  kmIni NUMBER(10,2) NOT NULL,
  kmFin NUMBER(10,2),
  gastoPeaje NUMBER(5,2),
  gastoDieta NUMBER(5,2),
  gastoGasoil NUMBER(5,2),
  gastoAutopista NUMBER(5,2),
  otrosGastos NUMBER(5,2),
  descripcion VARCHAR2(250),
  CONSTRAINT pk_Parte_idParte PRIMARY KEY (idParte),
  CONSTRAINT fk_Parte_Trabajador1
    FOREIGN KEY (DNI)
    REFERENCES Trabajador (DNI),
  CONSTRAINT fk_Parte_TipoParte1
    FOREIGN KEY (idTipo)
    REFERENCES TipoParte (idTipoParte))
;

--CREATE INDEX fk_Parte_Trabajador1_idx ON Parte (DNI ASC)  ;

--CREATE INDEX fk_Parte_TipoParte1_idx ON Parte (idTipo ASC)  ;


DROP TABLE Coche CASCADE CONSTRAINTS;

CREATE TABLE Coche (
  matricula VARCHAR2(7),
  marca VARCHAR2(45) NOT NULL,
  CONSTRAINT pk_Coche_Matricula PRIMARY KEY (matricula));


DROP TABLE Albaran CASCADE CONSTRAINTS;

CREATE TABLE Albaran(
  idAlbaran NUMBER(10),
  horaSalida VARCHAR2(5) NOT NULL,
  horaLlegada VARCHAR2(5) NOT NULL,
  CONSTRAINT pk_Alb_idAlbaran_idParte PRIMARY KEY (idAlbaran, idParte),
  CONSTRAINT fk_Albaran_Parte1
    FOREIGN KEY (idParte)
    REFERENCES Parte (idParte),
  CONSTRAINT fk_Albaran_Coche1
    FOREIGN KEY (matricula)
    REFERENCES Coche (matricula));

--CREATE INDEX fk_Albaran_Parte1_idx ON Albaran (idParte ASC)  ;

--CREATE INDEX fk_Albaran_Coche1_idx ON Albaran (matricula ASC)  ;

