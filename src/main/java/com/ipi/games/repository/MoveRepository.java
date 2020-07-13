package com.ipi.games.repository;

import com.ipi.games.domain.Game;
import com.ipi.games.domain.Move;
import com.ipi.games.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends CrudRepository<Move, Long> {

    List<Move> findByGame(Game game);
    List<Move> findByGameAndPlayer(Game game, Player player);
    int countByGameAndPlayer(Game game, Player player);
}
