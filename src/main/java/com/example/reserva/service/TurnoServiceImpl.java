package com.example.reserva.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reserva.dto.GeneracionTurnosRequest;
import com.example.reserva.dto.GeneracionTurnosResponseDTO;
import com.example.reserva.dto.ResponseTurnoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;

/**
 * Implementación del servicio de gestión de turnos.
 * Utiliza procedimientos almacenados para generar turnos disponibles
 * en base a los parámetros de fecha y servicio proporcionados.
 * 
 * @author Sistema Reserva
 * @version 1.0
 * @see TurnoService
 */
@Service
@Transactional
public class TurnoServiceImpl implements TurnoService {

	/**
	 * EntityManager para la gestión de persistencia y ejecución de procedimientos almacenados.
	 */
	private final EntityManager entityManager;

	/**
	 * Constructor que inicializa el servicio con el EntityManager.
	 * 
	 * @param entityManager gestor de entidades JPA para operaciones de base de datos
	 */
	public TurnoServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Ejecuta el procedimiento almacenado 'SYSBACKUP.generar_turnos' para obtener
	 * los turnos disponibles según los criterios especificados.
	 * 
	 * @param generacionTurnosRequest parámetros de generación de turnos
	 * @return lista de turnos con información del servicio, comercio y horarios
	 * @throws RuntimeException si hay error en el procedimiento
	 */
	@Override
	public GeneracionTurnosResponseDTO generarTurnos(GeneracionTurnosRequest generacionTurnosRequest) {
		// Crear consulta para el procedimiento almacenado
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SYSBACKUP.generar_turnos");

		// Registrar parámetros del procedimiento
		query.registerStoredProcedureParameter("p_fecha_inicio", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_fecha_fin", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_id_servicio", Long.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_codigo", Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_mensaje", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);

		// Establecer valores de los parámetros
		query.setParameter("p_fecha_inicio", generacionTurnosRequest.getFechaInicio());
		query.setParameter("p_fecha_fin", generacionTurnosRequest.getFechaFin());
		query.setParameter("p_id_servicio", generacionTurnosRequest.getIdServicio());

		// Ejecutar procedimiento
		query.execute();
		
		// Obtener parámetros de salida
		Integer codigo = (Integer) query.getOutputParameterValue("p_codigo");
		String mensaje = (String) query.getOutputParameterValue("p_mensaje");

		// Obtener resultados del cursor
		List<Object[]> rows = query.getResultList();

		// Mapear resultados a DTOs
		List<ResponseTurnoDTO> turnos = rows.stream().map(r -> {
			// Convertir Timestamp a Date manteniendo valores exactos
			java.sql.Timestamp fecha = (java.sql.Timestamp) r[2];
			java.sql.Timestamp horaInicio = (java.sql.Timestamp) r[3];
			java.sql.Timestamp horaFin = (java.sql.Timestamp) r[4];
			
			return new ResponseTurnoDTO(
				(String) r[0], 
				(String) r[1], 
				new java.util.Date(fecha.getTime()),
				new java.util.Date(horaInicio.getTime()),
				new java.util.Date(horaFin.getTime())
			);
		}).toList();
		
		return new GeneracionTurnosResponseDTO(codigo, mensaje, turnos);
	}

}
