package com.izham.prs.player.strategy;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomStrategy implements Strategy {
    private final Random random;

    public RandomStrategy() {
        random = new Random();
    }

    // todo do I need it
    public RandomStrategy(long seed) {
        random = new Random(seed);
    }

    public Move decideMove(List<Move> opponentMovesMemory) {
        var idx = random.nextInt(Move.values().length);
        return Move.values()[idx];
    }
}
