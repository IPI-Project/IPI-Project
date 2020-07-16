package com.ipi.games.service;

import com.ipi.games.DTO.PlayerDTO;
import com.ipi.games.domain.Game;
import com.ipi.games.domain.Player;
import com.ipi.games.enums.GameStatus;
import com.ipi.games.repository.GameRepository;
import com.ipi.games.repository.PlayerRepository;
import com.ipi.games.security.ContextUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createNewPlayer(PlayerDTO playerDTO) {
        Player newPlayer = new Player();
        newPlayer.setUserName(playerDTO.getUserName());
        newPlayer.setPassword(passwordEncoder.encode(playerDTO.getPassword()));
        newPlayer.setEmail(playerDTO.getEmail());
        playerRepository.save(newPlayer);
        return newPlayer;
    }

    public Player getLoggedUser() {
        ContextUser principal = (ContextUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return playerRepository.findOneByUserName(principal.getPlayer().getUserName());
    }

    public List<Player> listPlayers() {
        List<Player> players = (List<Player>) playerRepository.findAll();
        return players;
    }

    public List<Game> gamesHistory(Player player) {
        List<Game> gamesList = (List<Game>) gameRepository.findAllByFirstPlayerIdOrSecondPlayerId(player.getId(),player.getId()).
                stream().filter(game -> game.getGameStatus() != GameStatus.IN_PROGRESS).collect(Collectors.toList());
        return gamesList;
    }

}

