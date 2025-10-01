package com.example.reserva.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reserva.entity.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByServicioIdAndFechaBetween(Long idServicio, Date inicio, Date fin);
}

