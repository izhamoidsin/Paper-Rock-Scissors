package com.izham.prs.player.strategy;

import com.izham.prs.game.Move;

import java.util.List;

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
