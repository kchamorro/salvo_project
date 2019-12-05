package com.webcode.kc.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private LocalDateTime joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gameplayer_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    Set<Ship> ships = new HashSet<>();

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    Set<Salvo> salvos = new HashSet<>();

    public Set<Ship> getShip() {
        return ships;
    }

    public void setShip(Set<Ship> ship) {
        this.ships = ship;
    }

    public GamePlayer() {
    }

    public GamePlayer(Game game,Player player,LocalDateTime joinDate) {
        this.game = game;
        this.player = player;
        this.joinDate = joinDate;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public Player getPlayer(){
        return player;
    }

    public Set<Ship> getShips() {
        return ships;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }


    public Game getGame(){
        return game;
    };

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void addShip(Ship ship){
        ship.setGamePlayer(this);
        this.ships.add(ship);
    }

    public Set<Salvo> getSalvos() {
        return salvos;
    }

    public void setSalvos(Set<Salvo> salvos) {
        this.salvos = salvos;
    }

    public void addSalvo(Salvo salvo){
        salvo.setGamePlayer(this);
        this.salvos.add(salvo);
    }

    public Integer getLastTurn(){
        if(!this.getSalvos().isEmpty()){
            return this.getSalvos().stream()
                    .map(salvo1 ->salvo1.getTurn() )
                    .max((x,y)->Integer.compare(x,y))
                    .get();
        }else {
            return 0;
        }
    }

}
