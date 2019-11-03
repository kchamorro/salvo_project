package com.webcode.kc.salvo.repository;

import com.webcode.kc.salvo.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ShipRepository extends JpaRepository<Ship, Long> {
}

