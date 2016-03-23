CREATE OR REPLACE PACKAGE PAC_CENTRO IS
  TYPE G_CURSOR IS REF CURSOR;
  PROCEDURE insertar_centro(p_nombre in centros.nombre%type,p_provincia in CENTROS.PROVINCIA%type);
  PROCEDURE borrar_centro(v_fuente IN CENTROS.ID%TYPE, v_destino IN CENTROS.ID%TYPE);
  PROCEDURE cambiar_provincia(p_idCentro NUMBER,p_provincia VARCHAR2);
  PROCEDURE visualizar_lista_centro(o_cursor OUT G_CURSOR);
  PROCEDURE visualizar_datos_centro(i_idCen IN NUMBER, o_cursor OUT G_CURSOR);
  PROCEDURE visualizar_datos_centro(i_nomCen IN CENTRO.NOMBRE%TYPE, o_cursor OUT G_CURSOR);
  FUNCTION buscar_centro_por_nombre(v_nCentro IN CENTROS.NOMBRE%TYPE) RETURN NUMBER;
END PAC_CENTRO;
CREATE OR REPLACE PACKAGE BODY PAC_CENTRO IS
  /* PROCEDIMIENTO INSERTAR CENTRO */
    
  PROCEDURE insertar_centro(
    p_nombre in centros.nombre%type, 
    p_provincia in CENTROS.PROVINCIA%type
  )
  IS
    v_nombre CENTROS.NOMBRE%type;
    e_found exception;
  BEGIN
    select nombre
    into v_nombre
    from centros 
    where nombre=p_nombre;
    
    if sql%found
      then
        raise e_found;
    end if;    
  EXCEPTION
    when e_found then
      DBMS_OUTPUT.PUT_LINE('El centro que intenta insertar ya existe');
    when no_data_found then
      insert into centros (nombre, provincia) values (p_nombre, p_provincia);
  END;
  
  /* BORRAR CENTRO Y TRANSFERIR LOS TRABAJADORES A OTRO CENTRO */
  
  
  PROCEDURE borrar_centro(
    v_fuente IN CENTROS.ID%TYPE, v_destino IN CENTROS.ID%TYPE
  )AS
    E_FALLO_FUENTE EXCEPTION;
    E_FALLO_DESTINO EXCEPTION;
    E_FALLO_TRANSFERIR EXCEPTION;
    V_TEMP CENTROS.ID%TYPE;
  BEGIN
    SELECT ID INTO V_TEMP FROM CENTROS WHERE ID = v_fuente;
    IF SQL%NOTFOUND THEN
      RAISE E_FALLO_FUENTE;
    ELSE
      SELECT ID into V_TEMP FROM CENTROS WHERE ID = v_destino;
      IF SQL%NOTFOUND THEN
        RAISE E_FALLO_DESTINO;
      ELSE
        UPDATE TRABAJADOR SET IDCENTRO=v_destino WHERE IDCENTRO = v_fuente;
        IF SQL%NOTFOUND THEN
          RAISE E_FALLO_TRANSFERIR; 
        END IF;
      END IF;
    END IF;
  EXCEPTION
    WHEN E_FALLO_FUENTE THEN
      RAISE_APPLICATION_ERROR(-20111,'No se ha encontrado el centro a borrar.');
    WHEN E_FALLO_DESTINO THEN
      RAISE_APPLICATION_ERROR(-20111,'No se ha encontrado el centro de destino.');
    when E_FALLO_TRANSFERIR THEN
      RAISE_APPLICATION_ERROR(-20112,'Error al transferir los trabajadores.');
  END;
  
        --CAMBIAR PROVINCIA
      
  PROCEDURE cambiar_provincia(
    p_idCentro NUMBER,
    p_provincia VARCHAR2)
  IS
    e_idCentro_inexistente EXCEPTION;
  -- BLOQUE PRINCIPAL
  BEGIN
 -- BLOQUE Para comprobar CENTRO repetido(Puede disparar NO_DATA_FOUND)
    DECLARE
      v_idCentro centro.idCentro%TYPE; 
      -- e_idCentro_inexistente se propaga;
    BEGIN
      SELECT idCentro INTO v_idCentro FROM centro
      WHERE idCentro = p_idCentro;
        -- Enviamos al bloque principal la excepción definida por nosotros e_idCentro_inexistente
        IF SQL%NOTFOUND THEN
          RAISE e_idCentro_inexistente;  
        END IF;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        --Si se dispara esta excepción hay error, ese p_idCentro no es correcto
        RAISE_APPLICATION_ERROR (-20002,'Error: '||sqlerrm);
      WHEN TOO_MANY_ROWS THEN
        NULL; 
    END;		
    --	Fin del bloque de comprobación de id_Centro inexistente 
    -- Inserta Centro 
    UPDATE centro SET provincia = p_provincia WHERE centro.idCentro = p_idCentro;
    -- Comprobar
    IF SQL%FOUND THEN
      COMMIT;
    END IF;
  EXCEPTION
    WHEN e_idCentro_inexistente THEN
      RAISE_APPLICATION_ERROR (-20001,'Err. id de Centro inexistente');
    WHEN OTHERS THEN   
      RAISE_APPLICATION_ERROR (-20003,'Error: '||sqlerrm);
  END cambiar_provincia;
    
  
  /* PROCEDIMIENTO VISUALIZAR LISTA CENTRO */
  
  PROCEDURE visualizar_lista_centro(
  o_cursor OUT G_CURSOR
  )IS
  BEGIN
    OPEN o_cursor FOR
      select *
      from centros; 
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20107, 'NO SE ENCONTRARON CENTROS');
  END visualizar_lista_centro;
  
  /* PROCEDIMIENTO VISUALIZAR DATOS CENTRO */
  
  PROCEDURE visualizar_datos_centro (
  i_idCen IN NUMBER, o_cursor OUT G_CURSOR
  )IS
  BEGIN  
     OPEN o_cursor FOR 
        SELECT IDCENTRO, NOMBRE, TELEFONO, CALLE, NUMERO, CP, CIUDAD, PROVINCIA, (SELECT COUNT(*) FROM TRABAJADOR WHERE IDCENTRO = i_idCen) TRABAJADORES 
        FROM CENTRO C
        WHERE idCentro = i_idCen;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20009,'NO SE ENCONTRÓ EL CENTRO');
  END;
  
  /* PROCEDIMIENTO VISUALIZAR DATOS CENTRO SOBRECARGADO */
  
  PROCEDURE visualizar_datos_centro (
  i_nomCen IN CENTRO.NOMBRE%TYPE, o_cursor OUT G_CURSOR
  )IS
  BEGIN  
     OPEN o_cursor FOR 
        SELECT IDCENTRO, NOMBRE, TELEFONO, CALLE, NUMERO, CP, CIUDAD, PROVINCIA, (SELECT COUNT(*) FROM TRABAJADOR WHERE IDCENTRO = C.IDCENTRO) TRABAJADORES 
        FROM CENTRO C
        WHERE idCentro = buscar_centro_por_nombre(i_nomCen);
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20009,'NO SE ENCONTRÓ EL CENTRO');
  END;

  --BUSCAR CENTRO POR NOMBRE
    
  FUNCTION buscar_centro_por_nombre
    (v_nCentro IN CENTROS.NOMBRE%TYPE)
    return NUMBER
  IS
    v_idCentro CENTROS.ID%TYPE;
    E_CENTRO_ERRONEO EXCEPTION;
  BEGIN
    SELECT ID INTO v_idCentro FROM CENTROS WHERE UPPER(NOMBRE) LIKE UPPER(V_NCENTRO);
    IF SQL%NOTFOUND THEN
      RAISE E_CENTRO_ERRONEO;
    END IF;
  RETURN v_idCentro;
  EXCEPTION
    WHEN E_CENTRO_ERRONEO THEN
      RAISE_APPLICATION_ERROR(-20009,'NO SE ENCONTRÓ EL CENTRO');
  END buscar_centro_por_nombre;
 
END PAC_CENTRO;

