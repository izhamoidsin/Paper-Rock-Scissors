package com.izham.prs;

import com.izham.prs.game.GameOfTwo;
import com.izham.prs.player.ComputerPlayer;
import com.izham.prs.player.HumanPlayer;
import com.izham.prs.player.strategy.LearningStrategy;
import com.izham.prs.ui.TerminalUI;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        var terminal = new TerminalUI();
        var rounds = terminal.askRounds();

        var human = new HumanPlayer("Player", terminal::askNextMove);
        var computer = new ComputerPlayer("Computer", new LearningStrategy());
        var game = new GameOfTwo(rounds, human, computer);
        game.addObserver(terminal);

        game.play();

        terminal.messageGameOver();
        terminal.showScoreboard(game.getScoreboard());
    }
}
