CREATE OR REPLACE TRIGGER excesoHoras
BEFORE INSERT ON Parte FOR EACH ROW

DECLARE
v_limite number := 8;
v_horaini number;
v_horafinal number;

BEGIN
SELECT horaSalida, horaLlegada INTO v_horaini, v_horafinal FROM Albaran;
	IF((v_horafinal - v_horaini) > v_limite) THEN raise_application_error
		(-20600,('Error. Sobrepasado el límite de horas.')); 
	END IF;
END;
