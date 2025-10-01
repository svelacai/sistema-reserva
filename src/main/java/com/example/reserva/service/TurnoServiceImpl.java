package com.example.reserva.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reserva.dto.GeneracionTurnosRequest;
import com.example.reserva.dto.ResponseTurnoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TurnoServiceImpl implements TurnoService {

	private final EntityManager entityManager;

	public TurnoServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<ResponseTurnoDTO> generarTurnos(GeneracionTurnosRequest generacionTurnosRequest) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SYSBACKUP.generar_turnos");

		query.registerStoredProcedureParameter("p_fecha_inicio", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_fecha_fin", Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_id_servicio", Long.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);

		query.setParameter("p_fecha_inicio", generacionTurnosRequest.getFechaInicio());
		query.setParameter("p_fecha_fin", generacionTurnosRequest.getFechaFin());
		query.setParameter("p_id_servicio", generacionTurnosRequest.getIdServicio());

		List<Object[]> rows = query.getResultList();

		return rows.stream().map(r -> new ResponseTurnoDTO((String) r[0], (String) r[1], ((java.sql.Timestamp) r[2]),
				((java.sql.Timestamp) r[3]), ((java.sql.Timestamp) r[4]))).toList();
	}

}
