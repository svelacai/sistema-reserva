package com.example.reserva.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comercio_servicio")
@Data @NoArgsConstructor @AllArgsConstructor
@IdClass(ComercioServicioId.class)
public class ComercioServicio {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_comercio", nullable = false)
    private Comercio comercio;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio;
}
