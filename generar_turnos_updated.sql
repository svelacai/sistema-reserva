CREATE OR REPLACE PROCEDURE SYSBACKUP.generar_turnos(
    p_fecha_inicio IN DATE,
    p_fecha_fin    IN DATE,
    p_id_servicio  IN NUMBER,
    p_codigo       OUT NUMBER,
    p_mensaje      OUT VARCHAR2,
    p_cursor       OUT SYS_REFCURSOR
) AS
    v_duracion       NUMBER;
    v_hora_apertura  DATE;
    v_hora_cierre    DATE;
    v_id_comercio    NUMBER;
    v_fecha          DATE;
    v_inicio_jornada DATE;
    v_fin_jornada    DATE; 
    v_dummy          NUMBER; 
BEGIN
    
    IF TRUNC(p_fecha_inicio) > TRUNC(p_fecha_fin) THEN
        p_codigo := 1;
        p_mensaje := 'Fecha inicio no puede ser mayor a fecha fin';
        OPEN p_cursor FOR SELECT 1 FROM DUAL WHERE 1=0;
        RETURN;
    END IF;

    IF TRUNC(p_fecha_fin) < TRUNC(SYSDATE) THEN
        p_codigo := 1;
        p_mensaje := 'Fecha fin no puede ser menor a la fecha actual';
        OPEN p_cursor FOR SELECT 1 FROM DUAL WHERE 1=0;
        RETURN;
    END IF;
    
    BEGIN
        SELECT duracion_minutos, hora_apertura, hora_cierre
        INTO v_duracion, v_hora_apertura, v_hora_cierre
        FROM servicio
        WHERE id_servicio = p_id_servicio;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            p_codigo := 1;
            p_mensaje := 'Servicio no encontrado';
            OPEN p_cursor FOR SELECT 1 FROM DUAL WHERE 1=0;
            RETURN;
    END;

    FOR c IN (SELECT id_comercio FROM comercio_servicio WHERE id_servicio = p_id_servicio) LOOP
        v_id_comercio := c.id_comercio;
        v_fecha := GREATEST(TRUNC(p_fecha_inicio), TRUNC(SYSDATE)); 

        WHILE v_fecha <= TRUNC(p_fecha_fin) LOOP
            v_inicio_jornada := TRUNC(v_fecha) + (v_hora_apertura - TRUNC(v_hora_apertura));
            v_fin_jornada    := TRUNC(v_fecha) + (v_hora_cierre - TRUNC(v_hora_cierre));

            WHILE v_inicio_jornada + NUMTODSINTERVAL(v_duracion,'MINUTE') <= v_fin_jornada LOOP
                BEGIN
                    SELECT 1 INTO v_dummy
                    FROM turno t
                    WHERE t.id_servicio = p_id_servicio
                      AND t.id_comercio = v_id_comercio
                      AND t.fecha = v_fecha
                      AND t.hora_inicio = v_inicio_jornada;

                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        INSERT INTO turno(
                            id_servicio, id_comercio, fecha, hora_inicio, hora_fin, reservado
                        )
                        VALUES (
                            p_id_servicio, 
                            v_id_comercio, 
                            v_fecha, 
                            v_inicio_jornada, 
                            v_inicio_jornada + NUMTODSINTERVAL(v_duracion,'MINUTE'), 
                            'N'
                        );
                END;

                v_inicio_jornada := v_inicio_jornada + NUMTODSINTERVAL(v_duracion,'MINUTE');
            END LOOP;

            v_fecha := v_fecha + 1;
        END LOOP;
    END LOOP;

    COMMIT;

    p_codigo := 0;
    p_mensaje := 'Turnos generados exitosamente';
    
    OPEN p_cursor FOR
      SELECT 
        s.nombre AS nombre_servicio,
        c.nombre AS nombre_comercio,
        TO_CHAR(t.fecha, 'YYYY-MM-DD') AS fecha,
        TO_CHAR(t.hora_inicio, 'YYYY-MM-DD HH24:MI:SS') AS hora_inicio,
        TO_CHAR(t.hora_fin, 'YYYY-MM-DD HH24:MI:SS') AS hora_fin
      FROM turno t
      JOIN servicio s ON t.id_servicio = s.id_servicio
      JOIN comercio c ON t.id_comercio = c.id_comercio
      WHERE t.id_servicio = p_id_servicio
        AND t.fecha BETWEEN TRUNC(p_fecha_inicio) AND TRUNC(p_fecha_fin)
      ORDER BY t.fecha, t.hora_inicio;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        p_codigo := 1;
        p_mensaje := 'Error: ' || SQLERRM;
        OPEN p_cursor FOR SELECT 1 FROM DUAL WHERE 1=0;
END generar_turnos;