package com.izham.prs.ui;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;
import com.izham.prs.player.Player;

import java.util.Map;

public interface UI {
    int askRounds();

    Move askNextMove();

    void showRoundResult(RoundHistory roundHistory);

    void showScoreboard(Map<Player, Integer> scoreboard);
}
