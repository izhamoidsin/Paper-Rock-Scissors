package com.izham.prs.game;

import com.izham.prs.player.Player;

import java.util.Map;
import java.util.Set;

public record RoundHistory(int round, Map<Player, Move> moves, Set<Player> winners) {}
