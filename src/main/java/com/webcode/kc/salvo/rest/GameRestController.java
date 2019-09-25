package com.webcode.kc.salvo.rest;

import com.webcode.kc.salvo.model.Game;
import com.webcode.kc.salvo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController

public class GameRestController {


    @Autowired
    private GameRepository repo;

    @RequestMapping("/games")

    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getId());
        dto.put("created", game.getCreationDate());
        return dto;
    }

    /*
    public List<Game> getAllGames(){
        return repo
                .findAll()
                .stream()
                .map(gamePlayer -> makeGamePlayerDTO())
                .collect(Collectors.toList());
    }





    private Map<String, Object> makePlayerDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", player.getId());
        dto.put("username", player.getUserName());
        return dto;
    }

    private Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", makePlayerDTO(gamePlayer.getPlayer()));
        return dto;
    }


     */
}
