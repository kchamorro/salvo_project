package com.webcode.kc.salvo.model;

import javax.persistence.*;

@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private GamePlayer gamePlayerId;

    //Empty Constructor
    public Ship() {
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GamePlayer getGamePlayerId() {
        return gamePlayerId;
    }

    public void setGamePlayerId(GamePlayer gamePlayerId) {
        this.gamePlayerId = gamePlayerId;
    }

    //to String Method
    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", gamePlayerId=" + gamePlayerId +
                '}';
    }
}

