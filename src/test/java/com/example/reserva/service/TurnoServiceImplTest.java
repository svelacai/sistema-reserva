package com.example.reserva.service;

import com.example.reserva.dto.GeneracionTurnosRequest;
import com.example.reserva.dto.GeneracionTurnosResponseDTO;
import com.example.reserva.dto.ResponseTurnoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TurnoServiceImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @InjectMocks
    private TurnoServiceImpl turnoService;

    private GeneracionTurnosRequest request;
    private List<Object[]> mockResults;

    @BeforeEach
    void setUp() {
        request = new GeneracionTurnosRequest();
        request.setFechaInicio(new java.util.Date());
        request.setFechaFin(new java.util.Date());
        request.setIdServicio(1L);

        Object[] row = {
            "Corte de Cabello",
            "Peluquería Central", 
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis() + 3600000)
        };
        
        mockResults = java.util.Collections.singletonList(row);
    }

    @Test
    void deberiaGenerarTurnosExitosamente() {
        when(entityManager.createStoredProcedureQuery(anyString())).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getOutputParameterValue("p_codigo")).thenReturn(0);
        when(storedProcedureQuery.getOutputParameterValue("p_mensaje")).thenReturn("Turnos generados exitosamente");
        when(storedProcedureQuery.getResultList()).thenReturn((List) mockResults);

        GeneracionTurnosResponseDTO result = turnoService.generarTurnos(request);

        assertNotNull(result);
        assertEquals(0, result.getCodigo());
        assertEquals("Turnos generados exitosamente", result.getMensaje());
        assertEquals(1, result.getTurnos().size());
        assertEquals("Corte de Cabello", result.getTurnos().get(0).getNombreServicio());

        verify(entityManager).createStoredProcedureQuery("SYSBACKUP.generar_turnos");
        verify(storedProcedureQuery).execute();
        verify(storedProcedureQuery).getOutputParameterValue("p_codigo");
    }

    @Test
    void deberiaRetornarListaVaciaCuandoNoHayResultados() {
        when(entityManager.createStoredProcedureQuery(anyString())).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getOutputParameterValue("p_codigo")).thenReturn(0);
        when(storedProcedureQuery.getOutputParameterValue("p_mensaje")).thenReturn("Turnos generados exitosamente");
        when(storedProcedureQuery.getResultList()).thenReturn((List) List.of());

        GeneracionTurnosResponseDTO result = turnoService.generarTurnos(request);

        assertNotNull(result);
        assertEquals(0, result.getCodigo());
        assertEquals("Turnos generados exitosamente", result.getMensaje());
        assertEquals(0, result.getTurnos().size());
        
        verify(entityManager).createStoredProcedureQuery("SYSBACKUP.generar_turnos");
        verify(storedProcedureQuery).execute();
    }

    @Test
    void deberiaRetornarErrorCuandoProcedimientoFalla() {
        when(entityManager.createStoredProcedureQuery(anyString())).thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getOutputParameterValue("p_codigo")).thenReturn(1);
        when(storedProcedureQuery.getOutputParameterValue("p_mensaje")).thenReturn("Servicio no encontrado");

        GeneracionTurnosResponseDTO result = turnoService.generarTurnos(request);

        assertNotNull(result);
        assertEquals(1, result.getCodigo());
        assertEquals("Servicio no encontrado", result.getMensaje());
        assertEquals(0, result.getTurnos().size());
        verify(storedProcedureQuery).execute();
    }
}