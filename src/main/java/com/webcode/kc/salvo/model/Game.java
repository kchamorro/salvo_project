package com.webcode.kc.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private LocalDateTime creationDate;


    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<GamePlayer> gamePlayers = new HashSet<>();


    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Score> scores = new HashSet<>();



    //Empty Constructor
    public Game() {
    }

    //Constructor with parameters
    public Game(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }



    public void addGamePlayer(GamePlayer gamePlayer) {
        //se agrega el gamePlayer que ingresa como parámetro al set declarado en los atributos
        this.gamePlayers.add(gamePlayer);
        //al gamePlayer ingresado se le agrega este game mediante su setter en la clase GamePlayer
        gamePlayer.setGame(this);
    }

    public Set<Score> getScores() {
        return this.scores;
    }

    public void addScore(Score score) {
        this.scores.add(score);
        score.setGame(this);
    }


    //método que retorna todos los players relacionados con el game a partir de los gamePlayers
    public List<Player> getPlayers() {
        return this.gamePlayers.stream().map(gp -> gp.getPlayer()).collect(Collectors.toList());
    }


    public Score getScoreByPlayer(Player player) {
        return this.scores.stream()
                .filter(score -> score.getPlayer().getId() == player.getId())
                .findAny()
                .orElse(null);
    }

    public Map<String, Object> gameDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("created", this.getCreationDate());
        dto.put("gamePlayers", this.getGamePlayer().stream().map(GamePlayer::gamePlayerDTO).collect(Collectors.toList()));
        return dto;
    }


}
