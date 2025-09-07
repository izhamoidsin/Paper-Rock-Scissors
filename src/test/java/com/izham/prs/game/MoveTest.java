package com.izham.prs.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @ParameterizedTest
    @CsvSource({
            "PAPER,ROCK,TRUE",
            "PAPER,PAPER,FALSE",
            "PAPER,SCISSORS,FALSE",
            "ROCK,SCISSORS,TRUE",
            "ROCK,ROCK,FALSE",
            "ROCK,PAPER,FALSE",
            "SCISSORS,PAPER,TRUE",
            "SCISSORS,ROCK,FALSE",
            "SCISSORS,SCISSORS,FALSE",
    })
    public void beatsFollowRules(Move move, Move anotherMove, Boolean result) {
        assertEquals(move.beats(anotherMove), result);
    }

    @Test
    public void allMovesHaveEqualChanceToWin() {
        Map<Move, Integer> chances = new HashMap<>();

        for (Move move : Move.values()) {
            for (Move anotherMove : Move.values()) {
                if (move.equals(anotherMove)) {
                    continue;
                } else if (move.beats(anotherMove)) {
                    chances.compute(move, (k, v) -> v == null ? 1 : v + 1);
                }
            }
        }
        assertEquals(
            1L, chances.values().stream().distinct().count(),
            "Chance to win is not equal for every move. Check the rules!"
        );
    }
}
