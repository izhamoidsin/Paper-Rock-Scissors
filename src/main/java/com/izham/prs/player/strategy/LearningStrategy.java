package com.izham.prs.player.strategy;

import com.izham.prs.game.Move;

import java.util.*;

public class LearningStrategy implements Strategy {
    private final Strategy fallback;

    public LearningStrategy() {
        this.fallback = new RandomStrategy();
    }

    public LearningStrategy(Strategy fallback) {
        this.fallback = fallback;
    }

    @Override
    public Move decideMove(List<Move> opponentMovesMemory) {
        if (opponentMovesMemory.isEmpty()) return fallback.decideMove(opponentMovesMemory);

        // going imperative here because in this case it is clearer than verbose Stream Api chain
        var moveFrequency = new HashMap<Move, Integer>();
        var maxFrequency = 0;
        Move mostCommonMove = null;
        for (Move move : opponentMovesMemory) {
            var frequency = moveFrequency.compute(move, (k, v) -> v == null ? 1 : v + 1);
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                mostCommonMove = move;
            }
        }
        Move bestCounterMove = null;
        for (Move move : Move.values()) {
            if (move.beats(mostCommonMove)) {
                bestCounterMove = move;
                break;
            }
        }
        if (bestCounterMove == null) {
            // todo log error here; no move to beat mostCommonMove but the game should continue
            bestCounterMove = fallback.decideMove(opponentMovesMemory);
        }
        return bestCounterMove;
    }
}
