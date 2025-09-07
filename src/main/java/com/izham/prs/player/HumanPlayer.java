package com.izham.prs.player;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;

import java.util.function.Supplier;


public class HumanPlayer extends Player {
    private final Supplier<Move> in;

    public HumanPlayer(String name, Supplier<Move> in) {
        super(name);
        this.in = in;
    }

    @Override
    public Move nextMove() {
        return in.get();
    }

    @Override
    public void observeRound(RoundHistory roundHistory) {
        // do nothing, it's up to a human to analyze the round outcome
    }
}
