package com.ipi.games.DTO;

import com.ipi.games.domain.Player;
import com.ipi.games.enums.GameType;
import com.ipi.games.enums.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {

    private int id;
    private GameType gameType;
    private Piece piece;
}


