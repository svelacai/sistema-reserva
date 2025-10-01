package com.example.reserva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reserva.dto.GeneracionTurnosRequest;
import com.example.reserva.dto.GeneracionTurnosResponseDTO;
import com.example.reserva.service.TurnoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para la gestión de turnos.
 * 
 * @author Sistema Reserva
 * @version 1.0
 */
@RestController
@RequestMapping("/api/turnos")
@CrossOrigin(origins = "*")
@Tag(name = "Turnos", description = "API para la gestión de turnos")
public class TurnoController {

	@Autowired
    private TurnoService turnoService;

    /**
     * Genera turnos disponibles para un servicio en un rango de fechas.
     * 
     * @param request parámetros para la generación de turnos
     * @return lista de turnos disponibles
     */
    @PostMapping("/generar")
    @Operation(summary = "Generar turnos", 
               description = "Genera turnos disponibles para un servicio específico en un rango de fechas")
    @ApiResponse(responseCode = "200", description = "Turnos generados exitosamente")
    @ApiResponse(responseCode = "400", description = "Parámetros de entrada inválidos")
    public ResponseEntity<GeneracionTurnosResponseDTO> generarTurnos(
            @RequestBody GeneracionTurnosRequest request) { 

        GeneracionTurnosResponseDTO response = turnoService.generarTurnos(request);
        
        if (response.getCodigo() != 0) {
            return ResponseEntity.badRequest().body(response);
        }
        
        return ResponseEntity.ok(response);
    }
}
