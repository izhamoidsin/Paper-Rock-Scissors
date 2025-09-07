package com.izham.prs.player.strategy;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;

import java.util.List;
import java.util.UUID;

public class BruteStrategy implements Strategy {
    private final Move preferredMove;

    public BruteStrategy(Move preferredMove) {
        this.preferredMove = preferredMove;
    }

    @Override
    public Move decideMove(List<Move> opponentMovesMemory) {
        return preferredMove;
    }
}
