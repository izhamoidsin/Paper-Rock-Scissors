package com.izham.prs.player.strategy;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;

import java.util.List;
import java.util.UUID;

public interface Strategy {
    // todo think about moving selfId out of the contract
    Move decideMove(List<Move> opponentMovesMemory);
}
