package com.izham.prs.player.strategy;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        var mostFrequentOponentMove = opponentMovesMemory.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .get();  // because of emptyHistory check above, it's safe

        for (Move move : Move.values()) {
            if (move.beats(mostFrequentOponentMove))
                return move;
        }

        // todo remove this redundant piece of code
        return fallback.decideMove(opponentMovesMemory);
    }
}
