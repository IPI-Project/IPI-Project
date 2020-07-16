package com.ipi.games.controller;


import com.ipi.games.DTO.PlayerDTO;
import com.ipi.games.domain.Player;
import com.ipi.games.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

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
        return "redirect:/registration?success";
    }
}
