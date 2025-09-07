package com.izham.prs.player.strategy;

import com.izham.prs.game.Move;
import java.util.List;

public interface Strategy {

    Move decideMove(List<Move> opponentMovesMemory);
}
