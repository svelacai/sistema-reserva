package com.example.reserva.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.reserva.dto.GeneracionTurnosRequest;
import com.example.reserva.dto.GeneracionTurnosResponseDTO;
import com.example.reserva.dto.ResponseTurnoDTO;
import com.example.reserva.service.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TurnoController.class)
class TurnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TurnoService turnoService;

    @Autowired
    private ObjectMapper objectMapper;

    private GeneracionTurnosRequest requestDto;
    private GeneracionTurnosResponseDTO responseDto;

    @BeforeEach
    void setUp() {
        requestDto = new GeneracionTurnosRequest();
        requestDto.setFechaInicio(new Date());
        requestDto.setFechaFin(new Date());
        requestDto.setIdServicio(1L);

        ResponseTurnoDTO turno = new ResponseTurnoDTO(
            "Corte de Cabello",
            "Peluquería Central",
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis() + 3600000)
        );

        responseDto = new GeneracionTurnosResponseDTO(0, "Turnos generados exitosamente", List.of(turno));
    }

    @Test
    void deberiaGenerarTurnosConDatosValidos() throws Exception {
        when(turnoService.generarTurnos(any(GeneracionTurnosRequest.class)))
            .thenReturn(responseDto);

        mockMvc.perform(post("/api/turnos/generar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.codigo").value(0))
            .andExpect(jsonPath("$.mensaje").value("Turnos generados exitosamente"))
            .andExpect(jsonPath("$.turnos[0].nombreServicio").value("Corte de Cabello"));

        verify(turnoService).generarTurnos(any(GeneracionTurnosRequest.class));
    }

    @Test
    void deberiaRetornarListaVaciaCuandoNoHayTurnos() throws Exception {
        GeneracionTurnosResponseDTO emptyResponse = new GeneracionTurnosResponseDTO(0, "Turnos generados exitosamente", List.of());
        when(turnoService.generarTurnos(any(GeneracionTurnosRequest.class)))
            .thenReturn(emptyResponse);

        mockMvc.perform(post("/api/turnos/generar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.codigo").value(0))
            .andExpect(jsonPath("$.turnos").isArray())
            .andExpect(jsonPath("$.turnos").isEmpty());

        verify(turnoService).generarTurnos(any(GeneracionTurnosRequest.class));
    }

    @Test
    void deberiaRetornarBadRequestCuandoHayErrorEnProcedimiento() throws Exception {
        GeneracionTurnosResponseDTO errorResponse = new GeneracionTurnosResponseDTO(1, "Servicio no encontrado", List.of());
        when(turnoService.generarTurnos(any(GeneracionTurnosRequest.class)))
            .thenReturn(errorResponse);

        mockMvc.perform(post("/api/turnos/generar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.codigo").value(1))
            .andExpect(jsonPath("$.mensaje").value("Servicio no encontrado"))
            .andExpect(jsonPath("$.turnos").isEmpty());

        verify(turnoService).generarTurnos(any(GeneracionTurnosRequest.class));
    }
}