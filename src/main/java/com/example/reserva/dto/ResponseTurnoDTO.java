package com.example.reserva.dto;

import java.util.Date;

public class ResponseTurnoDTO {
	private String nombreServicio;
	private String nombreComercio;
	private Date fecha;
	private Date horaInicio;
	private Date horaFin;

	public ResponseTurnoDTO() {
	}

	public ResponseTurnoDTO(String nombreServicio, String nombreComercio, Date fecha, Date horaInicio, Date horaFin) {
		this.nombreServicio = nombreServicio;
		this.nombreComercio = nombreComercio;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getNombreComercio() {
		return nombreComercio;
	}

	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
}