package com.example.reserva.service;

import java.util.List;

import com.example.reserva.dto.GeneracionTurnosRequest;
import com.example.reserva.dto.ResponseTurnoDTO;

public interface TurnoService {
	List<ResponseTurnoDTO> generarTurnos(GeneracionTurnosRequest generacionTurnosRequest);

}
