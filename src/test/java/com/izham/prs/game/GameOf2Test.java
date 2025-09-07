package com.izham.prs.game;

import com.izham.prs.player.ComputerPlayer;
import com.izham.prs.player.HumanPlayer;
import com.izham.prs.player.strategy.BruteStrategy;
import com.izham.prs.ui.TerminalUI;
import com.izham.prs.ui.UI;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOf2Test {
    // todo replace console with logger
    private final UI ui = new TerminalUI();

    @Test
    public void gameBetweenComputersWithConstantInput() {
        var rounds = 25;
        var ui = new TerminalUI();
        var playerA = new ComputerPlayer("Player A", new BruteStrategy(Move.SCISSORS));
        var playerB = new ComputerPlayer("Player B", new BruteStrategy(Move.ROCK));
        var game = new GameOf2(rounds, ui, playerA, playerB);

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
        var game = new GameOf2(5, ui, playerA, playerB);

        game.play();

        var scores = game.getScoreboard();

        assertEquals(2, scores.get(playerA));
        assertEquals(2, scores.get(playerB));
    }
}
