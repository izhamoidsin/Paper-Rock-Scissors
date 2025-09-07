package com.izham.prs.player.strategy;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomStrategyTest {
    private static final int ATTEMPTS = 1000;
    private static final int DEVIATION_THRESHOLD_PERCENT = 10;

    @Test
    public void probabilityDistributedUniformly() {
        var sut = new RandomStrategy();
        var moveFrequency = new HashMap<Move, Integer>();

        for (int i = 0; i < ATTEMPTS; i++) {
            var move = sut.decideMove(List.of());
            moveFrequency.compute(move, (k, v) -> v == null ? 1 : v + 1);
        }

        var expectedValue = ATTEMPTS / Move.values().length;
        var deviationThreshold = expectedValue * DEVIATION_THRESHOLD_PERCENT / 100;
        moveFrequency.entrySet().forEach(probability -> {
            var deviation = Math.abs(probability.getValue() - expectedValue);
            var errorMessage = String.format(
                "%s move frequency %d is not in expected interval [%d,%d]",
                probability.getKey(),
                probability.getValue(),
                expectedValue - deviationThreshold,
                expectedValue + deviationThreshold
            );
            assertTrue(deviation <= deviationThreshold, errorMessage);
        });
    }

    @Test
    public void memoryDoseNotAffectNextMove() {
        var seed = System.nanoTime();

        var sut1 = new RandomStrategy(seed);
        var sut2 = new RandomStrategy(seed);

        var emptyHistory = new LinkedList<Move>();
        var fullHistory = List.of(Move.ROCK, Move.SCISSORS, Move.PAPER, Move.PAPER, Move.PAPER, Move.PAPER, Move.ROCK);

        for (int i = 0; i < ATTEMPTS; i++) {
            var nextMoveWoHistory = sut1.decideMove(emptyHistory);
            var nextMoveWithHistory = sut2.decideMove(fullHistory);

            assertEquals(nextMoveWoHistory, nextMoveWithHistory);
        }
    }
}
