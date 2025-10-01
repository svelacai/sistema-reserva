package com.example.reserva.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComercioServicioId implements Serializable {
	private Long comercio;
	private Long servicio;
}
