package com.izham.prs.player.strategy;

import com.izham.prs.game.Move;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy {
    private final Random random;

    public RandomStrategy() {
        random = new Random();
    }

    // internal api with controlled seed for reproducible random
    RandomStrategy(long seed) {
        random = new Random(seed);
    }

    public Move decideMove(List<Move> opponentMovesMemory) {
        var idx = random.nextInt(Move.values().length);
        return Move.values()[idx];
    }
}
