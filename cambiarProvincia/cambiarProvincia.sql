CREATE OR REPLACE PROCEDURE cambiar_provincia(
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
		WHERE idCentro = p_idCentro);
    -- Enviamos al bloque principal la excepción definida por nosotros e_idCentro_inexistente
      IF SQL%NOTFOUND THEN
	      RAISE e_idCentro_inexistente;  
      END IF;
	EXCEPTION
	  WHEN NO_DATA_FOUND THEN
	  --Si se dispara esta excepción hay error, ese p_idCentro no es correcto
	    RAISE_APPLICATION_ERROR ('-20002','Error: '||sqlerrm);
	  WHEN TOO_MANY_ROWS THEN
	    NULL; 
	END;		
  --	Fin del bloque de comprobación de id_Centro inexistente 

-- Inserta Centro 
    
	UPDATE centro SET provincia = p_provincia WHERE centro.idCentro = p_idCentro
	-- Comprobar
	IF SQL%FOUND
	THEN
		COMMIT;
	END IF;
 EXCEPTION
  WHEN e_idCentro_inexistente THEN
    RAISE_APPLICATION_ERROR ('-20001','Err. id de Centro inexistente');
  WHEN OTHERS THEN   
    RAISE_APPLICATION_ERROR ('-20003','Error: '||sqlerrm);

END cambiar_provincia;
