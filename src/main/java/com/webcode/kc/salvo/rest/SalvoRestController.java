package com.webcode.kc.salvo.rest;

import com.webcode.kc.salvo.model.*;
import com.webcode.kc.salvo.repository.GameRepository;
import com.webcode.kc.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@RequestMapping("/api")
@RestController

public class SalvoRestController {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;


    @RequestMapping("/games")
    public List<Map<String, Object>> getGames() {
        return gameRepository.findAll().stream().map(Game::gameDTO).collect(Collectors.toList());
    }

    @RequestMapping("/leaderboard")
    public List<Map<String, Object>> getPositions() {
        return playerRepository.findAll().stream()
                .sorted(comparing(Player::getTotalPoints).reversed())
                .map(Player::playerDTO)
                .collect(Collectors.toList());
    }

    private Comparator<Object> comparing(Object getTotalPoints) {
    }

    private Set<Map> shipsList(Set<Ship> ships) {
        return ships.stream()
                .map(ship -> ship.shipDTO())
                .collect(Collectors.toSet());
    }

    private List<Map> salvoesList(List<Salvo> salvoes) {
        return salvoes.stream()
                .map(salvo -> salvo.salvoDTO())
                .collect(Collectors.toList());
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    public Map<String, Object> getGameView(@PathVariable long gamePlayerId) {

        return this.gameViewDTO(gamePlayerRepository.findById(gamePlayerId).orElse(null));

    }

    private Map<String, Object> gameViewDTO(GamePlayer gamePlayer) {


        Map<String, Object> dto = new LinkedHashMap<>();

        if (gamePlayer != null) {
            dto.put("gameId", gamePlayer.getGame().getId());
            dto.put("gameCreationDate", gamePlayer.getGame().getCreationDate());
            dto.put("player", gamePlayer.getPlayer().getUserName());
            dto.put("playersInThisGame", gamePlayer.getGame().getGamePlayers().stream().map(GamePlayer::gamePlayerDTO));
            
            
            dto.put("opponent", gamePlayer.getGame().getGamePlayers().stream()
                    .filter(x -> x.getPlayer().getUserName() != gamePlayer.getPlayer().getUserName())
                    .map(GamePlayer::gamePlayerUserNameDTO));
            dto.put("ships", gamePlayer.getShips().stream().map(Ship::shipDTO));
            dto.put("salvoes", gamePlayer.getGame().getGamePlayers().stream()
                    .flatMap(gp -> gp.getSalvoes().stream()
                            .map(salvo -> salvo.salvoDTO())));
            dto.put("enemySalvoes", salvoesList(gamePlayer.getGame().getGamePlayers().stream()
                    .filter(gp -> gp.getId() != gamePlayer.getId()).findFirst()
                    .orElseThrow(() -> new RuntimeException()).getSalvoes()));
        } else {
            dto.put("error", "no such game");
        }
        return dto;
    }


}
}
