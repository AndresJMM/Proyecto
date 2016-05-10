CREATE OR REPLACE PACKAGE PAC_PARTE IS
  TYPE G_CURSOR IS REF CURSOR;
  PROCEDURE get_partes_mes(i_nomMes IN VARCHAR2, o_cursor OUT G_CURSOR);
  PROCEDURE get_albaranes_parte(i_idParte IN ALBARANES.IDPARTE%TYPE, o_cursor OUT G_CURSOR);
  PROCEDURE get_ids_partes(i_idTrabajador IN TRABAJADORES.IDTRABAJADOR%TYPE, i_fecha1 IN PARTES.FECHA%TYPE, i_fecha2 IN PARTES.FECHA%TYPE, o_cursor OUT G_CURSOR);
END PAC_PARTE;

CREATE OR REPLACE PACKAGE BODY PAC_PARTE IS
  
  PROCEDURE get_partes_mes(
    i_nomMes IN VARCHAR2, o_cursor OUT G_CURSOR
  )AS
  BEGIN
    OPEN o_cursor for
      SELECT IDPARTE, IDTRABAJADOR, IDTIPOPARTE, FECHA, KMINI, KMFIN, GASTOPEAJE, GASTODIETA, GASTOGASOIL, GASTOOTROS, DESCRIPCION, MATRICULA 
      FROM PARTES 
      WHERE trunc(fecha, 'month') = to_date(i_nomMes,'mm-yyyy');
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20105, 'No se encontraron partes pertenecientes a este mes.');
  END get_partes_mes;
  
  PROCEDURE get_albaranes_parte(
    i_idParte IN ALBARANES.IDPARTE%TYPE, o_cursor OUT G_CURSOR
  )AS
  BEGIN
    OPEN o_cursor for
      SELECT IDALBARAN, HORASALIDA, HORALLEGADA 
      FROM ALBARANES
      WHERE IDPARTE = i_idParte;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20105, 'No se encontraron albaranes pertenecientes a este parte.');
  END get_albaranes_parte;

  PROCEDURE get_ids_partes(
    i_idTrabajador IN TRABAJADORES.IDTRABAJADOR%TYPE, i_fecha1 IN PARTES.FECHA%TYPE, i_fecha2 IN PARTES.FECHA%TYPE, o_cursor OUT G_CURSOR 
  )AS
  BEGIN
    OPEN o_cursor for
      SELECT IDPARTE
      FROM PARTES
      WHERE IDTRABAJADOR = i_idTrabajador AND FECHA BETWEEN i_fecha1 AND i_fecha2;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20105, 'NO SE ENCONTRÓ EL IDPARTE.');
  END;  
  
END PAC_PARTE;


