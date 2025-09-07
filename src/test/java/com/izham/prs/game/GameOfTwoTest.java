package com.izham.prs.game;

import com.izham.prs.player.ComputerPlayer;
import com.izham.prs.player.HumanPlayer;
import com.izham.prs.player.strategy.BruteStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfTwoTest {

    @Test
    public void gameBetweenComputersWithConstantInput() {
        var rounds = 25;
        var playerA = new ComputerPlayer("Player A", new BruteStrategy(Move.SCISSORS));
        var playerB = new ComputerPlayer("Player B", new BruteStrategy(Move.ROCK));
        var game = new GameOfTwo(rounds, playerA, playerB);

        game.play();

        var scores = game.getScoreboard();

        assertEquals(0, scores.get(playerA));
        assertEquals(rounds, scores.get(playerB));
    }

    @Test
    public void gameBetweenHumansWithMockedInput() {
        var movesA = List.of(Move.ROCK, Move.SCISSORS, Move.PAPER, Move.PAPER, Move.SCISSORS).iterator();
        var movesB = List.of(Move.ROCK, Move.PAPER, Move.ROCK, Move.SCISSORS, Move.ROCK).iterator();
        var playerA = new HumanPlayer("Player A", movesA::next);
        var playerB = new HumanPlayer("Player B", movesB::next);
        var game = new GameOfTwo(5, playerA, playerB);

        game.play();

        var scores = game.getScoreboard();

        assertEquals(2, scores.get(playerA));
        assertEquals(2, scores.get(playerB));
    }
}
