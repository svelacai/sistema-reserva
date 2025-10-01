package com.example.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reserva.entity.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {}
