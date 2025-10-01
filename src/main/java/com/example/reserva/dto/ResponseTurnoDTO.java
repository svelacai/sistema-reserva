package com.example.reserva.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de respuesta que encapsula la información de un turno generado.
 * Contiene los detalles del servicio, comercio y horarios del turno disponible.
 * 
 * @author Sistema Reserva
 * @version 1.0
 */
@Schema(description = "Información de un turno disponible")
public class ResponseTurnoDTO {
	
	/**
	 * Nombre del servicio asociado al turno.
	 */
	@Schema(description = "Nombre del servicio", example = "Corte de Cabello")
	private String nombreServicio;
	
	/**
	 * Nombre del comercio donde se presta el servicio.
	 */
	@Schema(description = "Nombre del comercio", example = "Peluquería Central")
	private String nombreComercio;
	
	/**
	 * Fecha del turno.
	 */
	@Schema(description = "Fecha del turno")
	private Date fecha;
	
	/**
	 * Hora de inicio del turno.
	 */
	@Schema(description = "Hora de inicio del turno")
	private Date horaInicio;
	
	/**
	 * Hora de finalización del turno.
	 */
	@Schema(description = "Hora de fin del turno")
	private Date horaFin;

	/**
	 * Constructor por defecto.
	 */
	public ResponseTurnoDTO() {
	}

	/**
	 * Constructor con todos los parámetros.
	 * 
	 * @param nombreServicio nombre del servicio
	 * @param nombreComercio nombre del comercio
	 * @param fecha fecha del turno
	 * @param horaInicio hora de inicio del turno
	 * @param horaFin hora de fin del turno
	 */
	public ResponseTurnoDTO(String nombreServicio, String nombreComercio, Date fecha, Date horaInicio, Date horaFin) {
		this.nombreServicio = nombreServicio;
		this.nombreComercio = nombreComercio;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	/**
	 * Obtiene el nombre del servicio.
	 * 
	 * @return nombre del servicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * Establece el nombre del servicio.
	 * 
	 * @param nombreServicio nombre del servicio a establecer
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	/**
	 * Obtiene el nombre del comercio.
	 * 
	 * @return nombre del comercio
	 */
	public String getNombreComercio() {
		return nombreComercio;
	}

	/**
	 * Establece el nombre del comercio.
	 * 
	 * @param nombreComercio nombre del comercio a establecer
	 */
	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}

	/**
	 * Obtiene la fecha del turno.
	 * 
	 * @return fecha del turno
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha del turno.
	 * 
	 * @param fecha fecha del turno a establecer
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtiene la hora de inicio del turno.
	 * 
	 * @return hora de inicio del turno
	 */
	public Date getHoraInicio() {
		return horaInicio;
	}

	/**
	 * Establece la hora de inicio del turno.
	 * 
	 * @param horaInicio hora de inicio a establecer
	 */
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * Obtiene la hora de fin del turno.
	 * 
	 * @return hora de fin del turno
	 */
	public Date getHoraFin() {
		return horaFin;
	}

	/**
	 * Establece la hora de fin del turno.
	 * 
	 * @param horaFin hora de fin a establecer
	 */
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
}