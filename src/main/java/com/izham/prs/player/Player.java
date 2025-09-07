package com.izham.prs.player;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;

import java.util.UUID;

public abstract class Player {
    private final UUID id;
    private final String name;

    Player(String name) {
        this.name = name;
        id = UUID.randomUUID();
    }

    public abstract void observeRound(RoundHistory roundHistory);

    public abstract Move nextMove();

    public final String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
