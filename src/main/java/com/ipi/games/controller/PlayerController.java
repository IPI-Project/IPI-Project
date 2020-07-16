package com.ipi.games.controller;

import com.ipi.games.domain.Game;
import com.ipi.games.domain.Player;
import com.ipi.games.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/list")
    public List<Player> getPlayers() {
        return playerService.listPlayers();}

    @GetMapping ("/logged")
    public Player getLoggedPlayer() {
        return playerService.getLoggedUser();
    }

    @ModelAttribute("game")
    public Game game() {
        return new Game();
    }

    @GetMapping ("/history")
    public String playerHistory(Model model) {
        Player currentPlayer = playerService.getLoggedUser();
        model.addAttribute("games",playerService.gamesHistory(currentPlayer));
        return "history";
    }




}


