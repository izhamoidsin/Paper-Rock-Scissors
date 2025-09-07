package com.izham.prs.game;

public enum Move {
    // If the game evolves (e.g. Paper, Scissors, Rock, Lizard, Spock) new Moves should be added here,
    // Compiler will enforce switch block update and com/izham/prs/game/MoveTest.java will check the rules, including new ones.
    PAPER,
    SCISSORS,
    ROCK;

    public boolean beats(Move other) {
        return switch (this) {
            case PAPER -> other == ROCK;
            case SCISSORS -> other == PAPER;
            case ROCK -> other == SCISSORS;
        };
    }
}
