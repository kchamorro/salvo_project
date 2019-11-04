package com.webcode.kc.salvo.repository;


import com.webcode.kc.salvo.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface GameRepository extends JpaRepository<Game, Long> {
}