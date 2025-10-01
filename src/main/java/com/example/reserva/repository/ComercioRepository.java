package com.example.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reserva.entity.Comercio;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {}
