package com.webcode.kc.salvo.model;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.CascadeType;
import java.util.stream.Collectors;


@Entity
public class Game {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;


    private LocalDateTime creationDate;


    @OneToMany(mappedBy="game", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    private Set<GamePlayer> gamePlayers = new HashSet<>();

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
    private Set<Score> scores = new HashSet<>();


    //CONSTRUCTORES

    //constructor vacío
    public Game() {}

    public Game(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }


    //GETTERS Y SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    // Establecer relación entre game y gamePlayer
    public void addGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayers.add(gamePlayer);
        gamePlayer.setGame(this);
    }

    public Set<Score> getScores(){
        return this.scores;
    }

    public void addScore(Score score){
        this.scores.add(score);
        score.setGame(this);
    }

    //Retormar los players
    public List<Player> getPlayers() {
        return this.gamePlayers.stream().map(gp -> gp.getPlayer()).collect(Collectors.toList());
    }

    //DTO (data transfer object) para administrar la info de Game
    public Map<String, Object> gameDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("created", this.getCreationDate());
        dto.put("gamePlayers", this.getGamePlayers().stream().map(GamePlayer::gamePlayerDTO).collect(Collectors.toList()));
        return dto;
    }

}