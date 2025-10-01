package com.example.reserva.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para encapsular los parámetros de solicitud para la generación de turnos.
 * Contiene la información necesaria para generar turnos disponibles en un rango de fechas
 * para un servicio específico.
 * 
 * @author Sistema Reserva
 * @version 1.0
 */
@Schema(description = "Parámetros para la generación de turnos")
public class GeneracionTurnosRequest {
	
	/**
	 * Fecha de inicio del rango para generar turnos.
	 * Formato esperado: yyyy-MM-dd
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Schema(description = "Fecha de inicio del rango", example = "2024-01-01")
	private Date fechaInicio;

	/**
	 * Fecha de fin del rango para generar turnos.
	 * Formato esperado: yyyy-MM-dd
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Schema(description = "Fecha de fin del rango", example = "2024-01-31")
	private Date fechaFin;

	/**
	 * Identificador único del servicio para el cual se generarán los turnos.
	 */
	@Schema(description = "ID del servicio", example = "1")
	private Long idServicio;

	/**
	 * Obtiene la fecha de inicio del rango.
	 * 
	 * @return fecha de inicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Establece la fecha de inicio del rango.
	 * 
	 * @param fechaInicio fecha de inicio a establecer
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Obtiene la fecha de fin del rango.
	 * 
	 * @return fecha de fin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Establece la fecha de fin del rango.
	 * 
	 * @param fechaFin fecha de fin a establecer
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Obtiene el identificador del servicio.
	 * 
	 * @return identificador del servicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}

	/**
	 * Establece el identificador del servicio.
	 * 
	 * @param idServicio identificador del servicio a establecer
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

}
