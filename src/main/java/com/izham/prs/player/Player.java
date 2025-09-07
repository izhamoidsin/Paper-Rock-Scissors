package com.izham.prs.player;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistoryObserver;

import java.util.UUID;

public abstract class Player implements RoundHistoryObserver {
    private final UUID id;
    private final String name;

    Player(String name) {
        this.name = name;
        id = UUID.randomUUID();
    }

    public abstract Move nextMove();

    public final String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
