package com.example.reserva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reserva.dto.GeneracionTurnosRequest;
import com.example.reserva.dto.ResponseTurnoDTO;
import com.example.reserva.entity.Turno;
import com.example.reserva.service.TurnoService;

@RestController
@RequestMapping("/api/turnos")
@CrossOrigin(origins = "*")
public class TurnoController {

	@Autowired
    private  TurnoService turnoService;

    @PostMapping("/generar")
    public ResponseEntity<List<ResponseTurnoDTO>> generarTurnos(
            @RequestBody GeneracionTurnosRequest request) { 

        List<ResponseTurnoDTO> turnos = turnoService.generarTurnos(request);
        return ResponseEntity.ok(turnos);
    }
}
