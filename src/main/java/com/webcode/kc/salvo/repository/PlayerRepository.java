package com.webcode.kc.salvo.repository;


import com.webcode.kc.salvo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUserName(String username);
}
