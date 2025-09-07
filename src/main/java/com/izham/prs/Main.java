package com.izham.prs;

import com.izham.prs.game.GameOf2;
import com.izham.prs.player.ComputerPlayer;
import com.izham.prs.player.HumanPlayer;
import com.izham.prs.player.strategy.LearningStrategy;
import com.izham.prs.ui.TerminalUI;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        var ui = new TerminalUI();
        var rounds = ui.askRounds();

        var human = new HumanPlayer("Player", ui::askNextMove);
        var computer = new ComputerPlayer("Computer", new LearningStrategy());
        var game = new GameOf2(rounds, ui, human, computer);

        game.play();
        ui.showScoreboard(game.getScoreboard());
    }
}
