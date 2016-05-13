DROP TABLE Albaranes CASCADE CONSTRAINTS;

DROP TABLE Partes CASCADE CONSTRAINTS;

DROP TABLE Coches CASCADE CONSTRAINTS;

DROP TABLE Trabajadores CASCADE CONSTRAINTS;

DROP TABLE Logins CASCADE CONSTRAINTS;

DROP TABLE TipoTrabajadores CASCADE CONSTRAINTS;

DROP TABLE Centros CASCADE CONSTRAINTS;

DROP TABLE INFORMES CASCADE CONSTRAINTS;


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
  idlogin NUMBER(10) NOT NULL, 
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
    REFERENCES Centros (idCentro),
  CONSTRAINT fk_Trabajador_Login
    FOREIGN KEY(idLogin) 
    REFERENCES Logins (idLogin)
    ON DELETE CASCADE);

CREATE TABLE Coches(
  matricula VARCHAR2(7),
  marca VARCHAR2(45) NOT NULL,
  CONSTRAINT pk_Coche_Matricula PRIMARY KEY (matricula));

CREATE TABLE Partes(
  idParte NUMBER(10) 
    GENERATED ALWAYS AS IDENTITY
      NOORDER
      NOCYCLE NOT NULL ENABLE,
  idTrabajador NUMBER(10) NOT NULL,
  estado VARCHAR2(20) NOT NULL,
  fecha DATE NOT NULL,
  kmIni NUMBER(10,2) NOT NULL,
  kmFin NUMBER(10,2),
  matricula VARCHAR2(7) NOT NULL,
  gastoPeaje NUMBER(5,2),
  gastoDieta NUMBER(5,2),
  gastoGasoil NUMBER(5,2),
  gastoOtros NUMBER(5,2),
  descripcion VARCHAR2(250),
  CONSTRAINT pk_Parte_idParte PRIMARY KEY (idParte),
  CONSTRAINT fk_Parte_Trabajador
    FOREIGN KEY (idTrabajador)
    REFERENCES Trabajadores (idTrabajador),
  CONSTRAINT fk_Parte_Coche
    FOREIGN KEY (matricula)
    REFERENCES Coches (matricula));


CREATE TABLE Albaranes(
  idAlbaran VARCHAR2(10),
  idParte NUMBER(10) NOT NULL,
  horaSalida TIMESTAMP NOT NULL,
  horaLlegada TIMESTAMP NOT NULL,
  CONSTRAINT fk_Albaran_Parte
    FOREIGN KEY (idParte)
    REFERENCES Partes (idParte),
  CONSTRAINT pk_Alb_idAlb_idParte PRIMARY KEY (idAlbaran, idParte));

CREATE TABLE INFORMES( 
  Fecha DATE,
  Contenido CLOB
);

insert into logins(usuario, pass) values ('usu1', 'usu1');
insert into logins(usuario, pass) values ('usu2', 'usu2');
insert into logins(usuario, pass) values ('usu3', 'usu3');
insert into logins(usuario, pass) values ('usu4', 'usu4');
insert into logins(usuario, pass) values ('admin', 'admin');

insert into tipotrabajadores(tipo) values ('administracion');
insert into tipotrabajadores(tipo) values ('logistica');

insert into centros(nombre, telefono, calle, numero, cp, ciudad, provincia) values ('centro1', '111111111', 'calle1', '1', '01001', 'Vitoria', 'Alava');
insert into centros(nombre, telefono, calle, numero, cp, ciudad, provincia) values ('centro2', '222222222', 'calle2', '2', '01002', 'San Sebastian', 'Guipuzcoa');

insert into trabajadores(idLogin, idTipo, idCentro, dni, nombre, ape1, ape2, fechaNac, salario, movilEmp, tlfPersonal, calle, portal, piso, mano) values (1, 1, 1, '11111111A', 'Ana', 'Jimenez', 'Jimenez', '01/01/91', 1000, '612345678', '945111111', 'Diaz de Olano', '1', '1', 'A');
insert into trabajadores(idLogin, idTipo, idCentro, dni, nombre, ape1, ape2, fechaNac, salario, movilEmp, tlfPersonal, calle, portal, piso, mano) values (2, 1, 1, '22222222B', 'Bea', 'Jimenez', 'Jimenez', '02/02/91', 1000, '612345678', '945111111', 'Diaz de Olano', '2', '2', 'B');
insert into trabajadores(idLogin, idTipo, idCentro, dni, nombre, ape1, ape2, fechaNac, salario, movilEmp, tlfPersonal, calle, portal, piso, mano) values (3, 2, 2, '33333333C', 'Cecilia', 'Jimenez', 'Jimenez', '03/03/91', 1000, '612345678', '945111111', 'Diaz de Olano', '3', '3', 'C');
insert into trabajadores(idLogin, idTipo, idCentro, dni, nombre, ape1, ape2, fechaNac, salario, movilEmp, tlfPersonal, calle, portal, piso, mano) values (4, 2, 2, '44444444D', 'Dina', 'Jimenez', 'Jimenez', '04/04/91', 1000, '612345678', '945111111', 'Diaz de Olano', '4', '4', 'D');
insert into trabajadores(idLogin, idTipo, idCentro, dni, nombre, ape1, ape2, fechaNac, salario, movilEmp, tlfPersonal, calle, portal, piso, mano) values (5, 1, 1, '55555555D', 'Admin', 'Admin', 'Admin', '05/05/91', 1000, '612345678', '945111111', 'Admin', '1', '1', 'A');

insert into coches values ('B1206WW', 'Seat');
insert into coches values ('B1307LL', 'Opel');

commit;
