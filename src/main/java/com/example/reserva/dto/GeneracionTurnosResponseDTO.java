package com.example.reserva.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de respuesta para la generación de turnos que incluye
 * información del procedimiento almacenado y los turnos generados.
 * 
 * @author Sistema Reserva
 * @version 1.0
 */
@Schema(description = "Respuesta de la generación de turnos")
public class GeneracionTurnosResponseDTO {

    /**
     * Código de respuesta del procedimiento (0 = éxito, 1 = error).
     */
    @Schema(description = "Código de respuesta", example = "0")
    private Integer codigo;

    /**
     * Mensaje del procedimiento almacenado.
     */
    @Schema(description = "Mensaje de respuesta", example = "Turnos generados exitosamente")
    private String mensaje;

    /**
     * Lista de turnos generados.
     */
    @Schema(description = "Lista de turnos generados")
    private List<ResponseTurnoDTO> turnos;

    /**
     * Constructor por defecto.
     */
    public GeneracionTurnosResponseDTO() {
    }

    /**
     * Constructor con todos los parámetros.
     * 
     * @param codigo código de respuesta
     * @param mensaje mensaje de respuesta
     * @param turnos lista de turnos
     */
    public GeneracionTurnosResponseDTO(Integer codigo, String mensaje, List<ResponseTurnoDTO> turnos) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.turnos = turnos;
    }

    /**
     * Obtiene el código de respuesta.
     * 
     * @return código de respuesta
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de respuesta.
     * 
     * @param codigo código a establecer
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el mensaje de respuesta.
     * 
     * @return mensaje de respuesta
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje de respuesta.
     * 
     * @param mensaje mensaje a establecer
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene la lista de turnos.
     * 
     * @return lista de turnos
     */
    public List<ResponseTurnoDTO> getTurnos() {
        return turnos;
    }

    /**
     * Establece la lista de turnos.
     * 
     * @param turnos lista de turnos a establecer
     */
    public void setTurnos(List<ResponseTurnoDTO> turnos) {
        this.turnos = turnos;
    }
}