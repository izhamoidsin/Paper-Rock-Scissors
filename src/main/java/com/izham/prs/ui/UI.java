package com.izham.prs.ui;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistoryObserver;
import com.izham.prs.player.Player;

import java.util.Map;

public interface UI extends RoundHistoryObserver {
    int askRounds();

    Move askNextMove();

    void showScoreboard(Map<Player, Integer> scoreboard);

    void messageGameOver();
}
