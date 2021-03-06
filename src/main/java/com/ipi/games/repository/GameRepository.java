package com.ipi.games.repository;

import com.ipi.games.domain.Game;
import com.ipi.games.enums.GameStatus;
import com.ipi.games.enums.GameType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long>{
    List<Game> findByGameTypeAndGameStatus(GameType GameType, GameStatus GameStatus);
    List<Game> findByGameStatus(GameStatus gameStatus);
    Game findGameById(Long id);
    List<Game> findAllByFirstPlayerIdOrSecondPlayerId(int id, int id1);
}
