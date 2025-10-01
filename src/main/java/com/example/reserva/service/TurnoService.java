package com.example.reserva.service;

import java.util.List;

import com.example.reserva.dto.GeneracionTurnosRequest;
import com.example.reserva.dto.GeneracionTurnosResponseDTO;
import com.example.reserva.dto.ResponseTurnoDTO;

/**
 * Servicio para la gestión de turnos en el sistema de reservas.
 * Proporciona operaciones para generar y administrar turnos disponibles.
 * 
 * @author Sistema Reserva
 * @version 1.0
 */
public interface TurnoService {
	
	/**
	 * Genera turnos disponibles para un servicio específico en un rango de fechas.
	 * 
	 * @param generacionTurnosRequest objeto que contiene los parámetros para la generación:
	 *                               - fechaInicio: fecha de inicio del rango
	 *                               - fechaFin: fecha de fin del rango
	 *                               - idServicio: identificador del servicio
	 * @return respuesta con código, mensaje y lista de turnos generados
	 */
	GeneracionTurnosResponseDTO generarTurnos(GeneracionTurnosRequest generacionTurnosRequest);

}
