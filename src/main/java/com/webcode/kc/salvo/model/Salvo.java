package com.webcode.kc.salvo.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private int turn;

    @ElementCollection
    private List<String> locations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;


    public Salvo() { }

    public Salvo(int turn, List<String> locations) {
        this.turn = turn;
        this.locations = locations;
    }

    public long getId() {
        return this.id;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public List<String> getLocations() {
        return this.locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public GamePlayer getGamePlayer(){
        return this.gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer){
        this.gamePlayer = gamePlayer;
    }

    public List<String> getHits(List<String> myShots, Set<Ship> opponentShips){

        List<String> allEnemyLocs = new ArrayList<>();

        opponentShips.forEach(ship -> allEnemyLocs.addAll(ship.getLocations()));

        return myShots
                .stream()
                .filter(shot -> allEnemyLocs
                        .stream()
                        .anyMatch(loc -> loc.equals(shot)))
                .collect(Collectors.toList());

    }

    public List<Ship> getSunkenShips(Set<Salvo> mySalvoes, Set<Ship> opponentShips){

        List<String> allShots = new ArrayList<>();

        mySalvoes.forEach(salvo -> allShots.addAll(salvo.getLocations()));

        return opponentShips
                .stream()
                .filter(ship -> allShots.containsAll(ship.getLocations()))
                .collect(Collectors.toList());
    }


    public Map<String, Object> salvoDTO(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turn", this.getTurn());
        dto.put("player", this.getGamePlayer().getPlayer().getId());
        dto.put("locations", this.getLocations());

        GamePlayer opponent = this.getGamePlayer().getOpponent();

        if(opponent != null){

            Set<Ship> enemyShips = opponent.getShips();

            dto.put("hits", this.getHits(this.getLocations(),enemyShips));

            Set<Salvo> mySalvoes = this.getGamePlayer()
                    .getSalvoes()
                    .stream()
                    .filter(salvo -> salvo.getTurn() <= this.getTurn())
                    .collect(Collectors.toSet());

            dto.put("sunken", this.getSunkenShips(mySalvoes, enemyShips).stream().map(Ship::shipDTO));
        }

        return dto;
    }
}
