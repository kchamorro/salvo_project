package com.webcode.kc.salvo.repository;


import com.webcode.kc.salvo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.*;



@RepositoryRestResource
public interface PlayerRepository extends JpaRepository <Player, Long>{
    List<Player> findByUserName(String userName);
    Optional<Player> findFirstByUserName(String userName);
}
