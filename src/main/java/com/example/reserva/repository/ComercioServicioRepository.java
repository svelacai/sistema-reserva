package com.example.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reserva.entity.ComercioServicio;
import com.example.reserva.entity.ComercioServicioId;

@Repository
public interface ComercioServicioRepository extends JpaRepository<ComercioServicio, ComercioServicioId> {}
