package com.ipi.games.controller;

import com.ipi.games.DTO.GameDTO;
import com.ipi.games.DTO.PlayerDTO;
import com.ipi.games.domain.Game;
import com.ipi.games.domain.Player;
import com.ipi.games.service.PlayerService;
import com.ipi.games.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @ModelAttribute("player")
    public PlayerDTO playerRegistrationDto() {
        return new PlayerDTO();
    }


    @GetMapping ("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createAccount(@ModelAttribute("player") PlayerDTO newPlayerDTO) {
        Player newPlayer = playerService.createNewPlayer(newPlayerDTO);
        return "redirect:/player/registration?success";
    }

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


