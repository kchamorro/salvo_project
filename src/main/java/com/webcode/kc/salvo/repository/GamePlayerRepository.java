package com.webcode.kc.salvo.repository;

import com.webcode.kc.salvo.model.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {
}