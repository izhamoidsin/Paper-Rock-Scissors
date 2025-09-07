package com.izham.prs.player.strategy;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LearningStrategyTest {
    @Test
    public void fallbackIsUsedWhenMemoryIsEmpty() {
        var memory = new LinkedList<Move>();
        var sut = new LearningStrategy(new BruteStrategy(Move.SCISSORS));
        sut.decideMove(memory);
    }

    @Test
    public void nextMoveIsDynamicallyAdjustedToMemory() {
        var memory = new LinkedList<Move>() {{
           add(Move.ROCK);
           add(Move.ROCK);
           add(Move.ROCK);
           add(Move.PAPER);
           add(Move.PAPER);
           add(Move.SCISSORS);
           add(Move.ROCK);
           add(Move.PAPER);
           add(Move.SCISSORS);
        }};

        var sut = new LearningStrategy(new BruteStrategy(Move.SCISSORS));
        assertEquals(Move.PAPER, sut.decideMove(memory));
        memory.add(Move.PAPER);
        assertTrue(Move.PAPER.equals(sut.decideMove(memory)) || Move.SCISSORS.equals(sut.decideMove(memory)));
        memory.add(Move.PAPER);
        assertEquals(Move.SCISSORS, sut.decideMove(memory));
    }
}
