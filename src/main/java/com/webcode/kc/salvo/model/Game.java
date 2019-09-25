package com.webcode.kc.salvo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    private Date creationDate;


    //Empty Constructor
    public Game() {
    }

    //Constructor with parameters
    public Game(Long id, Date creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    //toString Method
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                '}';
    }
}
