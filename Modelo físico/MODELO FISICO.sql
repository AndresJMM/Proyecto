

DROP TABLE Albaranes CASCADE CONSTRAINTS;

DROP TABLE Coches CASCADE CONSTRAINTS;

DROP TABLE Partes CASCADE CONSTRAINTS;

DROP TABLE TipoPartes CASCADE CONSTRAINTS;

DROP TABLE Trabajadores CASCADE CONSTRAINTS;

DROP TABLE Logins CASCADE CONSTRAINTS;

DROP TABLE TipoTrabajadores CASCADE CONSTRAINTS;

DROP TABLE Centros CASCADE CONSTRAINTS;


CREATE TABLE TipoTrabajadores(
  idTipo NUMBER(10) 
    GENERATED ALWAYS AS IDENTITY
      NOORDER
      NOCYCLE NOT NULL ENABLE,
  tipo VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_TipoTrabajador_idTipo PRIMARY KEY (idTipo));


CREATE TABLE Logins(
  idLogin NUMBER (10) 
    GENERATED ALWAYS AS IDENTITY
      NOORDER
      NOCYCLE NOT NULL ENABLE,
  usuario VARCHAR2(20) NOT NULL,
  pass VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_Login_Usuario PRIMARY KEY (idLogin));
  

CREATE TABLE Centros(
  idCentro NUMBER(10) 
    GENERATED ALWAYS AS IDENTITY
      NOORDER
      NOCYCLE NOT NULL ENABLE,
  nombre VARCHAR2(45) NOT NULL,
  telefono VARCHAR2(9) NOT NULL,
  calle VARCHAR2(45) NOT NULL,
  numero VARCHAR2(2) NOT NULL,
  CP VARCHAR2(5) NOT NULL,
  ciudad VARCHAR2(45) NOT NULL,
  provincia VARCHAR2(45) NOT NULL,
  CONSTRAINT pk_Centro_idCentro PRIMARY KEY (idCentro));


CREATE TABLE Trabajadores(
  idTrabajador NUMBER(10) 
    GENERATED ALWAYS AS IDENTITY
      NOORDER
      NOCYCLE NOT NULL ENABLE,
  idTipo NUMBER(10) NOT NULL,
  idCentro NUMBER(10) NOT NULL,
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
  CONSTRAINT pk_Trabajador_idTrabajador PRIMARY KEY (idTrabajador),
  CONSTRAINT fk_Trabajador_TipoTrabajador
    FOREIGN KEY (idTipo)
    REFERENCES TipoTrabajadores (idTipo),
  CONSTRAINT fk_Trabajador_Centro
    FOREIGN KEY (idCentro)
    REFERENCES Centros (idCentro));


CREATE TABLE TipoPartes(
  idTipoParte NUMBER(10) 
    GENERATED ALWAYS AS IDENTITY
      NOORDER
      NOCYCLE NOT NULL ENABLE,
  estado VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_TipoParte_idTipoParte PRIMARY KEY (idTipoParte));


CREATE TABLE Partes(
  idParte NUMBER(10) 
    GENERATED ALWAYS AS IDENTITY
      NOORDER
      NOCYCLE NOT NULL ENABLE,
  idTrabajador NUMBER(10) NOT NULL,
  idTipoParte  NUMBER(10) NOT NULL,
  fecha DATE NOT NULL,
  kmIni NUMBER(10,2) NOT NULL,
  kmFin NUMBER(10,2),
  gastoPeaje NUMBER(5,2),
  gastoDieta NUMBER(5,2),
  gastoGasoil NUMBER(5,2),
  gastoAutopista NUMBER(5,2),
  gastoOtros NUMBER(5,2),
  descripcion VARCHAR2(250),
  CONSTRAINT pk_Parte_idParte PRIMARY KEY (idParte),
  CONSTRAINT fk_Parte_Trabajador
    FOREIGN KEY (idTrabajador)
    REFERENCES Trabajadores (idTrabajador),
  CONSTRAINT fk_Parte_TipoParte
    FOREIGN KEY (idTipoParte)
    REFERENCES TipoPartes (idTipoParte));


CREATE TABLE Coches(
  matricula VARCHAR2(7),
  marca VARCHAR2(45) NOT NULL,
  CONSTRAINT pk_Coche_Matricula PRIMARY KEY (matricula));


CREATE TABLE Albaranes(
  idAlbaran VARCHAR2(10),
  idParte NUMBER(10) NOT NULL,
  matricula VARCHAR2(7) NOT NULL,
  horaSalida TIMESTAMP NOT NULL,
  horaLlegada TIMESTAMP NOT NULL,
  CONSTRAINT fk_Albaran_Parte
    FOREIGN KEY (idParte)
    REFERENCES Partes (idParte),
  CONSTRAINT fk_Albaran_Coche
    FOREIGN KEY (matricula)
    REFERENCES Coches (matricula),
  CONSTRAINT pk_Alb_idAlbaran_idParte PRIMARY KEY (idAlbaran, idParte));