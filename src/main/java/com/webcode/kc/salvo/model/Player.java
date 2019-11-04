package com.webcode.kc.salvo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;


//Creacion clase Player
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String firstName;
    private String lastName;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String userName;


    private int password;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<GamePlayer> gamePlayers = new HashSet<>();


    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Score> scores = new HashSet<>();



    //CONSTRUCTORES

    public Player() { }

    public Player(String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = 0;
    }

    public Player(String userName, String firstName, String lastName, int password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }


    //GET Y SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Set<GamePlayer> getGamePlayers() {
        return this.gamePlayers;
    }


    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }


    public void setLastLogin(Date date) {

    }

    public Set<Score> getScores() {
        return this.scores;
    }

    public void addScore(Score score) {
        this.scores.add(score);
        score.setPlayer(this);
    }

    public Score getScoreByGame(Game game) {
        return this.scores.stream()
                .filter(score -> score.getGame().getId() == game.getId())
                .findFirst()
                .orElse(null);
    }

    public Set<Score> getLossesScores() {
        return this.scores.stream()
                .filter(lossScore -> lossScore.getPoints() == 0)
                .collect(Collectors.toSet());
    }

    public Set<Score> getTiesScores() {
        return this.scores.stream()
                .filter(tieScore -> tieScore.getPoints() == 1)
                .collect(Collectors.toSet());
    }

    public Set<Score> getWonScores() {
        return this.scores.stream()
                .filter(wonScore -> wonScore.getPoints() == 3)
                .collect(Collectors.toSet());
    }

    public int getTotalPoints() {
        return this.getWonScores().size() * 3 + getTiesScores().size();
    }


    public void addGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayers.add(gamePlayer);
        gamePlayer.setPlayer(this);
    }

    //método que retorna todos los games relacionados con el player a partir de los gamePlayers
    @JsonIgnore
    public List<Game> getGames() {
        return this.gamePlayers.stream().map(gp -> gp.getGame()).collect(Collectors.toList());
    }

    //DTO (data transfer object) para administrar la info de Player
    public Map<String, Object> playerDTO() {
        Integer cantWon = this.getWonScores().size();
        Integer cantLose = this.getLossesScores().size();
        Integer cantTie = this.getTiesScores().size();
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("username", this.getUserName());
        dto.put("won", cantWon);
        dto.put("lose", cantLose);
        dto.put("tie", cantTie);
        dto.put("total", this.getTotalPoints());
        return dto;
    }
}
