package com.ipi.games.controller;

import com.ipi.games.DTO.CreateMoveDTO;
import com.ipi.games.DTO.MoveDTO;
import com.ipi.games.domain.Game;
import com.ipi.games.domain.Move;
import com.ipi.games.service.GameService;
import com.ipi.games.service.MoveService;
import com.ipi.games.service.PlayerService;
import com.ipi.games.domain.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/move")
public class MoveController {

    @Autowired
    private MoveService moveService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Move createMove(@RequestBody CreateMoveDTO createMoveDTO) {
        Long gameId = (Long) httpSession.getAttribute("gameId");
        log.info("move to insert:" + createMoveDTO.getBoardColumn() + createMoveDTO.getBoardRow());


        Move move = moveService.createMove(gameService.getGame(gameId), playerService.getLoggedUser(), createMoveDTO);
        Game game = gameService.getGame(gameId);
        gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game));

        return move;
    }

    @RequestMapping(value = "/autocreate", method = RequestMethod.GET)
    public Move autoCreateMove() {
        Long gameId = (Long) httpSession.getAttribute("gameId");

        log.info("AUTO move to insert:" );

        Move move = moveService.autoCreateMove(gameService.getGame(gameId));

        Game game = gameService.getGame(gameId);
        gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game));

        return move;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<MoveDTO> getMovesInGame() {

        Long gameId = (Long) httpSession.getAttribute("gameId");

      return moveService.getMovesInGame(gameService.getGame(gameId));
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public List<Position> validateMoves() {
        Long gameId = (Long) httpSession.getAttribute("gameId");
        return moveService.getPlayerMovePositionsInGame(gameService.getGame(gameId), playerService.getLoggedUser());
    }

    @RequestMapping(value = "/turn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean isPlayerTurn() {
        Long gameId = (Long) httpSession.getAttribute("gameId");
        return moveService.isPlayerTurn(gameService.getGame(gameId), gameService.getGame(gameId).getFirstPlayer(),
                gameService.getGame(gameId).getSecondPlayer());
    }



}
