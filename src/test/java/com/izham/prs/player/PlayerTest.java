package com.izham.prs.player;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;
import com.izham.prs.player.strategy.RandomStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    public void computerMemorizesOpponentMoves() {
        var computer = new ComputerPlayer("Computer", new RandomStrategy());
        var opponent = new HumanPlayer("Human", () -> Move.ROCK);

        computer.onRoundComplete(new RoundHistory(0, Map.of(computer, Move.SCISSORS, opponent, Move.ROCK), Set.of(opponent)));
        computer.onRoundComplete(new RoundHistory(0, Map.of(computer, Move.ROCK, opponent, Move.ROCK), Set.of()));
        computer.onRoundComplete(new RoundHistory(0, Map.of(computer, Move.PAPER, opponent, Move.ROCK), Set.of(computer)));

        assertEquals(computer.getOpponentMovesMemory(), List.of(Move.ROCK, Move.ROCK, Move.ROCK));
    }
}
