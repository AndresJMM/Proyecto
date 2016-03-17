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
    REFERENCES TipoTrabajador (idTipo),
  CONSTRAINT fk_Trabajador_Centro
    FOREIGN KEY (idCentro)
    REFERENCES Centro (idCentro));

DROP TABLE TipoParte CASCADE CONSTRAINTS;

CREATE TABLE TipoParte(
  idTipoParte NUMBER(10),
  estado VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_TipoParte_idTipoParte PRIMARY KEY (idTipoParte));

DROP TABLE Parte CASCADE CONSTRAINTS;

CREATE TABLE Parte(
  idParte NUMBER(10),
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
    REFERENCES Trabajador (idTrabajador),
  CONSTRAINT fk_Parte_TipoParte
    FOREIGN KEY (idTipoParte)
    REFERENCES TipoParte (idTipoParte));

DROP TABLE Coche CASCADE CONSTRAINTS;

CREATE TABLE Coche(
  matricula VARCHAR2(7),
  marca VARCHAR2(45) NOT NULL,
  CONSTRAINT pk_Coche_Matricula PRIMARY KEY (matricula));

DROP TABLE Albaran CASCADE CONSTRAINTS;

CREATE TABLE Albaran(
  idAlbaran NUMBER(10),
  idParte NUMBER(10) NOT NULL,
  matricula VARCHAR2(7) NOT NULL,
  horaSalida VARCHAR2(5) NOT NULL,
  horaLlegada VARCHAR2(5) NOT NULL,
  CONSTRAINT fk_Albaran_Parte
    FOREIGN KEY (idParte)
    REFERENCES Parte (idParte),
  CONSTRAINT fk_Albaran_Coche
    FOREIGN KEY (matricula)
    REFERENCES Coche (matricula),
  CONSTRAINT pk_Alb_idAlbaran_idParte PRIMARY KEY (idAlbaran, idParte));
