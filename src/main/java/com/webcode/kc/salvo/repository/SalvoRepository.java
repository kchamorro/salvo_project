package com.webcode.kc.salvo.repository;

import com.webcode.kc.salvo.model.Salvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface SalvoRepository extends JpaRepository<Salvo, Long> {
}
