package com.izham.prs.game;

import java.util.Set;

public enum Move {
    PAPER,
    SCISSORS,
    ROCK;
    // todo check how it would evolve if we do Rock, Paper, Scissors, Lizard, Spock

    public boolean beats(Move other) {
        return switch (this) {
            case PAPER -> other == ROCK;
            case SCISSORS -> other == PAPER;
            case ROCK -> other == SCISSORS;
        };
    }
}
