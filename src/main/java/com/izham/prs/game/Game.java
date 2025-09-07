package com.izham.prs.game;

import com.izham.prs.player.Player;

import java.util.Map;

public interface Game {
    void play();

    Map<Player, Integer> getScoreboard();
}
